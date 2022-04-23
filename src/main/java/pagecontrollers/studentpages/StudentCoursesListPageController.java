package pagecontrollers.studentpages;

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
import models.student.Student;
import models.universityitems.College;
import models.universityitems.Course;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentCoursesListPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentCoursesListPageController.class);

    public static final String fxmlFileName = "studentCoursesListPage.fxml";

    @FXML
    TableView<Course> tableView;

    @FXML
    TextField professorNameTextField;

    @FXML
    TextField collegeIdTextField;

    @FXML
    TextField courseNumberTextField;

    @FXML
    Button filterButton;

    @FXML
    Text errorText;


    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<Course> data = tableView.getItems();
        data.clear();
        data.addAll(backend.getCourses());
    }

    public void initialize(String professorName, String collegeIdString, String courseNumberString){

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
        if(user instanceof Student) {
            for(Course course : backend.getCourses()) {
                Professor professor = backend.getProfessor(course.getProfessorId());
                if(professor == null){
                    log.error("course("+course.getId()+")'s professor doesn't exist");
                    throw new IllegalStateException("course("+course.getId()+")'s professor doesn't exist");
                }
                College college = backend.getCollege(course.getCollegeId());
                if(college == null){
                    log.error("course("+course.getId()+")'s college doesn't exist");
                    throw new IllegalStateException("course("+course.getId()+")'s college doesn't exist");
                }
                if((professorName.equals(professor.getName()) || professorName.equals("")) &&
                        (collegeIdString.equals(String.valueOf(college.getId())) || collegeIdString.equals("")) &&
                        (courseNumberString.equals(course.getCourseNumber()) || courseNumberString.equals(""))){
                    data.add(course);
                }
            }
        }
        else{
            log.error("logged in user is not a Student");
            throw new IllegalStateException("logged in user is not a Student");
        }

        if(professorName.equals("") &&
                collegeIdString.equals("") &&
                courseNumberString.equals("")){
            error("");
        }
        else {
            error("filter applied");
        }
    }


    @FXML
    void filterButtonOnAction(ActionEvent actionEvent){
        String professorName = professorNameTextField.getText();
        String collegeIdString = collegeIdTextField.getText();
        String courseNumber = courseNumberTextField.getText();
        initialize(professorName, collegeIdString, courseNumber);
    }


    private void error(String error){
        errorText.setText(error);
    }

    private void clean(){
        errorText.setText("");
        professorNameTextField.setText("");
        collegeIdTextField.setText("");
        courseNumberTextField.setText("");
    }
}
