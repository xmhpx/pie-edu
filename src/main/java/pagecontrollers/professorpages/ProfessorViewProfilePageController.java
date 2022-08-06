package pagecontrollers.professorpages;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import server.logic.Backend;
import models.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.universityitems.College;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class ProfessorViewProfilePageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorViewProfilePageController.class);

    public static final String fxmlFileName = "professorViewProfilePage.fxml";

    @FXML
    Text nameText;

    @FXML
    Text nationalIdNumberText;

    @FXML
    Text phoneNumberText;

    @FXML
    Text professorNumberText;

    @FXML
    Text emailText;

    @FXML
    Text collegeText;

    @FXML
    Text professorLevelText;

    @FXML
    Text roomNumberText;


    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor professor) {
            nameText.setText("Name : " + professor.getName());
            nationalIdNumberText.setText("National Id Number : " + professor.getNationalIdNumber());
            phoneNumberText.setText("Phone Number : " + professor.getPhoneNumber());
            professorNumberText.setText("Professor Number : " + professor.getProfessorNumber());
            emailText.setText("Email : " + professor.getEmail());

            College college = backend.getCollege(professor.getCollegeId());
            if(college == null){
                log.warn("professor("+professor.getId()+")'s college doesn't exist");
                throw new IllegalStateException("professor("+professor.getId()+")'s college doesn't exist");
            }
            String collegeName = college.getName();

            collegeText.setText("College : " + collegeName);
            roomNumberText.setText("Room Number : " + professor.getRoomNumber());
            professorLevelText.setText("Professor Level : " + professor.getProfessorLevel());
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }
    }
}
