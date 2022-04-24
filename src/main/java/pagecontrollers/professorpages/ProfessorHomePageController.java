package pagecontrollers.professorpages;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import logic.LoggedInUserHolder;
import models.UITheme;
import models.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ProfessorHomePageController extends ProfessorPageController {
    private static final Logger log = LogManager.getLogger(ProfessorHomePageController.class);

    public static final String fxmlFileName = "professorHomePage.fxml";


    @FXML
    Button skyBlueButton;

    @FXML
    Button redButton;

    @FXML
    Button darkButton;

    @FXML
    Button purpleButton;


    @Override
    public void initialize(){
        super.initialize();
    }


    @FXML
    void skyBlueButtonOnAction(){
        User user = LoggedInUserHolder.getUser();
        UITheme preferredUITheme = user.getPreferredUITheme();
        if(preferredUITheme == null){
            log.error("user("+user.getId()+") has null preferredUITheme");
            throw new IllegalStateException("user("+user.getId()+") has null preferredUITheme");
        }
        preferredUITheme.changeColorToSkyBlue();
        initialize();
    }

    @FXML
    void redButtonOnAction(){
        User user = LoggedInUserHolder.getUser();
        UITheme preferredUITheme = user.getPreferredUITheme();
        if(preferredUITheme == null){
            log.error("user("+user.getId()+") has null preferredUITheme");
            throw new IllegalStateException("user("+user.getId()+") has null preferredUITheme");
        }
        preferredUITheme.changeColorToRed();
        initialize();
    }

    @FXML
    void darkButtonOnAction(){
        User user = LoggedInUserHolder.getUser();
        UITheme preferredUITheme = user.getPreferredUITheme();
        if(preferredUITheme == null){
            log.error("user("+user.getId()+") has null preferredUITheme");
            throw new IllegalStateException("user("+user.getId()+") has null preferredUITheme");
        }
        preferredUITheme.changeColorToDark();
        initialize();
    }

    @FXML
    void purpleButtonOnAction(){
        User user = LoggedInUserHolder.getUser();
        UITheme preferredUITheme = user.getPreferredUITheme();
        if(preferredUITheme == null){
            log.error("user("+user.getId()+") has null preferredUITheme");
            throw new IllegalStateException("user("+user.getId()+") has null preferredUITheme");
        }
        preferredUITheme.changeColorToPurple();
        initialize();
    }
}
