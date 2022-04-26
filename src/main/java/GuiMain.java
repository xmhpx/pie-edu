import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Backend;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;


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
