package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.student.Student;
import models.universityitems.ReportCard;
import models.universityitems.ReportCardStatus;

public class StudentEducationStatusPageController extends StudentPageController {

    public static final String fxmlFileName = "studentEducationStatusPage.fxml";

    @FXML
    TableView<ReportCard> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<ReportCard> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            for(int reportCardId : student.getReportCardIds()) {
                ReportCard reportCard = backend.getReportCard(reportCardId);
                ReportCardStatus status = reportCard.getStatus();
                if (status != ReportCardStatus.TAKEN && status != ReportCardStatus.TEMPORARILY_SCORED) {
                    data.add(reportCard);
                }
            }
        }
    }
}