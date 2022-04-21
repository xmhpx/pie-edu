package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.WeeklySchedule;
import models.student.Student;

public class ProfessorWeeklySchedulePageController extends ProfessorPageController {

    public static final String fxmlFileName = "professorWeeklySchedulePage.fxml";

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
    }
}
