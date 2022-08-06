package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import server.logic.Backend;
import models.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.universityitems.Course;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Comparator;

public class ProfessorExamsListPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorExamsListPageController.class);

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
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
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
