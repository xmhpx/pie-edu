package pagecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import server.logic.Backend;
import models.LoggedInUserHolder;
import models.Captcha;
import models.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ForcedChangePasswordPageController extends LoggedInPageController {
    private static final Logger log = LogManager.getLogger(ForcedChangePasswordPageController.class);


    protected Captcha captcha;
    protected Backend backend;


    @FXML
    Text errorText;

    @FXML
    TextField passwordTextField;

    @FXML
    TextField newPasswordTextField;

    @FXML
    TextField repeatNewPasswordTextField;

    @FXML
    ImageView captchaImage;

    @FXML
    TextField captchaTextField;

    @FXML
    Button changePasswordButton;

    @FXML
    Button changeCaptchaButton;


    @FXML
    public void initialize(){
        super.initialize();
        backend = Backend.getInstance();
        clean();
    }


    @FXML
    void changePasswordButtonOnAction(ActionEvent actionEvent) {
        String captchaText = captchaTextField.getText();

        if(captcha.isCorrect(captchaText)){
            String currentPassword = passwordTextField.getText();
            String newPassword = newPasswordTextField.getText();
            String repeatNewPassword = repeatNewPasswordTextField.getText();

            if (!newPassword.equals(repeatNewPassword)) {
                error("new password and repeated new password don't match");
                return;
            }

            User user = LoggedInUserHolder.getUser();

            if(user == null){
                log.error("'user' is null");
                throw new IllegalStateException("'user' is null");
            }

            if(newPassword.length() < 5){
                error("new password is too short");
            }
            else if(user.getHashedPassword() != currentPassword.hashCode()){
                error("incorrect password");
            }
            else{
                user.setPassword(newPassword);
                error("password has been changed");
                goToLoginPage();
            }
        }
        else {
            error("incorrect captcha");
        }
    }


    @FXML
    void changeCaptchaButtonOnAction(ActionEvent actionEvent){
        setNewCaptchaImage();
    }


    private void error(String error){
        clean();
        errorText.setText(error);
    }

    private void clean(){
        errorText.setText("");
        passwordTextField.setText("");
        newPasswordTextField.setText("");
        repeatNewPasswordTextField.setText("");

        setNewCaptchaImage();
    }

    private void setRandomCaptcha(){
        captcha = backend.getRandomCaptcha();
    }

    private void setNewCaptchaImage(){
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
}
