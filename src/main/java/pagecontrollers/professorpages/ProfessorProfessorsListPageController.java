package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import models.professor.Professor;


public class ProfessorProfessorsListPageController extends ProfessorPageController {

    public static final String fxmlFileName = "professorProfessorsListPage.fxml";

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
