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
import models.universityitems.requests.CertificateStudentRequest;
import models.universityitems.requests.RecommendationLetterRequest;
import models.universityitems.requests.Request;

import java.io.IOException;

public class StudentRecommendationLetterRequestPageController extends StudentPageController {

    public static final String fxmlFileName = "studentRecommendationLetterRequestPage.fxml";

    @FXML
    TextField titleTextField;

    @FXML
    TextField bodyTextField;

    @FXML
    TextField professorIdTextField;

    @FXML
    Button addRequestButton;

    @FXML
    Text errorText;

    @FXML
    TableView<RecommendationLetterRequest> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<RecommendationLetterRequest> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            for(int requestId : student.getRequestIds()) {
                Request request = backend.getRequest(requestId);
                if(request instanceof RecommendationLetterRequest)data.add((RecommendationLetterRequest) request);
            }
        }
    }


    @FXML
    void addRequestButtonOnAction(ActionEvent actionEvent) {
        Backend backend = Backend.getInstance();

        String title = titleTextField.getText();
        String body = bodyTextField.getText();
        int professorId;

        if(title.equals("")){
            error("empty title is not allowed");
            return;
        }

        try {
            professorId = Integer.parseInt(professorIdTextField.getText());
        }
        catch (NumberFormatException numberFormatException){
            error("professor id must be an integer");
            return;
        }

        if(!backend.hasProfessor(professorId)){
            error("professor doesn't exist");
            return;
        }

        RecommendationLetterRequest request = new RecommendationLetterRequest(title, body, LoggedInUserHolder.getUser().getId(), professorId);
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
        professorIdTextField.setText("");
    }

    private void reload(){
        try {
            goToStudentPage(fxmlFileName);
        } catch (IOException e) {
            error("some backend problem happened, try again");
        }
    }
}
