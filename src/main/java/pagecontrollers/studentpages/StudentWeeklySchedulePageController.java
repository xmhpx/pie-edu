package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import server.logic.Backend;
import models.LoggedInUserHolder;
import models.User;
import models.WeeklySchedule;
import models.student.Student;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentWeeklySchedulePageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentWeeklySchedulePageController.class);

    public static final String fxmlFileName = "studentWeeklySchedulePage.fxml";

    @FXML
    TableView<WeeklySchedule> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<WeeklySchedule> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            WeeklySchedule weeklySchedule = new WeeklySchedule(student);
            data.add(weeklySchedule);
        }
        else{
            log.error("logged in user is not a student");
            throw new IllegalStateException("logged in user is not a student");
        }
    }
}
