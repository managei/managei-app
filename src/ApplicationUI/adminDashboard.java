package ApplicationUI;

import BussinessLogic.dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class adminDashboard {

    dashboard d= new dashboard();
    @FXML
    private Label label_name;
    @FXML
    private Button button_createTeam;

    @FXML
    private Button button_logOut;

    @FXML
    private Button button_registerUser;

    @FXML
    private Button button_viewTeams;

    @FXML
    private Button button_viewUser;

    @FXML
    void createTeam(ActionEvent event) throws IOException {
        Main.changeScene("createTeamPage");
    }

    @FXML
    void registerUser(ActionEvent event) throws IOException {
        Main.changeScene("registerUserPage.fxml");
    }

    @FXML
    void viewTeam(ActionEvent event) throws IOException {
        Main.changeScene("viewTeamPage");
    }

    @FXML
    void viewUsers(ActionEvent event) throws IOException {
        Main.changeScene("viewUsersPage");
    }
    @FXML
    void logOut(ActionEvent event) throws IOException {
        Main.logOutUser();
        Main.changeScene("welcome.fxml");
    }

    @FXML
    void initialize() {
        label_name.setText(Main.getLoggedUser().getFirstName()+" 👋");
    }
}
