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
import models.universityitems.Field;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;


public class ProfessorEditProfessorsPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorEditProfessorsPageController.class);

    public static final String fxmlFileName = "professorEditProfessorsPage.fxml";

    private Integer loadedProfessorId = null;

    @FXML
    TableView<Professor> tableView;

    @FXML
    TextField professorIdRemoveTextField;

    @FXML
    Button removeProfessorButton;

    @FXML
    Text errorEditText;

    @FXML
    TextField professorIdLoadTextField;

    @FXML
    Button loadButton;

    @FXML
    TextField ageEditTextField;

    @FXML
    TextField passwordEditTextField;

    @FXML
    TextField nationalIdNumberEditTextField;

    @FXML
    TextField fieldIdEditTextField;

    @FXML
    TextField professorNumberEditTextField;

    @FXML
    TextField professorLevelEditTextField;

    @FXML
    TextField professorTypeEditTextField;

    @FXML
    TextField roomNumberEditTextField;

    @FXML
    Button editProfessorButton;


    @FXML
    TextField professorNameTextField;

    @FXML
    TextField fieldIdTextField;

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
    void removeProfessorButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();

        String professorIdString = professorIdRemoveTextField.getText();

        try{
            if(professorIdString.length() > 0) Integer.parseInt(professorIdString);
        }
        catch (NumberFormatException numberFormatException){
            errorEdit("professor id must be an integer");
            return;
        }
        int professorId = Integer.parseInt(professorIdString);
        if(!backend.hasProfessor(professorId)){
            errorEdit("professor doesn't exist");
            return;
        }


        Professor professor = Backend.getInstance().getProfessor(professorId);

        if(LoggedInUserHolder.getUser() instanceof Professor dean) {
            if(dean.getProfessorType() != ProfessorType.DEAN_OF_THE_FACULTY){
                log.error("logged in user is not DEAN_OF_THE_FACULTY");
                throw new IllegalStateException("logged in user is not DEAN_OF_THE_FACULTY");
            }
            if(professor.getCollegeId() != dean.getCollegeId()){
                errorEdit("you can't remove professors from other colleges");
                return;
            }
            if(professor.getId() == LoggedInUserHolder.getUser().getId()){
                errorEdit("you can't remove yourself");
                return;
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }


        Backend.getInstance().removeFromProfessors(professorId);
        initialize();
        clean();

        errorEdit("professor("+professorId+") has been removed");
    }

    @FXML
    void loadButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();

        String professorIdString = professorIdLoadTextField.getText();

        try{
            if(professorIdString.length() > 0) Integer.parseInt(professorIdString);
        }
        catch (NumberFormatException numberFormatException){
            errorEdit("professor id must be an integer");
            return;
        }
        int professorId = Integer.parseInt(professorIdString);
        if(!backend.hasProfessor(professorId)){
            errorEdit("professor doesn't exist");
            return;
        }

        Professor professor = Backend.getInstance().getProfessor(professorId);

        if(LoggedInUserHolder.getUser() instanceof Professor dean) {
            if(dean.getProfessorType() != ProfessorType.DEAN_OF_THE_FACULTY){
                log.error("logged in user is not DEAN_OF_THE_FACULTY");
                throw new IllegalStateException("logged in user is not DEAN_OF_THE_FACULTY");
            }
            if(professor.getCollegeId() != dean.getCollegeId()){
                errorEdit("you can't edit professors from other colleges");
                return;
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }

        loadEditTextFields(professor);
    }

    @FXML
    void editProfessorButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();

        String professorIdString = professorIdLoadTextField.getText();

        try{
            if(professorIdString.length() > 0) Integer.parseInt(professorIdString);
        }
        catch (NumberFormatException numberFormatException){
            errorEdit("professor id must be an integer");
            return;
        }
        int professorId = Integer.parseInt(professorIdString);
        if(!backend.hasProfessor(professorId)){
            errorEdit("professor doesn't exist");
            return;
        }

        Professor professor = Backend.getInstance().getProfessor(professorId);

        if(LoggedInUserHolder.getUser() instanceof Professor dean) {
            if(dean.getProfessorType() != ProfessorType.DEAN_OF_THE_FACULTY){
                log.error("logged in user is not DEAN_OF_THE_FACULTY");
                throw new IllegalStateException("logged in user is not DEAN_OF_THE_FACULTY");
            }
            if(professor.getCollegeId() != dean.getCollegeId()){
                errorEdit("you can't edit professors from other colleges");
                return;
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }

        saveEditTextFields(professor);
    }


    private void loadEditTextFields(Professor professor){
        ageEditTextField.setText(String.valueOf(professor.getAge()));
        passwordEditTextField.setText("");
        nationalIdNumberEditTextField.setText(professor.getNationalIdNumber());
        fieldIdEditTextField.setText(String.valueOf(professor.getFieldId()));
        professorNumberEditTextField.setText(professor.getProfessorNumber());
        professorLevelEditTextField.setText(String.valueOf(professor.getProfessorLevel()));
        professorTypeEditTextField.setText(String.valueOf(professor.getProfessorType()));
        roomNumberEditTextField.setText(professor.getRoomNumber());
        loadedProfessorId = professor.getId();
        errorEdit("professor("+professor.getId()+") has been loaded");
    }

    private void saveEditTextFields(Professor professor){
        if(loadedProfessorId != professor.getId()){
            errorEdit("load professor first using load button");
            return;
        }
        professor.setAge(Integer.parseInt(ageEditTextField.getText()));
        professor.setPassword(passwordEditTextField.getText());
        professor.setNationalIdNumber(nationalIdNumberEditTextField.getText());
        professor.setFieldId(Integer.parseInt(fieldIdEditTextField.getText()));
        professor.setProfessorNumber(professorNumberEditTextField.getText());
        professor.setProfessorLevel(ProfessorLevel.valueOf(professorLevelEditTextField.getText()));
        if(professor.getId() == LoggedInUserHolder.getUser().getId()){
            professorTypeEditTextField.setText(String.valueOf(ProfessorType.DEAN_OF_THE_FACULTY));
        }
        professor.setProfessorType(ProfessorType.valueOf(professorTypeEditTextField.getText()));
        professor.setRoomNumber(roomNumberEditTextField.getText());
        errorEdit("professor("+professor.getId()+") has been edited");
    }


    @FXML
    void filterButtonOnAction(ActionEvent actionEvent){
        String professorName = professorNameTextField.getText();
        String collegeIdString = fieldIdTextField.getText();
        String professorLevelString = professorLevelTextField.getText();
        initialize(professorName, collegeIdString, professorLevelString);
    }


    private void error(String error){
        errorText.setText(error);
    }

    private void errorEdit(String error){
        errorEditText.setText(error);
    }

    private void clean(){
        errorText.setText("");
        professorNameTextField.setText("");
        fieldIdTextField.setText("");
        professorLevelTextField.setText("");
    }
}
