package pagecontrollers.professorpages;

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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProfessorEditProfileController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorEditProfileController.class);

    public static final String fxmlFileName = "professorEditProfile.fxml";

    protected Captcha captcha;
    protected Backend backend;

    @FXML
    Text errorText;

    @FXML
    TextField newNameTextField;

    @FXML
    TextField newEmailTextField;

    @FXML
    TextField newPhoneNumberTextField;

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
            String password = passwordTextField.getText();

            User user = LoggedInUserHolder.getUser();
            if(user.getHashedPassword() != password.hashCode()){
                error("incorrect password");
            }
            else {
                String errorMassage = "";

                String newName = newNameTextField.getText();
                if (newName.length() > 0) {
                    if (newName.length() < 5) {
                        error("new name is too short");
                    } else if (newName.length() > 30) {
                        error("new name is too long");
                    }
                    user.setName(newName);
                    errorMassage += "name has been changed\n";
                }

                String newEmail = newEmailTextField.getText();
                if (newEmail.length() > 0) {
                    user.setEmail(newEmail);
                    errorMassage += "email has been changed\n";
                }

                String newPhoneNumber = newPhoneNumberTextField.getText();
                if (newPhoneNumber.length() > 0) {
                    user.setPhoneNumber(newPhoneNumber);
                    errorMassage += "phone number has been changed\n";
                }

                error(errorMassage);
            }
        }
        else {
            error("incorrect captcha");
        }
    }

    private void error(String error){
        clean();
        super.initialize();
        errorText.setText(error);
    }

    private void clean(){
        errorText.setText("");
        newNameTextField.setText("");
        newEmailTextField.setText("");
        newPhoneNumberTextField.setText("");
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
