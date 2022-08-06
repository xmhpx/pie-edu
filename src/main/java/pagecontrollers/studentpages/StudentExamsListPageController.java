package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import server.logic.Backend;
import models.LoggedInUserHolder;
import models.User;
import models.student.Student;
import models.universityitems.Course;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Comparator;

public class StudentExamsListPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentExamsListPageController.class);

    public static final String fxmlFileName = "studentExamsListPage.fxml";

    @FXML
    TableView<Course> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<Course> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            for(int courseId : student.getCourseIds()) {
                Course course = backend.getCourse(courseId);
                if(course == null){
                    log.error("student("+student.getId()+") has courseId("+courseId+") which doesn't exist");
                    throw new IllegalStateException("student("+student.getId()+") has courseId("+courseId+") which doesn't exist");
                }
                data.add(course);
            }
        }
        else{
            log.error("logged in user is not a student");
            throw new IllegalStateException("logged in user is not a student");
        }

        Comparator<Course> courseComparator = new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                String examDate1 = o1.getExamDate();
                String examDate2 = o2.getExamDate();
                return examDate1.compareTo(examDate2);
            }
        };

        data.sort(courseComparator);
    }
}
