package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import DBHandler.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    void createTask(ActionEvent event) throws IOException {
        Main.changeScene("createTask.fxml");
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
    private ImageView logOutButton;

    @FXML
    private Button meetingButton;

    @FXML
    void logOut(MouseEvent event) {
        Main.logOutUser();
        try {
            Main.changeScene("welcome.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void modifyTeam(ActionEvent event) throws IOException {
        Main.changeScene("manageMembersPage.fxml");
    }

    @FXML
    void scheduleFypMeeting(ActionEvent event) throws IOException {
        Main.changeScene("createMeeting.fxml");
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
    void updateTask(ActionEvent event) throws IOException {
        Main.changeScene("updateTask.fxml");
    }
    @FXML
    void approveTask(ActionEvent event) throws IOException {
        Main.changeScene("approveTask.fxml");
    }

    @FXML
    void initialize() {
    }

}
