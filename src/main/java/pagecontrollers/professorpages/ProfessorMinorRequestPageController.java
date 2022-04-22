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
import models.professor.ProfessorType;
import models.student.Student;
import models.universityitems.Field;
import models.universityitems.requests.MinorRequest;
import models.universityitems.requests.RecommendationLetterRequest;
import models.universityitems.requests.Request;

import java.io.IOException;
import java.util.ArrayList;

public class ProfessorMinorRequestPageController extends ProfessorPageController {

    public static final String fxmlFileName = "professorMinorRequestPage.fxml";

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
        if(user instanceof Professor professor) {
            if(professor.getProfessorType() == ProfessorType.EDUCATIONAL_ASSISTANT) {
                for (Request request : backend.getRequests()) {
                    if (request instanceof MinorRequest minorRequest){
                        int destinationCollegeId = minorRequest.getDestinationCollegeId();
                        Student student = backend.getStudent(minorRequest.getSenderId());
                        int originCollegeId = student.getCollegeId();
                        if(destinationCollegeId == professor.getCollegeId() || originCollegeId == professor.getCollegeId()) {
                            data.add((MinorRequest) request);
                        }
                    }
                }
            }
        }
        tableView.setEditable(true);

        TableColumn<MinorRequest, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMaxWidth(200);
        statusColumn.setPrefWidth(70);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<MinorRequest, String> responseColumn = new TableColumn<>("Response");
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

        for(Field field : fields){
            if(field.getCollegeId() == destinationCollegeId && field.getId() == minorFieldId){
                MinorRequest request = new MinorRequest(title, body, LoggedInUserHolder.getUser().getId(), destinationCollegeId, minorFieldId);
                backend.addToRequests(request);
                Student student = backend.getStudent(request.getSenderId());
                student.addToRequest(request.getId());

                reload();
                return;
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
        try {
            goToStudentPage(fxmlFileName);
        } catch (IOException e) {
            error("some backend problem happened, try again");
        }
    }
}
