package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import models.universityitems.requests.MinorRequest;
import models.universityitems.requests.Request;

public class StudentMinorPageController extends StudentPageController {
    @FXML
    TableView<MinorRequest> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<MinorRequest> data = tableView.getItems();
        data.clear();
        for(Request request : backend.getRequests()){
            if(request instanceof MinorRequest){
                data.add((MinorRequest) request);
            }
        }
    }
}
