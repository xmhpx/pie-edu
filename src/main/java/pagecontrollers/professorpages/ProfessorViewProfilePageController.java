package pagecontrollers.professorpages;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.student.Student;
import models.universityitems.College;


public class ProfessorViewProfilePageController extends ProfessorPageController {

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
            String collegeName = "None";
            if(college != null){
                collegeName = college.getName();
            }
            collegeText.setText("College : " + collegeName);

            roomNumberText.setText("Room Number : " + professor.getRoomNumber());

            professorLevelText.setText("Professor Level : " + professor.getProfessorLevel());
        }
    }
}
