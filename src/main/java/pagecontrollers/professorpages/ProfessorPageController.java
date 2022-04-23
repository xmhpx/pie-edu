package pagecontrollers.professorpages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import logic.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.professor.ProfessorType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pagecontrollers.LoggedInPageController;

import java.io.IOException;

public class ProfessorPageController extends LoggedInPageController {
    private static final Logger log = LogManager.getLogger(ProfessorPageController.class);

    @FXML
    protected MenuItem coursesListMenuItem;

    @FXML
    protected MenuItem addUserMenuItem;

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
        if(user instanceof Professor professor){
            minorRequestMenuItem.setVisible(professor.getProfessorType() == ProfessorType.EDUCATIONAL_ASSISTANT);
            educationStatusMenuItem.setVisible(professor.getProfessorType() == ProfessorType.EDUCATIONAL_ASSISTANT);
            addUserMenuItem.setVisible(professor.getProfessorType() == ProfessorType.EDUCATIONAL_ASSISTANT);
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }
    }


    @FXML
    protected void goToProfessorHomePage(ActionEvent actionEvent) {
        goToProfessorPage("professorHomePage.fxml");
    }


    @FXML
    protected void goToProfessorCoursesListPage(ActionEvent actionEvent) {
        goToProfessorPage("professorCoursesListPage.fxml");
    }


    @FXML
    protected void goToAddUserPage(ActionEvent actionEvent) {
        goToProfessorPage("professorAddUserPage.fxml");
    }


    @FXML
    protected void goToProfessorProfessorsListPage(ActionEvent actionEvent) {
        goToProfessorPage("professorProfessorsListPage.fxml");
    }


    @FXML
    protected void goToProfessorExamsListPage(ActionEvent actionEvent) {
        goToProfessorPage("professorExamsListPage.fxml");
    }


    @FXML
    protected void goToProfessorRecommendationLetterRequestPage(ActionEvent actionEvent) {
        goToProfessorPage("professorRecommendationLetterRequestPage.fxml");
    }


    @FXML
    protected void goToProfessorMinorRequestPage(ActionEvent actionEvent) {
        goToProfessorPage("professorMinorRequestPage.fxml");
    }


    @FXML
    protected void goToProfessorWithdrawalRequestPage(ActionEvent actionEvent) {
        goToProfessorPage("professorWithdrawalRequestPage.fxml");
    }


    @FXML
    protected void goToProfessorTemporaryScoresPage(ActionEvent actionEvent) {
        goToProfessorPage("professorTemporaryScoresPage.fxml");
    }


    @FXML
    protected void goToProfessorEducationStatusPage(ActionEvent actionEvent) {
        goToProfessorPage("professorEducationStatusPage.fxml");
    }


    @FXML
    protected void goToProfessorViewProfilePage(ActionEvent actionEvent) {
        goToProfessorPage("professorViewProfilePage.fxml");
    }


    @FXML
    protected void goToProfessorEditPasswordPage(ActionEvent actionEvent) {
        goToProfessorPage("professorEditPasswordPage.fxml");
    }


    @FXML
    protected void goToProfessorEditProfilePage(ActionEvent actionEvent) {
        goToProfessorPage("professorEditProfilePage.fxml");
    }
}
