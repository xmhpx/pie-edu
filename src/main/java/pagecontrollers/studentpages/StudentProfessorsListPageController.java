package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import models.professor.Professor;


public class StudentProfessorsListPageController extends StudentPageController {

    public static final String fxmlFileName = "studentProfessorsListPage.fxml";

    @FXML
    TableView<Professor> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<Professor> data = tableView.getItems();
        data.clear();
        data.addAll(backend.getProfessors());
    }
}
