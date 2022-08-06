package pagecontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.LoggedInUserHolder;
import models.UITheme;
import models.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Calendar;

public class LoggedInPageController extends BasicPageController {
    private static final Logger log = LogManager.getLogger(LoggedInPageController.class);


    @FXML
    protected Text currentTimeText;

    @FXML
    protected Text lastTimeLoggedInText;

    @FXML
    protected Button logOutButton;

    @FXML
    protected ImageView UIProfileImageView;

    @FXML
    protected Text UINameText;

    @FXML
    protected Text UIEmailText;


    @FXML
    @Override
    public void initialize() {
        User user = LoggedInUserHolder.getUser();
        UITheme preferredUITheme = user.getPreferredUITheme();
        if(preferredUITheme == null){
            log.error("user("+user.getId()+") has null preferredUITheme");
            throw new IllegalStateException("user("+user.getId()+") has null preferredUITheme");
        }
        anchorPane.setStyle("-fx-background-color:"+preferredUITheme.getColor());

        try{
            UIProfileImageView.setImage(new Image(user.getProfileImagePath()));
        }
        catch (Exception exception){
            log.error("user("+user.getId()+")'s profile image file is missing");
            throw new IllegalStateException("user("+user.getId()+")'s profile image file is missing");
        }

        UINameText.setText(user.getName());
        UIEmailText.setText(user.getEmail());
        lastTimeLoggedInText.setText("Last Time Logged In: " + user.getLastVisit());

        Thread clock = new Thread() {
            public void run() {
                for (int i = 1; i < 5; i++) {
                    Calendar cal = Calendar.getInstance();

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    currentTimeText.setText("Current Time: " + hour + ":" + (minute) + ":" + second);

                    try {
                        sleep(990);
                    } catch (InterruptedException ignored) {
                        log.warn("thread for updating current time has been interrupted");
                    }
                }
                currentTimeText.setText("Reload To See Current Time");
            }
        };

        clock.start();
    }


    @FXML
    protected void logOutButtonClick(MouseEvent mouseEvent) {
        LoggedInUserHolder.logout();
        goToLoginPage();
    }
}
