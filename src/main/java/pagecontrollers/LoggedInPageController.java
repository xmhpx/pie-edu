package pagecontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.LoggedInUserHolder;
import models.User;

import java.io.IOException;
import java.util.Calendar;

public class LoggedInPageController extends BasicPageController {


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

//        UIProfileImageView.setImage(new Image(user.getProfileImagePath()));
        UINameText.setText(user.getName());
        UIEmailText.setText(user.getEmail());
        lastTimeLoggedInText.setText("Last Time Logged In : " + user.getLastVisit());

        Thread clock = new Thread() {
            public void run() {
                for (int i = 1; i < 5; i++) {
                    Calendar cal = Calendar.getInstance();

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    //System.out.println(hour + ":" + (minute) + ":" + second);
                    currentTimeText.setText("Current Time : " + hour + ":" + (minute) + ":" + second);

                    try {
                        sleep(990);
                    } catch (InterruptedException ex) {
                        //...
                    }
                }
                currentTimeText.setText("Reload To See Current Time");
            }
        };

        clock.start();
    }


    @FXML
    protected void logOutButtonClick(MouseEvent mouseEvent) throws IOException {
        LoggedInUserHolder.logout();
        goToPage("/loginPage.fxml");
    }
}
