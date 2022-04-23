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
import models.professor.ProfessorLevel;
import models.student.Student;
import models.universityitems.College;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;


public class StudentProfessorsListPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentProfessorsListPageController.class);

    public static final String fxmlFileName = "studentProfessorsListPage.fxml";

    @FXML
    TableView<Professor> tableView;

    @FXML
    TextField professorNameTextField;

    @FXML
    TextField collegeIdTextField;

    @FXML
    TextField professorLevelTextField;

    @FXML
    Button filterButton;

    @FXML
    Text errorText;


    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<Professor> data = tableView.getItems();
        data.clear();
        data.addAll(backend.getProfessors());
    }

    public void initialize(String professorName, String collegeIdString, String professorLevelString){

        try{
            if(collegeIdString.length() > 0) Integer.parseInt(collegeIdString);
        }
        catch (NumberFormatException numberFormatException){
            error("college id must be an integer");
            return;
        }

        try{
            if(professorLevelString.length() > 0) ProfessorLevel.valueOf(professorLevelString);
        }
        catch (IllegalArgumentException illegalArgumentException){
            error("professor level must be " + Arrays.toString(ProfessorLevel.values()));
            return;
        }

        super.initialize();

        Backend backend = Backend.getInstance();
        ObservableList<Professor> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student) {
            for(Professor professor : backend.getProfessors()) {
                College college = backend.getCollege(professor.getCollegeId());
                if(college == null){
                    log.error("professor("+professor.getId()+")'s college doesn't exist");
                    throw new IllegalStateException("professor("+professor.getId()+")'s college doesn't exist");
                }
                if((professorName.equals(professor.getName()) || professorName.equals("")) &&
                        (collegeIdString.equals(String.valueOf(college.getId())) || collegeIdString.equals("")) &&
                        (professorLevelString.equals("") || ProfessorLevel.valueOf(professorLevelString) == professor.getProfessorLevel())){
                    data.add(professor);
                }
            }
        }
        else{
            log.error("logged in user is not a Student");
            throw new IllegalStateException("logged in user is not a Student");
        }

        if(professorName.equals("") &&
                collegeIdString.equals("") &&
                professorLevelString.equals("")){
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
        String professorLevelString = professorLevelTextField.getText();
        initialize(professorName, collegeIdString, professorLevelString);
    }


    private void error(String error){
        errorText.setText(error);
    }

    private void clean(){
        errorText.setText("");
        professorNameTextField.setText("");
        collegeIdTextField.setText("");
        professorLevelTextField.setText("");
    }
}
