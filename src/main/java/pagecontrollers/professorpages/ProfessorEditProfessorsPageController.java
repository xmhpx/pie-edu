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
import models.universityitems.Field;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;


public class ProfessorEditProfessorsPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorEditProfessorsPageController.class);

    public static final String fxmlFileName = "professorEditProfessorsPage.fxml";

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
        initialize("", "", "");
    }

    public void initialize(String professorName, String fieldIdString, String professorLevelString){

        try{
            if(fieldIdString.length() > 0) Integer.parseInt(fieldIdString);
        }
        catch (NumberFormatException numberFormatException){
            error("field id must be an integer");
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
        if(user instanceof Professor dean) {
            if(dean.getProfessorType() != ProfessorType.DEAN_OF_THE_FACULTY){
                log.error("logged in user is not DEAN_OF_THE_FACULTY");
                throw new IllegalStateException("logged in user is not DEAN_OF_THE_FACULTY");
            }
            for(Professor professor : backend.getProfessors()) {
                Field field = backend.getField(professor.getFieldId());
                if(field == null){
                    log.error("professor("+professor.getId()+")'s field doesn't exist");
                    throw new IllegalStateException("professor("+professor.getId()+")'s field doesn't exist");
                }
                if((dean.getCollegeId() == professor.getCollegeId()) &&
                        (professorName.equals(professor.getName()) || professorName.equals("")) &&
                        (fieldIdString.equals(String.valueOf(field.getId())) || fieldIdString.equals("")) &&
                        (professorLevelString.equals("") || ProfessorLevel.valueOf(professorLevelString) == professor.getProfessorLevel())){
                    data.add(professor);
                }
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }

        if(professorName.equals("") &&
                fieldIdString.equals("") &&
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
