package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
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
    private Button progessGraphButton;
    @FXML
    void completeTask(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("completeTask.fxml");
        }catch(IOException ie){
            ie.printStackTrace();
        }
        Main.initializeLists();
    }

    @FXML
    void suggestNewTask(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("suggestTask.fxml");
        }catch(IOException ie){
            ie.printStackTrace();
        }
        Main.initializeLists();
    }

    @FXML
    void showProgressGraph(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("progressGraph.fxml");
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }
    @FXML
    void viewTeamTasks(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("teamTaskView.fxml");
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

    public void getTeamMemberTasks(){
        dashboard d = new dashboard();
        Integer completedTasks= d.getTeamMemberTasksWithStatus("complete");
        Integer assignedTasks=d.getTeamMemberTasksWithStatus("assigned");
        assignedTasks+=d.getTeamMemberTasksWithStatus("suggested");

        System.out.println("Assigned: " + assignedTasks.toString());
        completedTaskCountLabel.setText(completedTasks.toString());
        assignedTaskCountLabel.setText(assignedTasks.toString());
        userNameLabel.setText(Main.getLoggedInUser().getFirstName() + " " + Main.getLoggedInUser().getLastName());
    }

    @FXML
    void initialize() {
        assert CompletedTaskLabel != null : "fx:id=\"CompletedTaskLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert assignedTaskCountLabel != null : "fx:id=\"assignedTaskCountLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert assignedTaskLabel != null : "fx:id=\"assignedTaskLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert completeTaskButton != null : "fx:id=\"completeTaskButton\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert completedTaskCountLabel != null : "fx:id=\"completedTaskCountLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert leftBanner != null : "fx:id=\"leftBanner\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert progessGraphButton != null : "fx:id=\"progessGraphButton\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert suggestNewTaskButton != null : "fx:id=\"suggestNewTaskButton\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert userLabel != null : "fx:id=\"userLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert userNameLabel != null : "fx:id=\"userNameLabel\" was not injected: check your FXML file 'teamDashboard.fxml'.";
        assert viewOwnTasksButton != null : "fx:id=\"viewOwnTasksButton\" was not injected: check your FXML file 'teamDashboard.fxml'.";

        getTeamMemberTasks();
    }

}
