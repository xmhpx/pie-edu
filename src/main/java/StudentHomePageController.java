import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentHomePageController {

    @FXML
    AnchorPane anchorPane;


    void changeScene(Scene scene){
        Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentHomePage.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        changeScene(scene);
    }
}
