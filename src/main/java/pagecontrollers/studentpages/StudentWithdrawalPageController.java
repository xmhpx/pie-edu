package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.universityitems.requests.Request;
import models.universityitems.requests.WithdrawalRequest;

public class StudentWithdrawalPageController extends StudentPageController {

    public static final String fxmlFileName = "studentWithdrawalPage.fxml";

    @FXML
    TableView<WithdrawalRequest> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<WithdrawalRequest> data = tableView.getItems();
        data.clear();
        for(Request request : backend.getRequests()){
            if(request instanceof WithdrawalRequest && request.getSenderId() == LoggedInUserHolder.getUser().getId()){
                data.add((WithdrawalRequest) request);
            }
        }
    }
}
