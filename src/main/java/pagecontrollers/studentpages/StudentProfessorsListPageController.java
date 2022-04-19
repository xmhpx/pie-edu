package pagecontrollers.studentpages;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import logic.Backend;
import models.professor.Professor;
import models.professor.ProfessorLevel;
import models.professor.ProfessorType;
import models.universityitems.Course;

import java.io.IOException;

public class StudentProfessorsListPageController extends StudentPageController {

    @FXML
    TableView<Professor> tableView;

    @Override
    public void initialize(){
        super.initialize();
        Backend backend = Backend.getInstance();
        ObservableList<Professor> data = tableView.getItems();
        data.clear();
        backend.addProfessor(new Professor("password", "MohammadHossein Paydar",
                60001, 50001, "123456", ProfessorLevel.FULL_PROFESSOR,
                ProfessorType.DEAN_OF_THE_FACULTY, -17));
        data.addAll(backend.getProfessors());
    }
}
