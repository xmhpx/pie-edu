import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;

public class StudentHomePageController {

    @FXML
    MenuItem coursesListMenuItem;

    @FXML
    MenuItem professorsListMenuItem;

    @FXML
    MenuItem weeklyScheduleMenuItem;

    @FXML
    MenuItem examListMenuItem;

    @FXML
    MenuItem recommendationLetterRequestMenuItem;

    @FXML
    MenuItem minorMenuItem;

    @FXML
    MenuItem certificateStudentRequestMenuItem;

    @FXML
    MenuItem withdrawalMenuItem;

    @FXML
    MenuItem studentsEducationStatusMenuItem;

    @FXML
    MenuItem viewProfileMenuItem;

    @FXML
    MenuItem editPasswordMenuItem;

    @FXML
    MenuItem editProfileMenuItem;

    @FXML
    MenuItem temporaryScoresMenuItem;

    @FXML
    MenuItem homeMenuItem;


    @FXML
    Text currentTimeText;

    @FXML
    Text lastTimeLoggedInText;

    @FXML
    AnchorPane anchorPane;

    @FXML
    public void initialize() {

//        lastTimeLoggedInText.setText("Last Time Logged In : " + student.getLastVisit());

        Thread clock = new Thread() {
            public void run() {
                for (int i = 1; i < 10; i++) {
                    Calendar cal = Calendar.getInstance();

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    //System.out.println(hour + ":" + (minute) + ":" + second);
                    currentTimeText.setText("Current Time : " + hour + ":" + (minute) + ":" + second);

                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        //...
                    }
                }
            }
        };

        clock.start();
    }


    void changeScene(Scene scene){
        Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentpages/studentHomePage.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        changeScene(scene);
    }
}
