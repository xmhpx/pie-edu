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
import models.User;
import models.student.Student;
import models.universityitems.requests.DissertationDefenseRequest;
import models.universityitems.requests.Request;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentDissertationDefenseRequestPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentDissertationDefenseRequestPageController.class);

    public static final String fxmlFileName = "studentDissertationDefenseRequestPage.fxml";

    @FXML
    TableView<DissertationDefenseRequest> tableView;

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
        ObservableList<DissertationDefenseRequest> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            for(int requestId : student.getRequestIds()) {
                Request request = backend.getRequest(requestId);
                if(request instanceof DissertationDefenseRequest)data.add((DissertationDefenseRequest) request);
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

        DissertationDefenseRequest request = new DissertationDefenseRequest(title, body, LoggedInUserHolder.getUser().getId());

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
