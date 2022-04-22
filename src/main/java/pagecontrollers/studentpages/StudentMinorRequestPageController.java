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
import models.universityitems.Field;
import models.universityitems.requests.MinorRequest;
import models.universityitems.requests.Request;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class StudentMinorRequestPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentMinorRequestPageController.class);

    public static final String fxmlFileName = "studentMinorRequestPage.fxml";

    @FXML
    TableView<MinorRequest> tableView;

    @FXML
    TextField titleTextField;

    @FXML
    TextField bodyTextField;

    @FXML
    TextField destinationCollegeIdTextField;

    @FXML
    TextField minorFieldIdTextField;

    @FXML
    Text errorText;

    @FXML
    Button addRequestButton;


    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<MinorRequest> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            for(int requestId : student.getRequestIds()) {
                Request request = backend.getRequest(requestId);
                if(request == null){
                    log.error("student("+student.getId()+") has requestId("+requestId+") which doesn't exist");
                    throw new IllegalStateException("student("+student.getId()+") has requestId("+requestId+") which doesn't exist");
                }
                if(request instanceof MinorRequest)data.add((MinorRequest) request);
            }
        }
        else{
            log.error("logged in user is not a student");
            throw new IllegalStateException("logged in user is not a student");
        }
    }

    @FXML
    void addRequestButtonOnAction(ActionEvent actionEvent) {
        Backend backend = Backend.getInstance();

        String title = titleTextField.getText();
        String body = bodyTextField.getText();
        int destinationCollegeId;
        int minorFieldId;

        if(title.equals("")){
            error("empty title is not allowed");
            return;
        }

        try {
            destinationCollegeId = Integer.parseInt(destinationCollegeIdTextField.getText());
        }
        catch (NumberFormatException numberFormatException){
            error("destination college id must be an integer");
            return;
        }

        try {
            minorFieldId = Integer.parseInt(minorFieldIdTextField.getText());
        }
        catch (NumberFormatException numberFormatException){
            error("minor field id must be an integer");
            return;
        }

        if(!backend.hasCollege(destinationCollegeId)){
            error("destination college doesn't exist");
            return;
        }

        if(!backend.hasField(minorFieldId)){
            error("minor field doesn't exist");
            return;
        }

        ArrayList<Field> fields = backend.getFields();

        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            for(Field field : fields) {
                if (field.getCollegeId() == destinationCollegeId && field.getId() == minorFieldId) {
                    MinorRequest request = new MinorRequest(title, body, student.getId(), destinationCollegeId, minorFieldId);
                    backend.addToRequests(request);
                    student.addToRequest(request.getId());

                    reload();
                    return;
                }
            }
        }

        error("the destination college doesn't have that minor field");
    }

    private void error(String error){
        clean();
        errorText.setText(error);
    }

    private void clean(){
        errorText.setText("");
        titleTextField.setText("");
        bodyTextField.setText("");
        destinationCollegeIdTextField.setText("");
        minorFieldIdTextField.setText("");
    }

    private void reload(){
        clean();
        initialize();
    }
}
