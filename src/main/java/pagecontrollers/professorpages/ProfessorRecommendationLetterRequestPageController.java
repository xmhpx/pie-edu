package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import server.logic.Backend;
import models.LoggedInUserHolder;
import models.User;
import models.professor.Professor;
import models.universityitems.requests.RecommendationLetterRequest;
import models.universityitems.requests.Request;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProfessorRecommendationLetterRequestPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorRecommendationLetterRequestPageController.class);

    public static final String fxmlFileName = "professorRecommendationLetterRequestPage.fxml";

    @FXML
    TableView<RecommendationLetterRequest> tableView;


    @Override
    public void initialize(){
        super.initialize();
        tableView.setEditable(true);
        Backend backend = Backend.getInstance();
        ObservableList<RecommendationLetterRequest> data = tableView.getItems();
        data.clear();
        User user = LoggedInUserHolder.getUser();
        if(user instanceof Professor professor) {
            for(Request request : backend.getRequests()) {
                if(request instanceof RecommendationLetterRequest recommendationLetterRequest){
                    if(recommendationLetterRequest.getProfessorId() == professor.getId()) {
                        data.add(recommendationLetterRequest);
                    }
                }
            }
        }
        else{
            log.error("logged in user is not a Professor");
            throw new IllegalStateException("logged in user is not a Professor");
        }


        tableView.setEditable(true);

        TableColumn<RecommendationLetterRequest, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setMaxWidth(200);
        statusColumn.setPrefWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        statusColumn.setOnEditCommit(event -> {
            RecommendationLetterRequest recommendationLetterRequest = event.getRowValue();
            recommendationLetterRequest.setStatus(event.getNewValue());
        });


        TableColumn<RecommendationLetterRequest, String> responseColumn = new TableColumn<>("Response");
        responseColumn.setMaxWidth(500);
        responseColumn.setPrefWidth(100);
        responseColumn.setCellValueFactory(new PropertyValueFactory<>("response"));
        responseColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        responseColumn.setOnEditCommit(event -> {
            RecommendationLetterRequest recommendationLetterRequest = event.getRowValue();
            recommendationLetterRequest.setResponse(event.getNewValue());
        });

        tableView.getColumns().add(statusColumn);
        tableView.getColumns().add(responseColumn);
    }
}
