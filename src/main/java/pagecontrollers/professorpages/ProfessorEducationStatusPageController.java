package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.student.Student;
import models.universityitems.ReportCard;
import models.universityitems.ReportCardStatus;

public class ProfessorEducationStatusPageController extends ProfessorPageController {

    public static final String fxmlFileName = "professorEducationStatusPage.fxml";

    @FXML
    TableView<ReportCard> tableView;

    @FXML
    TextField studentNameTextField;

    @FXML
    TextField studentIdTextField;

    @FXML
    TextField courseIdTextField;

    @FXML
    Button filterButton;

    @FXML
    Text errorText;


    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<ReportCard> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor professor) {
            for(ReportCard reportCard : backend.getReportCards()) {
                Student student = backend.getStudent(reportCard.getStudentId());
                if(student.getCollegeId() == professor.getCollegeId()) {
                    ReportCardStatus status = reportCard.getStatus();
                    if (status != ReportCardStatus.TAKEN && status != ReportCardStatus.TEMPORARILY_SCORED) {
                        data.add(reportCard);
                    }
                }
            }
        }
    }

    public void initialize(String studentName, String studentIdString, String courseIdString){
        try{
            if(studentIdString.length() > 0) Integer.parseInt(studentIdString);
        }
        catch (NumberFormatException numberFormatException){
            error("student id must be an integer");
            initialize();
            return;
        }

        try{
            if(courseIdString.length() > 0) Integer.parseInt(courseIdString);
        }
        catch (NumberFormatException numberFormatException){
            error("course id must be an integer");
            initialize();
            return;
        }

        super.initialize();

        Backend backend = Backend.getInstance();
        ObservableList<ReportCard> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor professor) {
            for(ReportCard reportCard : backend.getReportCards()) {
                Student student = backend.getStudent(reportCard.getStudentId());
                if(student.getCollegeId() == professor.getCollegeId()) {
                    ReportCardStatus status = reportCard.getStatus();
                    if (status != ReportCardStatus.TAKEN && status != ReportCardStatus.TEMPORARILY_SCORED) {
                        if((studentName.equals(student.getName()) || studentName.equals("")) &&
                                (studentIdString.equals(String.valueOf(reportCard.getStudentId())) || studentIdString.equals("")) &&
                                (courseIdString.equals(String.valueOf(reportCard.getCourseId())) || courseIdString.equals(""))){
                            data.add(reportCard);
                        }
                    }
                }
            }
        }

        if(studentName.equals("") &&
                studentIdString.equals("") &&
                courseIdString.equals("")){
            error("");
        }
        else {
            error("filter applied");
        }
    }


    @FXML
    void filterButtonOnAction(ActionEvent actionEvent){
        String studentName = studentNameTextField.getText();
        String studentId = studentIdTextField.getText();
        String courseId = courseIdTextField.getText();
        initialize(studentName, studentId, courseId);
    }


    private void error(String error){
        errorText.setText(error);
    }

    private void clean(){
        errorText.setText("");
        studentNameTextField.setText("");
        studentIdTextField.setText("");
        courseIdTextField.setText("");
    }
}