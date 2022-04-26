package pagecontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.LoggedInUserHolder;
import models.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Calendar;

public class BasicPageController {
    private static final Logger log = LogManager.getLogger(BasicPageController.class);

    private static final int secondsUntilForcedPassword = 60*60*3;

    @FXML
    protected AnchorPane anchorPane;


    public void initialize(){

    }

    protected void goToForcedChangePasswordPage(){
        loadPage("/forcedChangePasswordPage.fxml");
    }

    protected void goToLoginPage(){
        loadPage("/loginPage.fxml");
    }

    protected void goToStudentPage(String str){
        goToPage("/studentpages/"+str);
    }

    protected void goToProfessorPage(String str) {
        goToPage("/professorpages/"+str);
    }

    protected void goToPage(String str){
        if (this instanceof LoggedInPageController) {
            if(!LoggedInUserHolder.hasUser())return;

            Calendar cal = Calendar.getInstance();

            int second = cal.get(Calendar.SECOND);
            int minute = cal.get(Calendar.MINUTE);
            int hour = cal.get(Calendar.HOUR);

            String[] lastVisitTime = LoggedInUserHolder.getUser().getLastVisit().split(":");
            int second2 = Integer.parseInt(lastVisitTime[2]);
            int minute2 = Integer.parseInt(lastVisitTime[1]);
            int hour2 = Integer.parseInt(lastVisitTime[0]);

            int deltaSeconds = (second - second2) + (minute - minute2) * 60 + (hour - hour2) * 60 * 60;
            if (deltaSeconds > secondsUntilForcedPassword) {
                goToForcedChangePasswordPage();
                return;
            }
        }
        loadPage(str);
    }

    protected void loadPage(String str){

        Parent root;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
            root = loader.load();
        }
        catch (Exception exception){
            log.error("couldn't load fxml file '"+str+"'");
            throw new RuntimeException("couldn't load fxml file '"+str+"'");
        }

        Scene scene = new Scene(root);
        changeScene(scene);
    }

    protected void changeScene(Scene scene){
        Stage primaryStage = (Stage) anchorPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
