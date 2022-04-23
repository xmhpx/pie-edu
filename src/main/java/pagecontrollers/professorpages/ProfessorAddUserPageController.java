package pagecontrollers.professorpages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.professor.Professor;
import models.professor.ProfessorLevel;
import models.professor.ProfessorType;
import models.student.Student;
import models.student.StudentLevel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class ProfessorAddUserPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorAddUserPageController.class);

    public static final String fxmlFileName = "professorAddUserPage.fxml";



    @FXML
    protected TextField professorPasswordTextField;

    @FXML
    protected TextField professorNameTextField;

    @FXML
    protected TextField professorFieldIdTextField;

    @FXML
    protected TextField professorCollegeIdTextField;

    @FXML
    protected TextField professorNumberTextField;

    @FXML
    protected TextField professorLevelTextField;

    @FXML
    protected TextField professorTypeTextField;

    @FXML
    protected TextField professorAgeTextField;

    @FXML
    protected TextField professorNationalIdNumberTextField;

    @FXML
    protected Button addProfessorButton;



    @FXML
    protected TextField studentPasswordTextField;

    @FXML
    protected TextField studentNameTextField;

    @FXML
    protected TextField studentFieldIdTextField;

    @FXML
    protected TextField studentCollegeIdTextField;

    @FXML
    protected TextField studentNumberTextField;

    @FXML
    protected TextField studentLevelTextField;

    @FXML
    protected TextField studentYearOfEntryTextField;

    @FXML
    protected TextField studentAgeTextField;

    @FXML
    protected TextField studentNationalIdNumberTextField;

    @FXML
    protected Button addStudentButton;


    @FXML
    protected Text errorText;


    @Override
    public void initialize(){
        super.initialize();
    }


    @FXML
    protected void addProfessorButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();

        String password = professorPasswordTextField.getText();
        String name = professorNameTextField.getText();

        String fieldIdString = professorFieldIdTextField.getText();
        int fieldId;
        try {
            fieldId = Integer.parseInt(fieldIdString);
        }
        catch (NumberFormatException ignored){
            error("field should be an integer");
            return;
        }
        if(!backend.hasField(fieldId)){
            error("field doesn't exist");
            return;
        }

        String collegeIdString = professorCollegeIdTextField.getText();
        int collegeId;
        try {
            collegeId = Integer.parseInt(collegeIdString);
        }
        catch (NumberFormatException ignored){
            error("college should be an integer");
            return;
        }
        if(!backend.hasCollege(collegeId)){
            error("college doesn't exist");
            return;
        }

        String professorNumber = professorNumberTextField.getText();

        String professorLevelString = professorLevelTextField.getText();
        ProfessorLevel professorLevel;
        try {
            professorLevel = ProfessorLevel.valueOf(professorLevelString);
        }
        catch (IllegalArgumentException ignored) {
            error("professor level should be "+ Arrays.toString(ProfessorLevel.values()));
            return;
        }

        String professorTypeString = professorTypeTextField.getText();
        ProfessorType professorType;
        try {
            professorType = ProfessorType.valueOf(professorTypeString);
        }
        catch (IllegalArgumentException ignored){
            error("professor type should be "+ Arrays.toString(ProfessorType.values()));
            return;
        }

        String ageString = professorAgeTextField.getText();
        int age;
        try {
            age = Integer.parseInt(ageString);
        }
        catch (NumberFormatException ignored){
            error("age should be an integer");
            return;
        }

        String nationalIdNumber = professorNationalIdNumberTextField.getText();

        Professor professor = new Professor(password, name, fieldId, collegeId, professorNumber, professorLevel, professorType, age, nationalIdNumber);
        backend.addToProfessors(professor);

        clearProfessorTextFields();
        error("professor has been added");
    }


    private void clearProfessorTextFields(){
        professorPasswordTextField.setText("");
        professorNameTextField.setText("");
        professorFieldIdTextField.setText("");
        professorCollegeIdTextField.setText("");
        professorNumberTextField.setText("");
        professorLevelTextField.setText("");
        professorTypeTextField.setText("");
        professorAgeTextField.setText("");
        professorNationalIdNumberTextField.setText("");
    }



    @FXML
    protected void addStudentButtonOnAction(ActionEvent actionEvent) {
        Backend backend = Backend.getInstance();

        String password = studentPasswordTextField.getText();
        String name = studentNameTextField.getText();

        String fieldIdString = studentFieldIdTextField.getText();
        int fieldId;
        try {
            fieldId = Integer.parseInt(fieldIdString);
        }
        catch (NumberFormatException ignored){
            error("field should be an integer");
            return;
        }
        if(!backend.hasField(fieldId)){
            error("field doesn't exist");
            return;
        }

        String collegeIdString = studentCollegeIdTextField.getText();
        int collegeId;
        try {
            collegeId = Integer.parseInt(collegeIdString);
        }
        catch (NumberFormatException ignored){
            error("college should be an integer");
            return;
        }
        if(!backend.hasCollege(collegeId)){
            error("college doesn't exist");
            return;
        }

        String studentNumber = studentNumberTextField.getText();

        String studentLevelString = studentLevelTextField.getText();
        StudentLevel studentLevel;
        try {
            studentLevel = StudentLevel.valueOf(studentLevelString);
        }
        catch (IllegalArgumentException ignored) {
            error("student level should be "+ Arrays.toString(StudentLevel.values()));
            return;
        }

        String studentYearOfEntry = studentYearOfEntryTextField.getText();

        String ageString = studentAgeTextField.getText();
        int age;
        try {
            age = Integer.parseInt(ageString);
        }
        catch (NumberFormatException ignored){
            error("age should be an integer");
            return;
        }

        String nationalIdNumber = studentNationalIdNumberTextField.getText();

        Student student = new Student(password, name, fieldId, collegeId, studentNumber, studentLevel, studentYearOfEntry, age, nationalIdNumber);
        backend.addToStudents(student);

        clearStudentTextFields();
        error("student has been added");
    }


    private void clearStudentTextFields(){
        studentPasswordTextField.setText("");
        studentNameTextField.setText("");
        studentFieldIdTextField.setText("");
        studentCollegeIdTextField.setText("");
        studentNumberTextField.setText("");
        studentLevelTextField.setText("");
        studentYearOfEntryTextField.setText("");
        studentAgeTextField.setText("");
        studentNationalIdNumberTextField.setText("");
    }


    @FXML
    private void error(String error){
        errorText.setText(error);
    }
}
