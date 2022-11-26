package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HODDashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button approveTaskButton;

    @FXML
    private Button createProjectButton;

    @FXML
    private Button deleteProjectButton;

    @FXML
    private ImageView logOutButton;

    @FXML
    private Button updateProjectButton;

    @FXML
    private Button viewProjectButton;

    @FXML
    void approveTask(ActionEvent event) {
        try {
            Main.changeScene("approveTask.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void createProject(ActionEvent event) {
        try {
            Main.changeScene("createProject.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    void logOut(MouseEvent event) {
        Main.logOutUser();
        try {
            Main.changeScene("welcome.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        assert approveTaskButton != null : "fx:id=\"approveTaskButton\" was not injected: check your FXML file 'HODDashboard.fxml'.";
        assert createProjectButton != null : "fx:id=\"createProjectButton\" was not injected: check your FXML file 'HODDashboard.fxml'.";
        assert deleteProjectButton != null : "fx:id=\"deleteProjectButton\" was not injected: check your FXML file 'HODDashboard.fxml'.";
        assert logOutButton != null : "fx:id=\"logOutButton\" was not injected: check your FXML file 'HODDashboard.fxml'.";
        assert updateProjectButton != null : "fx:id=\"updateProjectButton\" was not injected: check your FXML file 'HODDashboard.fxml'.";
        assert viewProjectButton != null : "fx:id=\"viewProjectButton\" was not injected: check your FXML file 'HODDashboard.fxml'.";

    }

}
