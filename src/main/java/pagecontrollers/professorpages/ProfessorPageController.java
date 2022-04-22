package pagecontrollers.professorpages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import logic.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.professor.ProfessorType;
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
        }
    }


    @FXML
    protected void goToProfessorHomePage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorHomePage.fxml");
    }


    @FXML
    protected void goToProfessorCoursesListPage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorCoursesListPage.fxml");
    }


    @FXML
    protected void goToProfessorProfessorsListPage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorProfessorsListPage.fxml");
    }


    @FXML
    protected void goToProfessorExamsListPage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorExamsListPage.fxml");
    }


    @FXML
    protected void goToProfessorRecommendationLetterRequestPage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorRecommendationLetterRequestPage.fxml");
    }


    @FXML
    protected void goToProfessorMinorRequestPage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorMinorRequestPage.fxml");
    }


    @FXML
    protected void goToProfessorWithdrawalRequestPage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorWithdrawalRequestPage.fxml");
    }


    @FXML
    protected void goToProfessorTemporaryScoresPage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorTemporaryScoresPage.fxml");
    }


    @FXML
    protected void goToProfessorEducationStatusPage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorEducationStatusPage.fxml");
    }


    @FXML
    protected void goToProfessorViewProfilePage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorViewProfilePage.fxml");
    }


    @FXML
    protected void goToProfessorEditPasswordPage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorEditPasswordPage.fxml");
    }


    @FXML
    protected void goToProfessorEditProfilePage(ActionEvent actionEvent) throws IOException {
        goToProfessorPage("professorEditProfilePage.fxml");
    }
}
