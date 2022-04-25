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
import models.student.Student;
import models.student.StudentEducationStatus;
import models.universityitems.requests.MinorRequest;
import models.universityitems.requests.Request;
import models.universityitems.requests.WithdrawalRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class ProfessorWithdrawalRequestPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorWithdrawalRequestPageController.class);

    public static final String fxmlFileName = "professorWithdrawalRequestPage.fxml";

    @FXML
    TableView<WithdrawalRequest> tableView;


    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<WithdrawalRequest> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor professor){
            for(Request request : backend.getRequests()){
                if(request instanceof WithdrawalRequest withdrawalRequest){
                    Student student = backend.getStudent(withdrawalRequest.getSenderId());
                    if(student == null){
                        log.error("withdrawalRequest("+withdrawalRequest.getId()+")'s student doesn't exist");
                        throw new IllegalStateException("withdrawalRequest("+withdrawalRequest.getId()+")'s student doesn't exist");
                    }
                    if(student.getCollegeId() == professor.getCollegeId()) {
                        data.add(withdrawalRequest);
                    }
                }
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }



        tableView.setEditable(true);

        TableColumn<WithdrawalRequest, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMaxWidth(200);
        statusColumn.setPrefWidth(90);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        statusColumn.setOnEditCommit(event -> {
            WithdrawalRequest recommendationLetterRequest = event.getRowValue();
            recommendationLetterRequest.setStatus(event.getNewValue());
        });


        TableColumn<WithdrawalRequest, String> responseColumn = new TableColumn<>("Response");
        responseColumn.setMaxWidth(500);
        responseColumn.setPrefWidth(90);
        responseColumn.setCellValueFactory(new PropertyValueFactory<>("response"));
        responseColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        responseColumn.setOnEditCommit(event -> {
            WithdrawalRequest recommendationLetterRequest = event.getRowValue();
            recommendationLetterRequest.setResponse(event.getNewValue());
        });

        tableView.getColumns().add(statusColumn);
        tableView.getColumns().add(responseColumn);
    }

    public void acceptWithdrawalRequest(WithdrawalRequest withdrawalRequest){
        withdrawalRequest.setStatus("Accepted");
        Student student = Backend.getInstance().getStudent(withdrawalRequest.getSenderId());
        if(student == null){
            log.error("WithdrawalRequest("+withdrawalRequest.getId()+")'s student doesn't exist");
            throw new IllegalStateException("WithdrawalRequest("+withdrawalRequest.getId()+")'s student doesn't exist");
        }
        student.setEducationStatus(StudentEducationStatus.WITHDRAWN);
    }
}
