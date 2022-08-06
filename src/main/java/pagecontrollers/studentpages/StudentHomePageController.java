package pagecontrollers.studentpages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import server.logic.Backend;
import models.LoggedInUserHolder;
import models.UITheme;
import models.User;
import models.student.Student;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentHomePageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentHomePageController.class);

    public static final String fxmlFileName = "studentHomePage.fxml";


    @FXML
    Text educationStatusText;

    @FXML
    Text supervisorText;

    @FXML
    Text registrationLicenseText;

    @FXML
    Text registrationTimeText;


    @FXML
    Button skyBlueButton;

    @FXML
    Button redButton;

    @FXML
    Button darkButton;

    @FXML
    Button purpleButton;



    @Override
    public void initialize(){
        super.initialize();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            educationStatusText.setText("Education Status: " + student.getEducationStatus());

            int supervisorId = student.getSupervisorId();
            if(Backend.getInstance().hasProfessor(supervisorId)){
                supervisorText.setText("Supervisor Name: " + Backend.getInstance().getProfessor(supervisorId).getName());
            }
            else{
                supervisorText.setText("Supervisor Name: N/A");
            }

            registrationLicenseText.setText("Registration License: " + student.getRegistrationLicense());
            registrationTimeText.setText("Registration Time: " + student.getRegistrationTime());
        }
        else{
            log.error("logged in user is not a student");
            throw new IllegalStateException("logged in user is not a student");
        }
    }


    @FXML
    void skyBlueButtonOnAction(){
        User user = LoggedInUserHolder.getUser();
        UITheme preferredUITheme = user.getPreferredUITheme();
        if(preferredUITheme == null){
            log.error("user("+user.getId()+") has null preferredUITheme");
            throw new IllegalStateException("user("+user.getId()+") has null preferredUITheme");
        }
        preferredUITheme.changeColorToSkyBlue();
        initialize();
    }

    @FXML
    void redButtonOnAction(){
        User user = LoggedInUserHolder.getUser();
        UITheme preferredUITheme = user.getPreferredUITheme();
        if(preferredUITheme == null){
            log.error("user("+user.getId()+") has null preferredUITheme");
            throw new IllegalStateException("user("+user.getId()+") has null preferredUITheme");
        }
        preferredUITheme.changeColorToRed();
        initialize();
    }

    @FXML
    void darkButtonOnAction(){
        User user = LoggedInUserHolder.getUser();
        UITheme preferredUITheme = user.getPreferredUITheme();
        if(preferredUITheme == null){
            log.error("user("+user.getId()+") has null preferredUITheme");
            throw new IllegalStateException("user("+user.getId()+") has null preferredUITheme");
        }
        preferredUITheme.changeColorToDark();
        initialize();
    }

    @FXML
    void purpleButtonOnAction(){
        User user = LoggedInUserHolder.getUser();
        UITheme preferredUITheme = user.getPreferredUITheme();
        if(preferredUITheme == null){
            log.error("user("+user.getId()+") has null preferredUITheme");
            throw new IllegalStateException("user("+user.getId()+") has null preferredUITheme");
        }
        preferredUITheme.changeColorToPurple();
        initialize();
    }
}
