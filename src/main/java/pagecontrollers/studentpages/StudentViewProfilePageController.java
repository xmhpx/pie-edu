package pagecontrollers.studentpages;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import server.logic.Backend;
import models.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.student.Student;
import models.universityitems.College;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class StudentViewProfilePageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentViewProfilePageController.class);

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
            nameText.setText("Name: " + student.getName());
            nationalIdNumberText.setText("National Id Number: " + student.getNationalIdNumber());
            phoneNumberText.setText("Phone Number: " + student.getPhoneNumber());
            studentNumberText.setText("Student Number: " + student.getStudentNumber());
            emailText.setText("Email: " + student.getEmail());
            averageScoreText.setText("Average Score: " + student.getAverage());

            College college = backend.getCollege(student.getCollegeId());
            if(college == null) {
                log.error("student("+student.getId()+")'s college("+student.getCollegeId()+") doesn't exist");
                throw new IllegalStateException("student("+student.getId()+")'s college("+student.getCollegeId()+") doesn't exist");
            }
            String collegeName = college.getName();

            collegeText.setText("College: " + collegeName);

            Professor professor = backend.getProfessor(student.getSupervisorId());
            String professorName = "None";
            if(professor != null){
                professorName = professor.getName();
            }
            supervisorText.setText("Professor: " + professorName);
            yearOfEntranceText.setText("Year Of Entrance: " + student.getYearOfEntry());
            studentLevelText.setText("Student Level: " + student.getStudentLevel());
            educationStatusText.setText("Education Status: " + student.getEducationStatus());
        }
        else{
            log.error("logged in user is not a student");
            throw new IllegalStateException("logged in user is not a student");
        }
    }
}
