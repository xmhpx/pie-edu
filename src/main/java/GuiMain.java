import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.Captcha;
import models.ClassTime;
import models.Time;
import models.WeekDay;
import models.professor.Professor;
import models.professor.ProfessorLevel;
import models.professor.ProfessorType;
import models.student.Student;
import models.student.StudentLevel;
import models.universityitems.Course;

import java.io.IOException;
import java.util.ArrayList;

public class GuiMain extends Application{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Backend backend = Backend.getInstance();
//        backend.addToStudents(new Student("password", "MohammadHossein Paydar", 1, 1, "400109221", StudentLevel.UNDERGRADUATE, "1400", 17, "0927132036"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/professorpages/professorHomePage.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
//        LoggedInUserHolder.setUser(new Professor("password", "name", 1, 1, "400109222", ProfessorLevel.FULL_PROFESSOR, ProfessorType.NORMAL, 17, "0927132036"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws IOException {
        Backend.getInstance().save();
    }

}
