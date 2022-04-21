package pagecontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.LoggedInUserHolder;

import java.io.IOException;

public class BasicPageController {
    @FXML
    AnchorPane anchorPane;


    protected void goToStudentPage(String str) throws IOException {
        goToPage("/studentpages/"+str);
    }

    protected void goToPage(String str) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        changeScene(scene);
    }

    protected void changeScene(Scene scene){
        Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    protected void logOutButtonClick(MouseEvent mouseEvent) throws IOException {
        LoggedInUserHolder.setUser(null);
        goToPage("/loginPage.fxml");
    }
}
