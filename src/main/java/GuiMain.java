import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Backend;
import logic.LoggedInUserHolder;
import models.student.Student;
import models.student.StudentLevel;

import java.io.IOException;

public class GuiMain extends Application{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentpages/studentHomePage.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        LoggedInUserHolder.setUser(new Student("password", "name", 0, 0, "400109221", StudentLevel.PHD_STUDENT, "1400", 17, "0927132036"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws IOException {
        Backend.getInstance().save();
    }

}
