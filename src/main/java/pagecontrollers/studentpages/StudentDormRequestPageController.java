package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import server.logic.Backend;
import models.LoggedInUserHolder;
import server.logic.RequestsAutomatedResponder;
import models.User;
import models.student.Student;
import models.universityitems.requests.DormRequest;
import models.universityitems.requests.Request;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentDormRequestPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentDormRequestPageController.class);

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

        RequestsAutomatedResponder.answerDormRequest(request);

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
