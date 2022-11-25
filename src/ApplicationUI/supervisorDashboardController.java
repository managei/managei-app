package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBHandler.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class supervisorDashboardController {

    DBHandler dbh=null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_logOut;

    @FXML
    private Button createProjectButton;

    @FXML
    private Button createTaskButton;

    @FXML
    private Button deleteProjectButton;

    @FXML
    private Button generateReportButton;

    @FXML
    private Button modifyTeamButton;

    @FXML
    private Button updateProjectButton;

    @FXML
    private Button viewProjectButton;

    @FXML
    void createProject(ActionEvent event) {
//        Main m = new Main();
        try {
            Main.changeScene("createProject.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void createTask(ActionEvent event) {

    }

    @FXML
    void deleteProject(ActionEvent event) {
        try {
            Main.changeScene("deleteProject.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void generateProjectProgressReport(ActionEvent event) {
//        Main m= new Main();
        try {
            Main.changeScene("progressReport.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        Main.logOutUser();
        try {
            Main.changeScene("welcome.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void modifyTeam(ActionEvent event) {

    }

    @FXML
    void updateProject(ActionEvent event) {
        try {
            Main.changeScene("updateProject.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void viewProjects(ActionEvent event) {
        try {
            Main.changeScene("viewProjects.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        assert button_logOut != null : "fx:id=\"button_logOut\" was not injected: check your FXML file 'supervisorDashboard.fxml'.";
        assert createProjectButton != null : "fx:id=\"createProjectButton\" was not injected: check your FXML file 'supervisorDashboard.fxml'.";
        assert createTaskButton != null : "fx:id=\"createTaskButton\" was not injected: check your FXML file 'supervisorDashboard.fxml'.";
        assert deleteProjectButton != null : "fx:id=\"deleteProjectButton\" was not injected: check your FXML file 'supervisorDashboard.fxml'.";
        assert generateReportButton != null : "fx:id=\"generateReportButton\" was not injected: check your FXML file 'supervisorDashboard.fxml'.";
        assert modifyTeamButton != null : "fx:id=\"modifyTeamButton\" was not injected: check your FXML file 'supervisorDashboard.fxml'.";
        assert updateProjectButton != null : "fx:id=\"updateProjectButton\" was not injected: check your FXML file 'supervisorDashboard.fxml'.";
        assert viewProjectButton != null : "fx:id=\"viewProjectButton\" was not injected: check your FXML file 'supervisorDashboard.fxml'.";

        dbh= new DBHandler();
    }

}
