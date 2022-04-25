package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.student.Student;
import models.universityitems.ReportCard;
import models.universityitems.ReportCardStatus;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentTemporaryScoresPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentTemporaryScoresPageController.class);

    public static final String fxmlFileName = "studentTemporaryScoresPage.fxml";

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
                if(reportCard.getStatus() == ReportCardStatus.TEMPORARILY_SCORED){
                    data.add(reportCard);
                }
            }
        }
        else{
            log.error("logged in user is not a student");
            throw new IllegalStateException("logged in user is not a student");
        }

        tableView.setEditable(true);

        TableColumn<ReportCard, String> objectionColumn = new TableColumn<>("Objection");
        objectionColumn.setMaxWidth(200);
        objectionColumn.setPrefWidth(100);
        objectionColumn.setCellValueFactory(new PropertyValueFactory<>("objection"));
        objectionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        objectionColumn.setOnEditCommit(event -> {
            ReportCard reportCard = event.getRowValue();
            reportCard.setObjection(event.getNewValue());
        });


        TableColumn<ReportCard, String> responseColumn = new TableColumn<>("Response");
        responseColumn.setMaxWidth(200);
        responseColumn.setPrefWidth(100);

        tableView.getColumns().add(objectionColumn);
        tableView.getColumns().add(responseColumn);
    }
}