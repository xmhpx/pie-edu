package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import models.universityitems.Course;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentCoursesListPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentCoursesListPageController.class);

    public static final String fxmlFileName = "studentCoursesListPage.fxml";

    @FXML
    TableView<Course> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<Course> data = tableView.getItems();
        data.clear();
        data.addAll(backend.getCourses());
    }
}
