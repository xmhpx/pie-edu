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
import models.ClassTime;
import models.Time;
import models.User;
import models.WeekDay;
import models.professor.Professor;
import models.professor.ProfessorType;
import models.student.Student;
import models.universityitems.College;
import models.universityitems.Course;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;

public class ProfessorAddCoursePageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorAddCoursePageController.class);

    public static final String fxmlFileName = "professorAddCoursePage.fxml";

    @FXML
    TableView<Course> tableView;


    // ADD

    @FXML
    TextField addCourseNameTextField;

    @FXML
    TextField addProfessorIdTextField;

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


    // LOAD

    @FXML
    TextField loadCourseIdTextField;

    @FXML
    Button loadButton;

    @FXML
    Button removeButton;


    @FXML
    Text errorEditText;


    // EDIT

    @FXML
    TextField editCourseNameTextField;

    @FXML
    TextField editProfessorIdTextField;

    @FXML
    TextField editHoldingSemesterTextField;

    @FXML
    TextField editCourseNumberTextField;

    @FXML
    TextField editSemesterCreditHoursTextField;

    @FXML
    TextField editExamDateTextField;

    @FXML
    Button editCourseButton;


    // ADD CLASS TIME

    @FXML
    TextField weekDayTextField;

    @FXML
    TextField startTimeTextField;

    @FXML
    TextField endTimeTextField;

    @FXML
    Button addClassTimeButton;


    // FILTER

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
                if((college.getId() == eduAssistant.getCollegeId()) &&
                        (courseName.equals(course.getName()) || courseName.equals("")) &&
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
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor eduAssistant) {
            if(eduAssistant.getProfessorType() != ProfessorType.EDUCATIONAL_ASSISTANT){
                log.error("logged in user is not EDUCATIONAL_ASSISTANT");
                throw new IllegalStateException("logged in user is not EDUCATIONAL_ASSISTANT");
            }

            String name = addCourseNameTextField.getText();

            int collegeId = eduAssistant.getCollegeId();

            String professorIdString = addProfessorIdTextField.getText();
            int professorId;
            try {
                professorId = Integer.parseInt(professorIdString);
            }
            catch (NumberFormatException ignored){
                errorEdit("professor id should be an integer");
                return;
            }
            if(!backend.hasProfessor(professorId)){
                errorEdit("professor doesn't exist");
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
                errorEdit("semester credit hours should be an integer");
                return;
            }

            String examDate = addExamDateTextField.getText();

            Course course = new Course(name, collegeId, professorId, holdingSemester, courseNumber, semesterCreditHours, examDate);
            backend.addToCourses(course);

            clearAddCourseTextFields();
            clean();
            initialize();
            errorEdit("course has been added");
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }
    }


    @FXML
    protected void removeCourseButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor eduAssistant) {
            if(eduAssistant.getProfessorType() != ProfessorType.EDUCATIONAL_ASSISTANT){
                log.error("logged in user is not EDUCATIONAL_ASSISTANT");
                throw new IllegalStateException("logged in user is not EDUCATIONAL_ASSISTANT");
            }

            String courseIdString = loadCourseIdTextField.getText();
            int courseId;
            try {
                courseId = Integer.parseInt(courseIdString);
            }
            catch (NumberFormatException ignored){
                errorEdit("course id should be an integer");
                return;
            }
            if(!backend.hasCourse(courseId)){
                errorEdit("course doesn't exist");
                return;
            }
            Course course = backend.getCourse(courseId);

            if(course.getCollegeId() != eduAssistant.getCollegeId()){
                errorEdit("you can't remove courses from other colleges");
                return;
            }

            for(int studentId : course.getStudentIds()){
                Student student = backend.getStudent(studentId);
                if(student == null){
                    log.error("course("+courseId+")'s has studentId("+studentId+") which doesn't exist");
                    throw new IllegalStateException("course("+courseId+")'s has studentId("+studentId+") which doesn't exist");
                }
            }
            for(int studentId : course.getStudentIds()){
                Student student = backend.getStudent(studentId);
                student.removeFromCourseIds(courseId);
            }
            backend.removeFromCourses(courseId);

            clearEditCourseTextFields();
            clean();
            initialize();
            errorEdit("course("+courseId+") has been removed");
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }
    }



    @FXML
    protected void loadButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor eduAssistant) {
            if(eduAssistant.getProfessorType() != ProfessorType.EDUCATIONAL_ASSISTANT){
                log.error("logged in user is not EDUCATIONAL_ASSISTANT");
                throw new IllegalStateException("logged in user is not EDUCATIONAL_ASSISTANT");
            }

            String courseIdString = loadCourseIdTextField.getText();
            int courseId;
            try {
                courseId = Integer.parseInt(courseIdString);
            }
            catch (NumberFormatException ignored){
                errorEdit("course id should be an integer");
                return;
            }
            if(!backend.hasCourse(courseId)){
                errorEdit("course doesn't exist");
                return;
            }
            Course course = backend.getCourse(courseId);

            if(course.getCollegeId() != eduAssistant.getCollegeId()){
                errorEdit("you can't load courses from other colleges");
                return;
            }

            editCourseNameTextField.setText(course.getName());
            editProfessorIdTextField.setText(String.valueOf(course.getProfessorId()));
            editHoldingSemesterTextField.setText(course.getHoldingSemester());
            editCourseNumberTextField.setText(course.getCourseNumber());
            editSemesterCreditHoursTextField.setText(String.valueOf(course.getSemesterCreditHours()));
            editExamDateTextField.setText(course.getExamDate());

            errorEdit("course("+courseId+") has been loaded");
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }
    }


    @FXML
    protected void editCourseButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor eduAssistant) {
            if(eduAssistant.getProfessorType() != ProfessorType.EDUCATIONAL_ASSISTANT){
                log.error("logged in user is not EDUCATIONAL_ASSISTANT");
                throw new IllegalStateException("logged in user is not EDUCATIONAL_ASSISTANT");
            }

            String courseIdString = loadCourseIdTextField.getText();
            int courseId;
            try {
                courseId = Integer.parseInt(courseIdString);
            }
            catch (NumberFormatException ignored){
                errorEdit("course id should be an integer");
                return;
            }
            if(!backend.hasCourse(courseId)){
                errorEdit("course doesn't exist");
                return;
            }
            Course course = backend.getCourse(courseId);

            if(course.getCollegeId() != eduAssistant.getCollegeId()){
                errorEdit("you can't edit courses from other colleges");
                return;
            }

            try{
                Integer.parseInt(editProfessorIdTextField.getText());
            }
            catch (Exception ignore){
                errorEdit("professor id should be an integer");
                return;
            }
            if(!backend.hasProfessor(Integer.parseInt(editProfessorIdTextField.getText()))){
                errorEdit("professor doesn't exist");
                return;
            }

            try{
                Integer.parseInt(editSemesterCreditHoursTextField.getText());
            }
            catch (Exception ignore){
                errorEdit("semester credit hours should be an integer");
                return;
            }

            course.setName(editCourseNameTextField.getText());
            course.setProfessorId(Integer.parseInt(editProfessorIdTextField.getText()));
            course.setHoldingSemester(editHoldingSemesterTextField.getText());
            course.setCourseNumber(editCourseNumberTextField.getText());
            course.setSemesterCreditHours(Integer.parseInt(editSemesterCreditHoursTextField.getText()));
            course.setExamDate(editExamDateTextField.getText());

            clearEditCourseTextFields();
            errorEdit("course("+courseId+") has been edited");
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }
    }


    @FXML
    protected void addClassTimeButtonOnAction(ActionEvent actionEvent){
        Backend backend = Backend.getInstance();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor eduAssistant) {
            if(eduAssistant.getProfessorType() != ProfessorType.EDUCATIONAL_ASSISTANT){
                log.error("logged in user is not EDUCATIONAL_ASSISTANT");
                throw new IllegalStateException("logged in user is not EDUCATIONAL_ASSISTANT");
            }

            String courseIdString = loadCourseIdTextField.getText();
            int courseId;
            try {
                courseId = Integer.parseInt(courseIdString);
            }
            catch (NumberFormatException ignored){
                errorEdit("course id should be an integer");
                return;
            }
            if(!backend.hasCourse(courseId)){
                errorEdit("course doesn't exist");
                return;
            }
            Course course = backend.getCourse(courseId);

            if(course.getCollegeId() != eduAssistant.getCollegeId()){
                errorEdit("you can't edit courses from other colleges");
                return;
            }

            WeekDay weekDay;
            try{
                weekDay = WeekDay.valueOf(weekDayTextField.getText());
            }
            catch (Exception ignore){
                errorEdit("week day should be "+Arrays.toString(WeekDay.values()));
                return;
            }

            Time startTime = Time.toTime(startTimeTextField.getText());
            if(startTime == null) {
                errorEdit("start time should be in format H:M");
                return;
            }

            Time endTime = Time.toTime(endTimeTextField.getText());
            if(endTime == null) {
                errorEdit("end time should be in format H:M");
                return;
            }

            ClassTime classTime = new ClassTime(weekDay, startTime, endTime, courseId);
            course.addToClassTimes(classTime);

            clearClassTimeTextFields();
            errorEdit("class time has been added");
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }
    }



    private void clearAddCourseTextFields(){
        addCourseNameTextField.setText("");
        addProfessorIdTextField.setText("");
        addHoldingSemesterTextField.setText("");
        addCourseNumberTextField.setText("");
        addSemesterCreditHoursTextField.setText("");
        addExamDateTextField.setText("");
    }

    private void clearEditCourseTextFields(){
        editCourseNameTextField.setText("");
        editProfessorIdTextField.setText("");
        editHoldingSemesterTextField.setText("");
        editCourseNumberTextField.setText("");
        editSemesterCreditHoursTextField.setText("");
        editExamDateTextField.setText("");
    }

    private void clearClassTimeTextFields(){
        weekDayTextField.setText("");
        startTimeTextField.setText("");
        endTimeTextField.setText("");
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
