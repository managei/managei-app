package ApplicationUI;

import Utils.Printing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class registerController {
    Main m=new Main();

//    private DBHandler dbh;
    @FXML
    private Button button_login;

    @FXML
    private Button button_register;

    @FXML
    void initialize(){
//        dbh=new DBHandler();
        Printing.PrintStr("Register Page initialized");
    }
    @FXML
    public void goToWelcome(ActionEvent event) throws IOException {
        m.changeScene("welcome.fxml");
    }
}
