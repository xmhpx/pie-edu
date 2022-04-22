package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.student.Student;
import models.universityitems.ReportCard;
import models.universityitems.ReportCardStatus;

public class ProfessorEducationStatusPageController extends ProfessorPageController {

    public static final String fxmlFileName = "professorEducationStatusPage.fxml";

    @FXML
    TableView<ReportCard> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<ReportCard> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor professor) {
            for(ReportCard reportCard : backend.getReportCards()) {
                Student student = backend.getStudent(reportCard.getStudentId());
                if(student.getCollegeId() == professor.getCollegeId()) {
                    ReportCardStatus status = reportCard.getStatus();
                    if (status != ReportCardStatus.TAKEN && status != ReportCardStatus.TEMPORARILY_SCORED) {
                        data.add(reportCard);
                    }
                }
            }
        }
    }
}