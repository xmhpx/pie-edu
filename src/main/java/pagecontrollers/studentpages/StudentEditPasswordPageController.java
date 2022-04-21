package pagecontrollers.studentpages;

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

public class StudentEditPasswordPageController extends StudentPageController {

    public static final String fxmlFileName = "studentEditPasswordPage.fxml";

    protected Captcha captcha;
    protected Backend backend;

    @FXML
    Text errorText;

    @FXML
    TextField currentPasswordTextField;

    @FXML
    TextField newPasswordTextField;

    @FXML
    TextField repeatNewPasswordTextField;

    @FXML
    ImageView captchaImage;

    @FXML
    TextField captchaTextField;

    @FXML
    Button editPasswordButton;


    @FXML
    public void initialize(){
        backend = Backend.getInstance();
        setRandomCaptcha();
    }


    @FXML
    void editPasswordButtonOnAction(ActionEvent actionEvent) {
        String captchaText = captchaTextField.getText();

        if(captcha.isCorrect(captchaText)){
            String currentPassword = currentPasswordTextField.getText();
            String newPassword = newPasswordTextField.getText();
            String repeatNewPassword = repeatNewPasswordTextField.getText();

            if (!newPassword.equals(repeatNewPassword)) {
                error("new password and repeated new password don't match");
                return;
            }

            User user = LoggedInUserHolder.getUser();

            if(newPassword.length() < 5){
                error("new password is too short");
            }
            else if(user.getHashedPassword() != currentPassword.hashCode()){
                error("incorrect password");
            }
            else{
                user.setPassword(newPassword);
                error("password changed");
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
        currentPasswordTextField.setText("");
        newPasswordTextField.setText("");
        repeatNewPasswordTextField.setText("");

        setRandomCaptcha();
        Image image = new Image(captcha.getImagePath());
        captchaImage.setImage(image);
        captchaTextField.setText("");
    }

    private void setRandomCaptcha(){
        captcha = backend.getRandomCaptcha();
    }
}
