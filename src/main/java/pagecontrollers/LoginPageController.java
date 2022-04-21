package pagecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.Captcha;
import models.User;
import models.professor.Professor;
import models.student.Student;

import java.io.IOException;

public class LoginPageController extends BasicPageController {

    protected Captcha captcha;
    protected Backend backend;


    @FXML
    Text errorText;

    @FXML
    TextField usernameTextField;

    @FXML
    TextField passwordTextField;

    @FXML
    ImageView captchaImage;

    @FXML
    TextField captchaTextField;

    @FXML
    Button loginButton;


    @FXML
    public void initialize(){
        super.initialize();
        backend = Backend.getInstance();
        clean();
    }


    @FXML
    void loginButtonOnAction(ActionEvent actionEvent) {
        String captchaText = captchaTextField.getText();

        if(captcha.isCorrect(captchaText)){
            String studentOrProfessorNumber = usernameTextField.getText();
            String password = passwordTextField.getText();

            User user = backend.getUserObjByUserPass(studentOrProfessorNumber, password);

            if(user == null){
                error("username and password don't match");
            }
            else if(user instanceof Student){
                LoggedInUserHolder.setUser(user);
                try {
                    goToStudentPage("studentHomePage.fxml");
                } catch (IOException e) {
                    error("some AAAAAAA backend problem happened, try again");
                }
            }
            else if(user instanceof Professor){
                LoggedInUserHolder.setUser(user);
                try {
                    goToProfessorPage("professorHomePage.fxml");
                } catch (IOException e) {
                    error("some BBBBBB backend problem happened, try again");
                }
            }
        }
        else {
            error("incorrect captcha");
        }
    }

    private void error(String error){
        clean();
        errorText.setText(error);
    }

    private void clean(){
        errorText.setText("");
        usernameTextField.setText("");
        passwordTextField.setText("");

        setRandomCaptcha();
        Image image = new Image(captcha.getImagePath());
        captchaImage.setImage(image);
        captchaTextField.setText("");
    }

    private void setRandomCaptcha(){
        captcha = backend.getRandomCaptcha();
    }
}
