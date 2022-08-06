package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import server.logic.Backend;
import models.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.professor.ProfessorType;
import models.student.Student;
import models.universityitems.requests.DissertationDefenseRequest;
import models.universityitems.requests.Request;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProfessorDissertationDefenceRequestPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorDissertationDefenceRequestPageController.class);

    public static final String fxmlFileName = "professorDissertationDefenceRequestPage.fxml";

    @FXML
    TableView<DissertationDefenseRequest> tableView;


    @Override
    public void initialize(){
        super.initialize();
        tableView.setEditable(true);
        Backend backend = Backend.getInstance();
        ObservableList<DissertationDefenseRequest> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor professor) {
            if(professor.getProfessorType() != ProfessorType.EDUCATIONAL_ASSISTANT) {
                log.error("logged in user is not EDUCATIONAL_ASSISTANT");
                throw new IllegalStateException("logged in user is not EDUCATIONAL_ASSISTANT");
            }
            for (Request request : backend.getRequests()) {
                if (request instanceof DissertationDefenseRequest dissertationDefenseRequest) {
                    Student student = backend.getStudent(dissertationDefenseRequest.getSenderId());
                    if(student == null){
                        log.error("dissertationDefenseRequest("+dissertationDefenseRequest.getId()+")'s sender doesn't exist");
                        throw new IllegalStateException("dissertationDefenseRequest("+dissertationDefenseRequest.getId()+")'s sender doesn't exist");
                    }
                    if (student.getCollegeId() == professor.getCollegeId()) {
                        data.add(dissertationDefenseRequest);
                    }
                }
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }


        tableView.setEditable(true);

        TableColumn<DissertationDefenseRequest, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMaxWidth(200);
        statusColumn.setPrefWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        statusColumn.setOnEditCommit(event -> {
            DissertationDefenseRequest dissertationDefenseRequest = event.getRowValue();
            dissertationDefenseRequest.setStatus(event.getNewValue());
        });


        TableColumn<DissertationDefenseRequest, String> responseColumn = new TableColumn<>("Response");
        responseColumn.setMaxWidth(500);
        responseColumn.setPrefWidth(100);
        responseColumn.setCellValueFactory(new PropertyValueFactory<>("response"));
        responseColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        responseColumn.setOnEditCommit(event -> {
            DissertationDefenseRequest dissertationDefenseRequest = event.getRowValue();
            dissertationDefenseRequest.setResponse(event.getNewValue());
        });

        tableView.getColumns().add(statusColumn);
        tableView.getColumns().add(responseColumn);
    }
}
