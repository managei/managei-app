package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class deleteProjectController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text createErrorMsg;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField fypIDTextField;

    @FXML
    private Button goBackButton;

    @FXML
    private Label taskNameLabel2;

    @FXML
    void deleteProject(ActionEvent event) {
        if(fypIDTextField.getText().equals("")){
            createErrorMsg.setText("No ID Entered");
            return;
        }
        dashboard d = new dashboard();
        try {
            d.deleteProject(fypIDTextField.getText());
        } catch (SQLException e) {
            createErrorMsg.setText("No such ID");
            return;
        }
        Main.initializeLists();
    }

    @FXML
    void gotoSupervisorDashboard(ActionEvent event) {
        try {
            Main.changeScene("supervisorDashboard.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        assert createErrorMsg != null : "fx:id=\"createErrorMsg\" was not injected: check your FXML file 'deleteProject.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'deleteProject.fxml'.";
        assert fypIDTextField != null : "fx:id=\"fypIDTextField\" was not injected: check your FXML file 'deleteProject.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'deleteProject.fxml'.";
        assert taskNameLabel2 != null : "fx:id=\"taskNameLabel2\" was not injected: check your FXML file 'deleteProject.fxml'.";

    }

}
