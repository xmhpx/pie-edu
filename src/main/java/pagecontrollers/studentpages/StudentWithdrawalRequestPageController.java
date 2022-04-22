package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.student.Student;
import models.universityitems.requests.CertificateStudentRequest;
import models.universityitems.requests.Request;
import models.universityitems.requests.WithdrawalRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class StudentWithdrawalRequestPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentWithdrawalRequestPageController.class);

    public static final String fxmlFileName = "studentWithdrawalRequestPage.fxml";

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
        for(Request request : backend.getRequests()){
            if(request instanceof WithdrawalRequest && request.getSenderId() == LoggedInUserHolder.getUser().getId()){
                data.add((WithdrawalRequest) request);
            }
        }
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
        Student student = (Student) LoggedInUserHolder.getUser();
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
        clean();
        initialize();
    }
}
