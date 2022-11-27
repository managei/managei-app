package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import DBHandler.PersistantHandlerClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class createProjectController {

    PersistantHandlerClass dbh=null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text createErrorMsg;

    @FXML
    private Button createProjectButton;

    @FXML
    private Text errorTextField;

    @FXML
    private TextField fypNameField;

    @FXML
    private TextField fypStatusField;

    @FXML
    private Button goBackButton;

    @FXML
    private Label taskNameLabel;

    @FXML
    private Label taskNameLabel1;

    @FXML
    void createProject(ActionEvent event) {
        dashboard d = new dashboard();
        String fypName=fypNameField.getText();
        String fypStatus=fypStatusField.getText();

        if(fypName.equals("") || fypStatus.equals("")){
            createErrorMsg.setText("Please fill all boxes");
            return;
        }
        d.createProject(dbh,fypName,fypStatus);

        // DONE: Faraz will add create Team after this (called on click of next button)
        try {
            Main.changeScene("createTeamPage.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void gotoSupervisorDashboard(ActionEvent event) throws IOException {
        if(Main.getLoggedUser().getType().equals("admin"))
            Main.changeScene("adminDashBoard.fxml");
        else if(Main.getLoggedUser().getType().equals("supervisor"))
            Main.changeScene("supervisorDashboard.fxml");
        else if(Main.getLoggedUser().getType().equals("headOfDepartment"))
            Main.changeScene("HODDashboard.fxml");
    }
    @FXML
    void initialize() {
        assert createErrorMsg != null : "fx:id=\"createErrorMsg\" was not injected: check your FXML file 'createProject.fxml'.";
        assert createProjectButton != null : "fx:id=\"createProjectButton\" was not injected: check your FXML file 'createProject.fxml'.";
        assert errorTextField != null : "fx:id=\"errorTextField\" was not injected: check your FXML file 'createProject.fxml'.";
        assert fypNameField != null : "fx:id=\"fypNameField\" was not injected: check your FXML file 'createProject.fxml'.";
        assert fypStatusField != null : "fx:id=\"fypStatusField\" was not injected: check your FXML file 'createProject.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'createProject.fxml'.";
        assert taskNameLabel != null : "fx:id=\"taskNameLabel\" was not injected: check your FXML file 'createProject.fxml'.";
        assert taskNameLabel1 != null : "fx:id=\"taskNameLabel1\" was not injected: check your FXML file 'createProject.fxml'.";

        dbh= new PersistantHandlerClass();
        createErrorMsg.setText("");
    }

}
