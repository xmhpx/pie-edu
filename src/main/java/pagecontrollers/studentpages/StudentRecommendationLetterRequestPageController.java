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
import models.universityitems.requests.RecommendationLetterRequest;
import models.universityitems.requests.Request;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class StudentRecommendationLetterRequestPageController extends StudentPageController {
    private static final Logger log = LogManager.getLogger(StudentRecommendationLetterRequestPageController.class);

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
                if(request == null){
                    log.error("student("+student.getId()+") has requestId("+requestId+") which doesn't exist");
                    throw new IllegalStateException("student("+student.getId()+") has requestId("+requestId+") which doesn't exist");
                }
                if(request instanceof RecommendationLetterRequest)data.add((RecommendationLetterRequest) request);
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

        User user = LoggedInUserHolder.getUser();
        if(user instanceof Student student) {
            RecommendationLetterRequest request = new RecommendationLetterRequest(title, body, student.getId(), professorId);
            backend.addToRequests(request);
            student.addToRequest(request.getId());
        }
        else{
            log.error("logged in user is not a student");
            throw new IllegalStateException("logged in user is not a student");
        }

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
        } catch (Exception e) {
            log.error("student page '"+fxmlFileName+"' doesn't exist");
            error("some backend problem happened, try again");
        }
    }
}
