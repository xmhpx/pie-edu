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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentEducationStatusPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentEducationStatusPageController.class);

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
                if(reportCard == null){
                    log.error("student("+student.getId()+") has reportCardId("+reportCardId+") which doesn't exist");
                    throw new IllegalStateException("student("+student.getId()+") has reportCardId("+reportCardId+") which doesn't exist");
                }
                ReportCardStatus status = reportCard.getStatus();
                if (status != ReportCardStatus.TAKEN && status != ReportCardStatus.TEMPORARILY_SCORED) {
                    data.add(reportCard);
                }
            }
        }
        else{
            log.error("logged in user is not a Student");
            throw new IllegalStateException("logged in user is not a Student");
        }
    }
}