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
import models.universityitems.requests.CertificateStudentRequest;
import models.universityitems.requests.Request;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentCertificateStudentRequestPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentCertificateStudentRequestPageController.class);


    public static final String fxmlFileName = "studentCertificateStudentRequestPage.fxml";

    @FXML
    TableView<CertificateStudentRequest> tableView;

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
        ObservableList<CertificateStudentRequest> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            for(int requestId : student.getRequestIds()) {
                Request request = backend.getRequest(requestId);
                if(request == null){
                    log.error("student("+student.getId()+") has requestId("+requestId+") which doesn't exist");
                    throw new IllegalStateException("student("+student.getId()+") has requestId("+requestId+") which doesn't exist");
                }
                if(request instanceof CertificateStudentRequest)data.add((CertificateStudentRequest) request);
            }
        }
        else{
            log.error("logged in user is not a Student");
            throw new IllegalStateException("logged in user is not a Student");
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

        CertificateStudentRequest request = new CertificateStudentRequest(title, body, LoggedInUserHolder.getUser().getId());

        RequestsAutomatedResponder.answerCertificateStudentRequest(request);

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
