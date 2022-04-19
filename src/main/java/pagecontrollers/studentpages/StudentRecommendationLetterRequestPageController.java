package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import models.universityitems.Course;
import models.universityitems.requests.RecommendationLetterRequest;
import models.universityitems.requests.Request;

public class StudentRecommendationLetterRequestPageController extends StudentPageController {
    @FXML
    TableView<RecommendationLetterRequest> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<RecommendationLetterRequest> data = tableView.getItems();
        data.clear();
        for(Request request : backend.getRequests()){
            if(request instanceof RecommendationLetterRequest){
                data.add((RecommendationLetterRequest) request);
            }
        }
    }
}
