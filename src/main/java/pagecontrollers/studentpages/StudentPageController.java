package pagecontrollers.studentpages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import models.LoggedInUserHolder;
import models.User;
import models.student.Student;
import models.student.StudentLevel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pagecontrollers.LoggedInPageController;

import java.io.IOException;

public class StudentPageController extends LoggedInPageController {
    private static final Logger log = LogManager.getLogger(StudentPageController.class);

    @FXML
    protected MenuItem coursesListMenuItem;

    @FXML
    protected MenuItem professorsListMenuItem;

    @FXML
    protected MenuItem weeklyScheduleMenuItem;

    @FXML
    protected MenuItem examListMenuItem;

    @FXML
    protected MenuItem recommendationLetterRequestMenuItem;

    @FXML
    protected MenuItem minorRequestMenuItem;

    @FXML
    protected MenuItem dormRequestMenuItem;

    @FXML
    protected MenuItem dissertationDefenseRequestMenuItem;

    @FXML
    protected MenuItem certificateStudentRequestMenuItem;

    @FXML
    protected MenuItem withdrawalRequestMenuItem;

    @FXML
    protected MenuItem educationStatusMenuItem;

    @FXML
    protected MenuItem viewProfileMenuItem;

    @FXML
    protected MenuItem editPasswordMenuItem;

    @FXML
    protected MenuItem editProfileMenuItem;

    @FXML
    protected MenuItem temporaryScoresMenuItem;

    @FXML
    protected MenuItem homeMenuItem;


    @Override
    public void initialize(){
        super.initialize();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            StudentLevel studentLevel = student.getStudentLevel();
            certificateStudentRequestMenuItem.setVisible(studentLevel != StudentLevel.PHD_STUDENT);
            minorRequestMenuItem.setVisible(studentLevel == StudentLevel.UNDERGRADUATE);
            dormRequestMenuItem.setVisible(studentLevel == StudentLevel.MASTERS_STUDENT);
            dissertationDefenseRequestMenuItem.setVisible(studentLevel == StudentLevel.PHD_STUDENT);
        }
        else{
            log.error("logged in user is not a student");
            throw new IllegalStateException("logged in user is not a student");
        }
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
    protected void goToStudentMinorRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentMinorRequestPage.fxml");
    }

    @FXML
    protected void goToStudentDormRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentDormRequestPage.fxml");
    }

    @FXML
    protected void goToStudentDissertationDefenseRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentDissertationDefenseRequestPage.fxml");
    }


    @FXML
    protected void goToStudentWithdrawalRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("studentWithdrawalRequestPage.fxml");
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
}
