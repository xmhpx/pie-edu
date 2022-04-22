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
import models.universityitems.requests.MinorRequest;
import models.universityitems.requests.Request;
import models.universityitems.requests.WithdrawalRequest;

import java.io.IOException;

public class ProfessorWithdrawalRequestPageController extends ProfessorPageController {

    public static final String fxmlFileName = "professorWithdrawalRequestPage.fxml";

    @FXML
    TableView<WithdrawalRequest> tableView;

    @FXML
    TextField titleTextField;

    @FXML
    TextField bodyTextField;

    @FXML
    Button addRequestButton;

    @FXML
    Text errorText;


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
                    if(student.getCollegeId() == professor.getCollegeId()) {
                        data.add(withdrawalRequest);
                    }
                }
            }
        }


        tableView.setEditable(true);

        TableColumn<WithdrawalRequest, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMaxWidth(200);
        statusColumn.setPrefWidth(70);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<WithdrawalRequest, String> responseColumn = new TableColumn<>("Response");
        responseColumn.setMaxWidth(500);
        responseColumn.setPrefWidth(70);
        responseColumn.setCellValueFactory(new PropertyValueFactory<>("response"));
        responseColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tableView.getColumns().add(statusColumn);
        tableView.getColumns().add(responseColumn);
    }


    @FXML
    void addRequestButtonOnAction(ActionEvent actionEvent) {
        Backend backend = Backend.getInstance();

        String title = titleTextField.getText();
        String body = bodyTextField.getText();

        if(title.equals("")){
            error("empty title is not allowed");
            return;
        }

        WithdrawalRequest request = new WithdrawalRequest(title, body, LoggedInUserHolder.getUser().getId());
        backend.addToRequests(request);
        Student student = backend.getStudent(request.getSenderId());
        student.addToRequest(request.getId());

        reload();
    }

    private void error(String error){
        clean();
        errorText.setText(error);
    }

    private void clean(){
        errorText.setText("");
        titleTextField.setText("");
        bodyTextField.setText("");
    }

    private void reload(){
        try {
            goToStudentPage(fxmlFileName);
        } catch (IOException e) {
            error("some backend problem happened, try again");
        }
    }
}
