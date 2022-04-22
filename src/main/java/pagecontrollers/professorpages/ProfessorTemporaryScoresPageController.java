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
import models.student.Student;
import models.universityitems.ReportCard;
import models.universityitems.ReportCardStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProfessorTemporaryScoresPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorTemporaryScoresPageController.class);

    public static final String fxmlFileName = "professorTemporaryScoresPage.fxml";

    @FXML
    TableView<ReportCard> tableView;

    @FXML
    TextField studentNameTextField;

    @FXML
    TextField studentIdTextField;

    @FXML
    TextField professorNameTextField;

    @FXML
    TextField professorIdTextField;

    @FXML
    TextField courseIdTextField;

    @FXML
    Button filterButton;

    @FXML
    TextField courseSummeryIdTextField;

    @FXML
    Button courseSummeryButton;

    @FXML
    Text averageText;

    @FXML
    Text failedText;

    @FXML
    Text creditedText;

    @FXML
    Text averageWithoutFailedText;

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
                if(student == null){
                    log.error("reportCard("+reportCard.getId()+")'s student doesn't exist");
                    throw new IllegalStateException("reportCard("+reportCard.getId()+")'s student doesn't exist");
                }
                if(student.getCollegeId() == professor.getCollegeId()) {
                    ReportCardStatus status = reportCard.getStatus();
                    if (status == ReportCardStatus.TEMPORARILY_SCORED) {
                        data.add(reportCard);
                    }
                }
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }
    }

    public void initialize(String studentName, String studentIdString, String professorName, String professorIdString, String courseIdString){
        try{
            if(studentIdString.length() > 0) Integer.parseInt(studentIdString);
        }
        catch (NumberFormatException numberFormatException){
            error("student id must be an integer");
            return;
        }

        try{
            if(professorIdString.length() > 0) Integer.parseInt(professorIdString);
        }
        catch (NumberFormatException numberFormatException){
            error("professor id must be an integer");
            return;
        }

        try{
            if(courseIdString.length() > 0) Integer.parseInt(courseIdString);
        }
        catch (NumberFormatException numberFormatException){
            error("course id must be an integer");
            return;
        }

        super.initialize();

        Backend backend = Backend.getInstance();
        ObservableList<ReportCard> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();

        if(user instanceof Professor) {
            for(ReportCard reportCard : backend.getReportCards()) {
                Student student = backend.getStudent(reportCard.getStudentId());
                if(student == null){
                    log.error("reportCard("+reportCard.getId()+")'s student doesn't exist");
                    throw new IllegalStateException("reportCard("+reportCard.getId()+")'s student doesn't exist");
                }

                Professor professor = backend.getProfessor(reportCard.getProfessorId());
                if(professor == null){
                    log.error("reportCard("+reportCard.getId()+")'s professor doesn't exist");
                    throw new IllegalStateException("reportCard("+reportCard.getId()+")'s professor doesn't exist");
                }

                if(student.getCollegeId() == user.getCollegeId()) {
                    ReportCardStatus status = reportCard.getStatus();
                    if (status == ReportCardStatus.TEMPORARILY_SCORED) {
                        if((studentName.equals(student.getName()) || studentName.equals("")) &&
                                (professorName.equals(professor.getName()) || professorName.equals("")) &&
                                (studentIdString.equals(String.valueOf(reportCard.getStudentId())) || studentIdString.equals("")) &&
                                (professorIdString.equals(String.valueOf(reportCard.getProfessorId())) || professorIdString.equals("")) &&
                                (courseIdString.equals(String.valueOf(reportCard.getCourseId())) || courseIdString.equals(""))){
                            data.add(reportCard);
                        }
                    }
                }
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }

        if(studentName.equals("") &&
                professorName.equals("") &&
                studentIdString.equals("") &&
                professorIdString.equals("") &&
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
        String professorName = professorNameTextField.getText();
        String professorId = professorIdTextField.getText();
        String courseId = courseIdTextField.getText();
        initialize(studentName, studentId, professorName, professorId, courseId);
    }

    @FXML
    void courseSummeryButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();
        String courseSummeryIdString = courseSummeryIdTextField.getText();
        int courseSummeryId;
        try{
            courseSummeryId = Integer.parseInt(courseSummeryIdString);
        }
        catch (NumberFormatException numberFormatException){
            error("course summery id must be an integer");
            return;
        }

        if(courseSummeryIdString.length() == 0){
            error("course summery id must be an integer");
            return;
        }

        if(!backend.hasCourse(Integer.parseInt(courseSummeryIdString))){
            error("course id doesn't exist");
            return;
        }

        int failedCount = 0;
        int creditedCount = 0;
        int finalCount = 0;
        double sum = 0;
        double sumWithoutFailed = 0;

        for (ReportCard reportCard : backend.getReportCards()) {
            if (reportCard.getCourseId() == courseSummeryId) {
                if (reportCard.getStatus() == ReportCardStatus.FAILED) {
                    failedCount++;
                    finalCount++;
                }
                if (reportCard.getStatus() == ReportCardStatus.CREDITED) {
                    creditedCount++;
                    finalCount++;
                }

                try{
                    if(reportCard.getStatus() != ReportCardStatus.FAILED)
                        sumWithoutFailed += Double.parseDouble(reportCard.getScore());
                    sum += Double.parseDouble(reportCard.getScore());
                }
                catch (NumberFormatException ignored){}
            }
        }


        failedText.setText("Failed: "+failedCount);
        creditedText.setText("Credited: "+creditedCount);

        if(finalCount == 0){
            averageText.setText("Average: N/A");
        }
        else {
            double average = sum / finalCount;
            averageText.setText("Average: " + average);
        }

        if(finalCount - failedCount == 0) {
            averageWithoutFailedText.setText("Average Without Failed: N/A");
        }
        else{
            double averageWithoutFailed = sumWithoutFailed / (finalCount - failedCount);
            averageWithoutFailedText.setText("Average Without Failed: " + averageWithoutFailed);
        }
    }


    private void error(String error){
        errorText.setText(error);
    }

    private void cleanFilter(){
        errorText.setText("");
        studentNameTextField.setText("");
        studentIdTextField.setText("");
        professorNameTextField.setText("");
        professorIdTextField.setText("");
        courseIdTextField.setText("");
        initialize();
    }
}