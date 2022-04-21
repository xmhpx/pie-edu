package pagecontrollers.studentpages;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.student.Student;
import models.universityitems.College;


public class StudentViewProfilePageController extends StudentPageController {

    public static final String fxmlFileName = "studentViewProfilePage.fxml";

    @FXML
    Text nameText;

    @FXML
    Text nationalIdNumberText;

    @FXML
    Text phoneNumberText;

    @FXML
    Text studentNumberText;

    @FXML
    Text emailText;

    @FXML
    Text averageScoreText;

    @FXML
    Text collegeText;

    @FXML
    Text supervisorText;

    @FXML
    Text yearOfEntranceText;

    @FXML
    Text studentLevelText;

    @FXML
    Text educationStatusText;


    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            nameText.setText("Name : " + student.getName());

            nationalIdNumberText.setText("National Id Number : " + student.getNationalIdNumber());

            phoneNumberText.setText("Phone Number : " + student.getPhoneNumber());

            studentNumberText.setText("Student Number : " + student.getStudentNumber());

            emailText.setText("Email : " + student.getEmail());

            averageScoreText.setText("Average Score : " + student.getAverage());

            College college = backend.getCollege(student.getCollegeId());
            String collegeName = "None";
            if(college != null){
                collegeName = college.getName();
            }
            collegeText.setText("College : " + collegeName);

            Professor professor = backend.getProfessor(student.getSupervisorId());
            String professorName = "None";
            if(professor != null){
                professorName = professor.getName();
            }
            supervisorText.setText("Professor : " + professorName);

            yearOfEntranceText.setText("Year Of Entrance : " + student.getYearOfEntry());

            studentLevelText.setText("Student Level : " + student.getStudentLevel());

            educationStatusText.setText("Education Status : " + student.getEducationStatus());
        }
    }
}
