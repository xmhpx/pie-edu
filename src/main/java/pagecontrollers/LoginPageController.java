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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class LoginPageController extends BasicPageController {
    private static final Logger log = LogManager.getLogger(LoginPageController.class);


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
                goToStudentPage("studentHomePage.fxml");
            }
            else if(user instanceof Professor){
                LoggedInUserHolder.setUser(user);
                goToProfessorPage("professorHomePage.fxml");
            }
            else{
                log.warn("'user' is neither Student nor Professor");
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

        Image image;

        try {
            image = new Image(captcha.getImagePath());
        }
        catch (Exception e){
            log.error("unable to construct 'image' with imagePath('"+captcha.getImagePath()+"')");
            throw new IllegalStateException("unable to construct 'image' with imagePath('"+captcha.getImagePath()+"')");
        }

        captchaImage.setImage(image);
        captchaTextField.setText("");
    }

    private void setRandomCaptcha(){
        captcha = backend.getRandomCaptcha();
    }
}
