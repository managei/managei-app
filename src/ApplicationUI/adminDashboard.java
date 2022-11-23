package ApplicationUI;

import BussinessLogic.dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class adminDashboard {

    Main m=new Main();
    dashboard d= new dashboard();
    @FXML
    private Button button_logOut;

    @FXML
    void logOut(ActionEvent event) throws IOException {
        m.logOutUser();
        m.changeScene("welcome.fxml");
    }

    @FXML
    void initialize() {

    }
}
