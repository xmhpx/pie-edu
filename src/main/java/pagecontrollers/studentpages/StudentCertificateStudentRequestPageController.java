package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.User;
import models.student.Student;
import models.universityitems.requests.CertificateStudentRequest;
import models.universityitems.requests.Request;

public class StudentCertificateStudentRequestPageController extends StudentPageController {
    @FXML
    TableView<CertificateStudentRequest> tableView;

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
                if(request instanceof CertificateStudentRequest)data.add((CertificateStudentRequest) request);
            }
        }
    }
}
