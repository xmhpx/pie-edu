package pagecontrollers.professorpages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import logic.LoggedInUserHolder;
import models.student.Student;
import models.student.StudentLevel;
import pagecontrollers.LoggedInPageController;

import java.io.IOException;

public class ProfessorPageController extends LoggedInPageController {

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
        Student student = (Student) LoggedInUserHolder.getUser();
        StudentLevel studentLevel = student.getStudentLevel();
        certificateStudentRequestMenuItem.setVisible(studentLevel != StudentLevel.PHD_STUDENT);
        minorRequestMenuItem.setVisible(studentLevel == StudentLevel.UNDERGRADUATE);
        dormRequestMenuItem.setVisible(studentLevel == StudentLevel.MASTERS_STUDENT);
        dissertationDefenseRequestMenuItem.setVisible(studentLevel == StudentLevel.PHD_STUDENT);
    }


    @FXML
    protected void goToStudentHomePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorHomePage.fxml");
    }


    @FXML
    protected void goToStudentCoursesListPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorCoursesListPage.fxml");
    }


    @FXML
    protected void goToStudentProfessorsListPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorProfessorsListPage.fxml");
    }


    @FXML
    protected void goToStudentWeeklySchedulePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorWeeklySchedulePage.fxml");
    }


    @FXML
    protected void goToStudentExamsListPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorExamsListPage.fxml");
    }


    @FXML
    protected void goToStudentRecommendationLetterRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorRecommendationLetterRequestPage.fxml");
    }


    @FXML
    protected void goToStudentCertificateStudentRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorCertificateStudentRequestPage.fxml");
    }


    @FXML
    protected void goToStudentMinorRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorMinorRequestPage.fxml");
    }

    @FXML
    protected void goToStudentDormRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorDormRequestPage.fxml");
    }

    @FXML
    protected void goToStudentDissertationDefenseRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorDissertationDefenseRequestPage.fxml");
    }


    @FXML
    protected void goToStudentWithdrawalRequestPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorWithdrawalRequestPage.fxml");
    }


    @FXML
    protected void goToStudentTemporaryScoresPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorTemporaryScoresPage.fxml");
    }


    @FXML
    protected void goToStudentEducationStatusPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorEducationStatusPage.fxml");
    }


    @FXML
    protected void goToStudentViewProfilePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorViewProfilePage.fxml");
    }


    @FXML
    protected void goToStudentEditPasswordPage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorEditPasswordPage.fxml");
    }


    @FXML
    protected void goToStudentEditProfilePage(ActionEvent actionEvent) throws IOException {
        goToStudentPage("professorEditProfilePage.fxml");
    }
}
