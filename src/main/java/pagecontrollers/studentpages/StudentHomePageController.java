package pagecontrollers.studentpages;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import logic.Backend;
import logic.LoggedInUserHolder;
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
}
