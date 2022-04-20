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
    TextField usernameTextfield;
    @FXML
    TextField passwordTextfield;
    @FXML
    ImageView captchaImage;
    @FXML
    TextField captchaTextfield;
    @FXML
    Button loginButton;


    @FXML
    public void initialize(){
        backend = Backend.getInstance();
        setRandomCaptcha();
    }


    @FXML
    void loginButtonOnAction(ActionEvent actionEvent) {
        String captchaText = captchaTextfield.getText();

        if(captcha.isCorrect(captchaText)){
            String studentOrProfessorNumber = usernameTextfield.getText();
            String password = passwordTextfield.getText();

            User user = backend.getUserObjByUserPass(studentOrProfessorNumber, password);

            if(user == null){
                error("username and password don't match");
            }
            else if(user instanceof Student){
                LoggedInUserHolder.setUser(user);
                try {
                    goToStudentPage("studentHomePage.fxml");
                } catch (IOException e) {
                    error("some backend problem happened, try again");
                }
            }
            else if(user instanceof Professor){
                LoggedInUserHolder.setUser(user);
                //TODO
//                try {
//                    goToStudentPage("HomePage.fxml");
//                } catch (IOException e) {
                    error("some backend problem happened, try again");
//                }
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
        usernameTextfield.setText("");
        passwordTextfield.setText("");

        setRandomCaptcha();
        Image image = new Image(captcha.getImagePath());
        captchaImage.setImage(image);
        captchaTextfield.setText("");
    }

    private void setRandomCaptcha(){
        captcha = backend.getCaptcha(1);
    }
}
