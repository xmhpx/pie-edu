package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.student.Student;
import models.universityitems.Course;
import models.universityitems.requests.RecommendationLetterRequest;
import models.universityitems.requests.Request;


public class StudentCoursesListPageController extends StudentPageController {

    @FXML
    TableView<Course> tableView;

    @Override
    public void initialize(){
        super.initialize();
        ObservableList<Course> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            data.addAll(student.getCourses());
        }
    }
}
