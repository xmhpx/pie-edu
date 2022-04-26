import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Backend;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;


// DONE add ability to add new users by educational assistant
//// DONE add filters to 'coursesListPage' and 'professorsListPage'
// DONE update 'editProfessorPage' for deanOfFaculty (add ability to remove/edit)
// DONE add "add/remove/edit" abilities to 'coursesListPage' for educational assistant and add class time
//// DONE check and update different(possibly hidden) request pages
//// TODO maybe change stuff related to request responses?
//// TODO both ends of a minor request must agree, fix somehow
//// DONE make withdrawal request actually functional
//// DONE add auto response for dorm and certificate student requests
//// TODO change dissertation defence request's response (add time to defence maybe?)
// DONE add ability to add objections and edit them to temporaryScoresPage
// TODO fix many things in temporaryScoresPage (doesn't exist for professors, only for educational assistants)
//// DONE add 'number of passed SCHs' to education status for students
//// TODO make calculating average weighted for different courses
//// DONE add theme changer (just change background color maybe)
//// DONE add default profile image and make profile images to work
//// TODO change password page?? (more information is in the last pages of the doc)
//// DONE add ability to change profile pictures
//// TODO maybe add ability to edi 'homeAddress' and 'postalCode'
//// TODO maybe make a page to add/edit/remove class times


public class GuiMain extends Application{
    private static final Logger log = LogManager.getLogger(GuiMain.class);

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        log.info("application started");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws IOException {
        Backend.getInstance().save();
        log.info("application stopped");
    }
}
