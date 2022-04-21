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
import models.User;
import models.student.Student;
import models.universityitems.requests.DormRequest;
import models.universityitems.requests.Request;

import java.io.IOException;

public class StudentDormRequestPageController extends StudentPageController {

    public static final String fxmlFileName = "studentDormRequestPage.fxml";

    @FXML
    TableView<DormRequest> tableView;

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
        ObservableList<DormRequest> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            for(int requestId : student.getRequestIds()) {
                Request request = backend.getRequest(requestId);
                if(request instanceof DormRequest)data.add((DormRequest) request);
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

        DormRequest request = new DormRequest(title, body, LoggedInUserHolder.getUser().getId());
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
