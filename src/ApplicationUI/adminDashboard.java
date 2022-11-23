package ApplicationUI;

import BussinessLogic.dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class adminDashboard {

    Main m=new Main();
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
    void createTeam(ActionEvent event) {

    }

    @FXML
    void registerUser(ActionEvent event) {

    }

    @FXML
    void viewTeam(ActionEvent event) {

    }

    @FXML
    void viewUsers(ActionEvent event) {

    }
    @FXML
    void logOut(ActionEvent event) throws IOException {
        m.logOutUser();
        m.changeScene("welcome.fxml");
    }

    @FXML
    void initialize() {
        label_name.setText(m.getLoggedUser().getFirstName()+" 👋");
    }
}
