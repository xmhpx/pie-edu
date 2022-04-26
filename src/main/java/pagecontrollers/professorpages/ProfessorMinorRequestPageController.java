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
import javafx.util.converter.BooleanStringConverter;
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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

public class ProfessorMinorRequestPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorMinorRequestPageController.class);

    public static final String fxmlFileName = "professorMinorRequestPage.fxml";

    @FXML
    TableView<MinorRequest> tableView;


    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<MinorRequest> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor professor) {
            if(professor.getProfessorType() != ProfessorType.EDUCATIONAL_ASSISTANT){
                log.error("logged in user is not a EDUCATIONAL_ASSISTANT");
                throw new IllegalStateException("logged in user is not a EDUCATIONAL_ASSISTANT");
            }
            for (Request request : backend.getRequests()) {
                if (request instanceof MinorRequest minorRequest){
                    int destinationCollegeId = minorRequest.getDestinationCollegeId();
                    Student student = backend.getStudent(minorRequest.getSenderId());
                    if(student == null){
                        log.error("minorRequest("+minorRequest.getId()+")'s student doesn't exist");
                        throw new IllegalStateException("minorRequest("+minorRequest.getId()+")'s student doesn't exist");
                    }
                    int originCollegeId = student.getCollegeId();
                    if(destinationCollegeId == professor.getCollegeId() || originCollegeId == professor.getCollegeId()) {
                        data.add((MinorRequest) request);
                    }
                }
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }



        tableView.setEditable(true);


        TableColumn<MinorRequest, Boolean> hasOriginCollegeAgreedColumn = new TableColumn<>("Has Origin College Agreed");
        hasOriginCollegeAgreedColumn.setMaxWidth(250);
        hasOriginCollegeAgreedColumn.setPrefWidth(100);
        hasOriginCollegeAgreedColumn.setCellValueFactory(new PropertyValueFactory<>("hasOriginCollegeAgreed"));
        hasOriginCollegeAgreedColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
        hasOriginCollegeAgreedColumn.setOnEditCommit(event -> {
            MinorRequest minorRequest = event.getRowValue();
            if(professor.getCollegeId() == minorRequest.getOriginCollegeId()) {
                minorRequest.setHasOriginCollegeAgreed(event.getNewValue());
            }
        });

        TableColumn<MinorRequest, Boolean> hasDestinationCollegeAgreedColumn = new TableColumn<>("Has Dest. College Agreed");
        hasDestinationCollegeAgreedColumn.setMaxWidth(250);
        hasDestinationCollegeAgreedColumn.setPrefWidth(100);
        hasDestinationCollegeAgreedColumn.setCellValueFactory(new PropertyValueFactory<>("hasDestinationCollegeAgreed"));
        hasDestinationCollegeAgreedColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
        hasDestinationCollegeAgreedColumn.setOnEditCommit(event -> {
            MinorRequest minorRequest = event.getRowValue();
            if(professor.getCollegeId() == minorRequest.getDestinationCollegeId()) {
                minorRequest.setHasDestinationCollegeAgreed(event.getNewValue());
            }
        });

        tableView.getColumns().add(hasOriginCollegeAgreedColumn);
        tableView.getColumns().add(hasDestinationCollegeAgreedColumn);
    }
}
