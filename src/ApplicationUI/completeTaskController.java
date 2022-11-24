package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class completeTaskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button completeTaskButton;

    @FXML
    private TableColumn<?, ?> detailColumn;

    @FXML
    private AnchorPane leftBanner;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> taskColumn;

    @FXML
    private TableView<?> taskViewTable;

    @FXML
    private TextField taskIDField;

    @FXML
    private Text errorTextField;
    @FXML
    void goToTeamDashboard(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("teamDashboard.fxml");
        }catch(IOException ie){

        }
    }

    @FXML
    void completeAssignedTask(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert detailColumn != null : "fx:id=\"detailColumn\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert leftBanner != null : "fx:id=\"leftBanner\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert statusColumn != null : "fx:id=\"statusColumn\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert taskColumn != null : "fx:id=\"taskColumn\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert taskViewTable != null : "fx:id=\"taskViewTable\" was not injected: check your FXML file 'completeTask.fxml'.";

    }

}
