package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.student.Student;
import models.universityitems.Course;

import java.util.Comparator;

public class ProfessorExamsListPageController extends ProfessorPageController {

    public static final String fxmlFileName = "professorExamsListPage.fxml";

    @FXML
    TableView<Course> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<Course> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor professor) {
            for(Course course : backend.getCourses()) {
                if(course.getProfessorId() == professor.getId()) {
                    data.add(course);
                }
            }
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
