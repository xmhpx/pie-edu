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

public class StudentEditProfileController extends StudentPageController {

    public static final String fxmlFileName = "studentEditProfile.fxml";

    protected Captcha captcha;
    protected Backend backend;

    @FXML
    Text errorText;

    @FXML
    ImageView newProfileImageImageView;

    @FXML
    TextField newNameTextField;

    @FXML
    TextField passwordTextField;

    @FXML
    ImageView captchaImage;

    @FXML
    TextField captchaTextField;

    @FXML
    Button applyButton;


    @FXML
    public void initialize(){
        super.initialize();
        backend = Backend.getInstance();
        clean();
    }


    @FXML
    void applyButtonOnAction(ActionEvent actionEvent) {
        String captchaText = captchaTextField.getText();

        if(captcha.isCorrect(captchaText)){
            String newName = newNameTextField.getText();
            String password = passwordTextField.getText();

            User user = LoggedInUserHolder.getUser();

            if(newName.length() < 5){
                error("new name is too short");
            }
            else if(newName.length() > 30){
                error("new name is too long");
            }
            else if(user.getHashedPassword() != password.hashCode()){
                error("incorrect password");
            }
            else{
                user.setName(newName);
                error("name has been changed");
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
        newNameTextField.setText("");
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
