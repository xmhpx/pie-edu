package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.professor.ProfessorLevel;
import models.professor.ProfessorType;
import models.universityitems.College;
import models.universityitems.Course;
import models.universityitems.Field;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class ProfessorAddCoursePageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorAddCoursePageController.class);

    public static final String fxmlFileName = "professorAddCoursePage.fxml";

    @FXML
    TableView<Course> tableView;

    @FXML
    TextField addCourseNameAddTextField;

    @FXML
    TextField addCourseCollegeIdTextField;

    @FXML
    TextField addCourseProfessorIdTextField;

    @FXML
    TextField addHoldingSemesterTextField;

    @FXML
    TextField addCourseNumberTextField;

    @FXML
    TextField addSemesterCreditHoursTextField;

    @FXML
    TextField addExamDateTextField;

    @FXML
    Button addCourseButton;


    @FXML
    Text errorEditText;


    @FXML
    TextField courseNameTextField;

    @FXML
    TextField collegeIdTextField;

    @FXML
    TextField holdingSemesterTextField;

    @FXML
    Button filterButton;

    @FXML
    Text errorText;


    @Override
    public void initialize(){
        initialize("", "", "");
    }

    public void initialize(String courseName, String collegeIdString, String holdingSemester){

        try{
            if(collegeIdString.length() > 0) Integer.parseInt(collegeIdString);
        }
        catch (NumberFormatException numberFormatException){
            error("college id must be an integer");
            return;
        }

        super.initialize();

        Backend backend = Backend.getInstance();
        ObservableList<Course> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor eduAssistant) {
            if(eduAssistant.getProfessorType() != ProfessorType.EDUCATIONAL_ASSISTANT){
                log.error("logged in user is not EDUCATIONAL_ASSISTANT");
                throw new IllegalStateException("logged in user is not EDUCATIONAL_ASSISTANT");
            }
            for(Course course : backend.getCourses()) {
                College college = backend.getCollege(course.getCollegeId());
                if(college == null){
                    log.error("course("+course.getId()+")'s college doesn't exist");
                    throw new IllegalStateException("course("+course.getId()+")'s college doesn't exist");
                }
                if((courseName.equals(course.getName()) || courseName.equals("")) &&
                        (collegeIdString.equals(String.valueOf(college.getId())) || collegeIdString.equals("")) &&
                        (holdingSemester.equals(course.getHoldingSemester()) || holdingSemester.equals(""))){
                    data.add(course);
                }
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }

        if(courseName.equals("") &&
                collegeIdString.equals("") &&
                holdingSemester.equals("")){
            error("");
        }
        else {
            error("filter applied");
        }
    }



    @FXML
    protected void addCourseButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();

        String name = addCourseNameAddTextField.getText();

        String collegeIdString = addCourseCollegeIdTextField.getText();
        int collegeId;
        try {
            collegeId = Integer.parseInt(collegeIdString);
        }
        catch (NumberFormatException ignored){
            error("college id should be an integer");
            return;
        }
        if(!backend.hasCollege(collegeId)){
            error("college doesn't exist");
            return;
        }

        String professorIdString = addCourseProfessorIdTextField.getText();
        int professorId;
        try {
            professorId = Integer.parseInt(professorIdString);
        }
        catch (NumberFormatException ignored){
            error("professor id should be an integer");
            return;
        }
        if(!backend.hasProfessor(professorId)){
            error("professor doesn't exist");
            return;
        }

        String holdingSemester = addHoldingSemesterTextField.getText();

        String courseNumber = addCourseNumberTextField.getText();

        String semesterCreditHoursString = addSemesterCreditHoursTextField.getText();
        int semesterCreditHours;
        try {
            semesterCreditHours = Integer.parseInt(semesterCreditHoursString);
        }
        catch (NumberFormatException ignored){
            error("semester credit hours should be an integer");
            return;
        }

        String examDate = addExamDateTextField.getText();

        Course course = new Course(name, collegeId, professorId, holdingSemester, courseNumber, semesterCreditHours, examDate);
        backend.addToCourses(course);

        clearAddCourseTextFields();
        clean();
        initialize();
        error("course has been added");
    }


    private void clearAddCourseTextFields(){
        addCourseNameAddTextField.setText("");
        addCourseCollegeIdTextField.setText("");
        addCourseProfessorIdTextField.setText("");
        addHoldingSemesterTextField.setText("");
        addCourseNumberTextField.setText("");
        addSemesterCreditHoursTextField.setText("");
        addExamDateTextField.setText("");
    }


    @FXML
    void filterButtonOnAction(ActionEvent actionEvent){
        String courseName = courseNameTextField.getText();
        String collegeIdString = collegeIdTextField.getText();
        String holdingSemester = holdingSemesterTextField.getText();
        initialize(courseName, collegeIdString, holdingSemester);
    }


    private void error(String error){
        errorText.setText(error);
    }

    private void errorEdit(String error){
        errorEditText.setText(error);
    }

    private void clean(){
        errorText.setText("");
        courseNameTextField.setText("");
        collegeIdTextField.setText("");
        holdingSemesterTextField.setText("");
    }
}
