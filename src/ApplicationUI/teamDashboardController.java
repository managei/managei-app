package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class teamDashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label CompletedTaskLabel;

    @FXML
    private Label assignedTaskCountLabel;

    @FXML
    private Label assignedTaskLabel;

    @FXML
    private Button completeTaskButton;

    @FXML
    private Label completedTaskCountLabel;

    @FXML
    private AnchorPane leftBanner;

    @FXML
    private Button suggestNewTaskButton;

    @FXML
    private Button teamTaskButton;

    @FXML
    private Label userLabel;

    @FXML
    private Label userNameLabel;

    @FXML
    private Button viewOwnTasksButton;

    @FXML
    void completeTask(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("completeTask.fxml");
        }catch(IOException ie){

        }
    }

    @FXML
    void suggestNewTask(ActionEvent event) {

    }

    @FXML
    void viewOwnTasks(ActionEvent event) {

    }

    @FXML
    void viewTeamTasks(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert CompletedTaskLabel != null : "fx:id=\"CompletedTaskLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert assignedTaskCountLabel != null : "fx:id=\"assignedTaskCountLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert assignedTaskLabel != null : "fx:id=\"assignedTaskLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert completeTaskButton != null : "fx:id=\"completeTaskButton\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert completedTaskCountLabel != null : "fx:id=\"completedTaskCountLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert leftBanner != null : "fx:id=\"leftBanner\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert suggestNewTaskButton != null : "fx:id=\"suggestNewTaskButton\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert teamTaskButton != null : "fx:id=\"teamTaskButton\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert userLabel != null : "fx:id=\"userLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert userNameLabel != null : "fx:id=\"userNameLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert viewOwnTasksButton != null : "fx:id=\"viewOwnTasksButton\" was not injected: check your FXML file 'teamDashboard.fxml'.";

    }

}
