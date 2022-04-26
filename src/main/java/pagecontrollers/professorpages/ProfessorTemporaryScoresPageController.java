package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.student.Student;
import models.universityitems.Course;
import models.universityitems.ReportCard;
import models.universityitems.ReportCardStatus;
import models.universityitems.requests.RecommendationLetterRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProfessorTemporaryScoresPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorTemporaryScoresPageController.class);

    public static final String fxmlFileName = "professorTemporaryScoresPage.fxml";

    @FXML
    TableView<ReportCard> tableView;

    @FXML
    TableView<Course> coursesTableView;


    // LOAD

    @FXML
    TextField loadCourseIdTextField;

    @FXML
    Button loadButton;


    @FXML
    Text errorText;


    // FILTER

    @FXML
    TextField studentIdTextField;

    @FXML
    TextField studentNameTextField;

    @FXML
    TextField minimumScoreTextField;

    @FXML
    TextField maximumScoreTextField;

    @FXML
    Button filterButton;


    @FXML
    Button releaseTemporaryScoresButton;

    @FXML
    Button finalizeScoresButton;


    private Integer courseId = null;

    @Override
    public void initialize(){
        super.initialize();

        Backend backend = Backend.getInstance();

        ObservableList<Course> coursesData = coursesTableView.getItems();
        coursesData.clear();

        User user = LoggedInUserHolder.getUser();

        if(user instanceof Professor professor) {
            for(Course course : backend.getCourses()){
                if(course.getProfessorId() == professor.getId()){
                    coursesData.add(course);
                }
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }

        tableView.setEditable(true);

        TableColumn<ReportCard, String> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setMaxWidth(130);
        scoreColumn.setPrefWidth(90);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        scoreColumn.setOnEditCommit(event -> {
            ReportCard reportCard = event.getRowValue();
            reportCard.setScore(event.getNewValue());
        });


        tableView.getColumns().add(scoreColumn);
    }

    public void filter() {
        clearFilter();
        filter("", "", "", "");
    }

    public void filter(String studentIdString, String studentName, String minimumScoreString, String maximumScoreString){
        Backend backend = Backend.getInstance();

        if(courseId == null){
            error("first load a course using load button");
            return;
        }

        Course course = backend.getCourse(courseId);
        if(course == null){
            error("course doesn't exist");
            return;
        }

        try{
            if(studentIdString.length() > 0) Integer.parseInt(studentIdString);
        }
        catch (NumberFormatException numberFormatException){
            error("student id must be an integer");
            return;
        }

        try{
            if(minimumScoreString.length() > 0) Double.parseDouble(minimumScoreString);
        }
        catch (NumberFormatException numberFormatException){
            error("minimum score must be a double");
            return;
        }

        try{
            if(maximumScoreString.length() > 0) Double.parseDouble(maximumScoreString);
        }
        catch (NumberFormatException numberFormatException){
            error("maximum score must be a double");
            return;
        }

        double minimumScore = 0;
        double maximumScore = 20;
        if(minimumScoreString.length() > 0) minimumScore = Double.parseDouble(minimumScoreString);
        if(maximumScoreString.length() > 0)maximumScore = Double.parseDouble(maximumScoreString);

        if(minimumScore > maximumScore){
            error("minimum score should be less than or equal to maximum score");
            return;
        }

        ObservableList<ReportCard> data = tableView.getItems();
        data.clear();

        User user = LoggedInUserHolder.getUser();

        if(user instanceof Professor professor) {
            for (int reportCardId : course.getReportCardIds()) {
                ReportCard reportCard = backend.getReportCard(reportCardId);
                if (reportCard == null) {
                    log.error("course(" + course.getId() + ") has a reportCardId(" + reportCardId + ") which doesn't exist");
                    throw new IllegalStateException("course(" + course.getId() + ") has a reportCardId(" + reportCardId + ") which doesn't exist");
                }

                Student student = backend.getStudent(reportCard.getStudentId());
                if (student == null) {
                    log.error("reportCard(" + reportCard.getId() + ")'s student doesn't exist");
                    throw new IllegalStateException("reportCard(" + reportCard.getId() + ")'s student doesn't exist");
                }

                Double score = null;
                if (reportCard.getScore().length() > 0) {
                    try {
                        score = Double.parseDouble(reportCard.getScore());
                    } catch (NumberFormatException ignored) {}
                }

                if (student.getCollegeId() == professor.getCollegeId()) {
                    ReportCardStatus status = reportCard.getStatus();
                    if (status == ReportCardStatus.TEMPORARILY_SCORED) {
                        if ((studentName.equals(student.getName()) || studentName.equals("")) &&
                                (studentIdString.equals(String.valueOf(reportCard.getStudentId())) || studentIdString.equals("")) &&
                                (score == null || (minimumScore <= score && score <= maximumScore))) {
                            data.add(reportCard);
                        }
                    }
                }
            }
        }
        else {
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }

        if(studentIdString.equals("") &&
                studentName.equals("") &&
                minimumScoreString.equals("") &&
                maximumScoreString.equals("")){
            error("");
        }
        else {
            error("filter applied");
        }
    }


    @FXML
    void loadButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();

        String courseIdString = loadCourseIdTextField.getText();

        try{
            if(courseIdString.length() > 0) Integer.parseInt(courseIdString);
        }
        catch (NumberFormatException numberFormatException){
            error("course id must be an integer");
            return;
        }

        int courseId = Integer.parseInt(courseIdString);

        Course course = backend.getCourse(courseId);
        if(course == null){
            error("course doesn't exist");
            return;
        }

        this.courseId = courseId;

        error("course has been loaded");
        filter();
    }


    @FXML
    void filterButtonOnAction(ActionEvent actionEvent){
        String studentId = studentIdTextField.getText();
        String studentName = studentNameTextField.getText();
        String minimumScoreString = minimumScoreTextField.getText();
        String maximumScoreString = maximumScoreTextField.getText();
        filter(studentId, studentName, minimumScoreString, maximumScoreString);
    }


    @FXML
    void releaseTemporaryScoresButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();
        if(courseId == null){
            error("first load a course using load button");
            return;
        }
        Course course = backend.getCourse(courseId);
        if(course == null){
            error("course doesn't exist");
            return;
        }

        ObservableList<ReportCard> data = tableView.getItems();

        boolean released = true;
        for(ReportCard reportCard : data){
            if(reportCard.getStatus() == ReportCardStatus.TAKEN){
                released = false;
                break;
            }
        }
        if(released){
            error("all report cards of course("+courseId+") are already released");
            return;
        }

        for(ReportCard reportCard : data){
            if(reportCard.getStatus() != ReportCardStatus.CREDITED &&
                    reportCard.getStatus() != ReportCardStatus.FAILED &&
                    reportCard.getStatus() != ReportCardStatus.WITHDRAWN) {
                try {
                    Double.parseDouble(reportCard.getScore());
                } catch (Exception ignored) {
                    error("reportCard(" + reportCard.getId() + ") of course(" + courseId + ") has a non-double score");
                    return;
                }
            }
        }

        for(ReportCard reportCard : data){
            if(reportCard.getStatus() != ReportCardStatus.CREDITED &&
                    reportCard.getStatus() != ReportCardStatus.FAILED &&
                    reportCard.getStatus() != ReportCardStatus.WITHDRAWN) {
                reportCard.setStatus(ReportCardStatus.TEMPORARILY_SCORED);
            }
        }

        filter();
        error("temporary scores of course("+courseId+") has been released");
    }


    @FXML
    void finalizeScoresButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();
        if(courseId == null){
            error("first load a course using load button");
            return;
        }
        Course course = backend.getCourse(courseId);
        if(course == null){
            error("course doesn't exist");
            return;
        }

        ObservableList<ReportCard> data = tableView.getItems();

        boolean released = true;
        for(ReportCard reportCard : data){
            if(reportCard.getStatus() == ReportCardStatus.TAKEN){
                released = false;
                break;
            }
        }
        if(!released){
            error("all report cards of course("+courseId+") must be released first");
            return;
        }

        boolean finalized = true;
        for(ReportCard reportCard : data){
            if(reportCard.getStatus() == ReportCardStatus.TEMPORARILY_SCORED){
                finalized = false;
                break;
            }
        }
        if(finalized){
            error("all report cards of course("+courseId+") are already finalized");
            return;
        }

        for(ReportCard reportCard : data){
            if(reportCard.getStatus() != ReportCardStatus.CREDITED &&
                    reportCard.getStatus() != ReportCardStatus.FAILED &&
                    reportCard.getStatus() != ReportCardStatus.WITHDRAWN) {
                try {
                    Double.parseDouble(reportCard.getScore());
                } catch (Exception ignored) {
                    error("reportCard(" + reportCard.getId() + ") of course(" + courseId + ") has a non-double score");
                    return;
                }
            }
        }

        for(ReportCard reportCard : data){
            if(reportCard.getStatus() != ReportCardStatus.CREDITED &&
                    reportCard.getStatus() != ReportCardStatus.FAILED &&
                    reportCard.getStatus() != ReportCardStatus.WITHDRAWN) {
                double score = Double.parseDouble(reportCard.getScore());

                if(score < 10) reportCard.setStatus(ReportCardStatus.FAILED);
                else reportCard.setStatus(ReportCardStatus.CREDITED);
            }
        }

        filter();
        error("scores of course("+courseId+") has been finalized");
    }


    private void error(String error){
        errorText.setText(error);
    }


    private void clearFilter(){
        studentIdTextField.setText("");
        studentNameTextField.setText("");
        minimumScoreTextField.setText("");
        maximumScoreTextField.setText("");
    }
}