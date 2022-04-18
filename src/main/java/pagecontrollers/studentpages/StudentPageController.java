package pagecontrollers.studentpages;

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

public class StudentPageController {

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
    MenuItem educationStatusMenuItem;

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

    void goToStudentPage(String str) throws IOException {
        goToPage("studentpages/"+str);
    }


    void goToPage(String str) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        changeScene(scene);
    }


    void changeScene(Scene scene){
        Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    void goToStudentHomePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentHomePage.fxml");
    }


    @FXML
    void goToStudentCoursesListPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentCoursesListPage.fxml");
    }


    @FXML
    void goToStudentProfessorsListPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentProfessorsListPage.fxml");
    }


    @FXML
    void goToStudentWeeklySchedulePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentWeeklySchedulePage.fxml");
    }


    @FXML
    void goToStudentExamsListPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentExamsListPage.fxml");
    }


    @FXML
    void goToStudentRecommendationLetterRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentRecommendationLetterRequestPage.fxml");
    }


    @FXML
    void goToStudentCertificateStudentRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentCertificateStudentRequestPage.fxml");
    }


    @FXML
    void goToStudentMinorPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentMinorPage.fxml");
    }


    @FXML
    void goToStudentWithdrawalPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentWithdrawalPage.fxml");
    }


    @FXML
    void goToStudentTemporaryScoresPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentTemporaryScoresPage.fxml");
    }


    @FXML
    void goToStudentEducationStatusPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentEducationStatusPage.fxml");
    }


    @FXML
    void goToStudentViewProfilePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentViewProfilePage.fxml");
    }


    @FXML
    void goToStudentEditPasswordPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentEditPasswordPage.fxml");
    }


    @FXML
    void goToStudentEditProfilePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentEditProfilePage.fxml");
    }
}
