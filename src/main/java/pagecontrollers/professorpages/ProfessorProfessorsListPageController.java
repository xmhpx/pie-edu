package pagecontrollers.professorpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import logic.Backend;
import models.professor.Professor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class ProfessorProfessorsListPageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorProfessorsListPageController.class);

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
