package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.professor.ProfessorType;
import models.student.Student;
import models.universityitems.Field;
import models.universityitems.requests.MinorRequest;
import models.universityitems.requests.RecommendationLetterRequest;
import models.universityitems.requests.Request;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class ProfessorMinorRequestPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorMinorRequestPageController.class);

    public static final String fxmlFileName = "professorMinorRequestPage.fxml";

    @FXML
    TableView<MinorRequest> tableView;


    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<MinorRequest> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor professor) {
            for (Request request : backend.getRequests()) {
                if (request instanceof MinorRequest minorRequest){
                    int destinationCollegeId = minorRequest.getDestinationCollegeId();
                    Student student = backend.getStudent(minorRequest.getSenderId());
                    if(student == null){
                        log.error("minorRequest("+minorRequest.getId()+")'s student doesn't exist");
                        throw new IllegalStateException("minorRequest("+minorRequest.getId()+")'s student doesn't exist");
                    }
                    int originCollegeId = student.getCollegeId();
                    if(destinationCollegeId == professor.getCollegeId() || originCollegeId == professor.getCollegeId()) {
                        data.add((MinorRequest) request);
                    }
                }
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }



        tableView.setEditable(true);

        TableColumn<MinorRequest, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMaxWidth(200);
        statusColumn.setPrefWidth(70);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        statusColumn.setOnEditCommit(event -> {
            MinorRequest recommendationLetterRequest = event.getRowValue();
            recommendationLetterRequest.setStatus(event.getNewValue());
        });


        TableColumn<MinorRequest, String> responseColumn = new TableColumn<>("Response");
        responseColumn.setMaxWidth(500);
        responseColumn.setPrefWidth(70);
        responseColumn.setCellValueFactory(new PropertyValueFactory<>("response"));
        responseColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        responseColumn.setOnEditCommit(event -> {
            MinorRequest recommendationLetterRequest = event.getRowValue();
            recommendationLetterRequest.setResponse(event.getNewValue());
        });

        tableView.getColumns().add(statusColumn);
        tableView.getColumns().add(responseColumn);
    }
}
