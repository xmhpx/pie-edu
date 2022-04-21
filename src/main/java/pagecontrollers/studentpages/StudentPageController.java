package pagecontrollers.studentpages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.LoggedInUserHolder;
import pagecontrollers.BasicPageController;

import java.io.IOException;
import java.util.Calendar;

public class StudentPageController extends BasicPageController {

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
    Button logOutButton;

    @FXML
    ImageView UIProfileImageView;

    @FXML
    Text UINameText;

    @FXML
    Text UIEmailText;


    @FXML
    public void initialize() {

//        lastTimeLoggedInText.setText("Last Time Logged In : " + student.getLastVisit());

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
    protected void goToStudentHomePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentHomePage.fxml");
    }


    @FXML
    protected void goToStudentCoursesListPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentCoursesListPage.fxml");
    }


    @FXML
    protected void goToStudentProfessorsListPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentProfessorsListPage.fxml");
    }


    @FXML
    protected void goToStudentWeeklySchedulePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentWeeklySchedulePage.fxml");
    }


    @FXML
    protected void goToStudentExamsListPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentExamsListPage.fxml");
    }


    @FXML
    protected void goToStudentRecommendationLetterRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentRecommendationLetterRequestPage.fxml");
    }


    @FXML
    protected void goToStudentCertificateStudentRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentCertificateStudentRequestPage.fxml");
    }


    @FXML
    protected void goToStudentMinorPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentMinorPage.fxml");
    }


    @FXML
    protected void goToStudentWithdrawalPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentWithdrawalPage.fxml");
    }


    @FXML
    protected void goToStudentTemporaryScoresPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentTemporaryScoresPage.fxml");
    }


    @FXML
    protected void goToStudentEducationStatusPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentEducationStatusPage.fxml");
    }


    @FXML
    protected void goToStudentViewProfilePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentViewProfilePage.fxml");
    }


    @FXML
    protected void goToStudentEditPasswordPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentEditPasswordPage.fxml");
    }


    @FXML
    protected void goToStudentEditProfilePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentEditProfilePage.fxml");
    }

    @FXML
    protected void logOutButtonClick(MouseEvent mouseEvent) throws IOException {
        LoggedInUserHolder.setUser(null);
        goToPage("/loginPage.fxml");
    }
}
