package ApplicationUI;

import DBHandler.DBHandler;
import Utils.Printing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class welcomeController {
    Main m=new Main();

//    private DBHandler dbh;
    @FXML
    private TextField textBox_userName;
    @FXML
    private TextField textBox_password;
    @FXML
    private Button button_login;
    @FXML
    private Button button_register;

    @FXML
    void initialize(){
//        dbh=new DBHandler();
        Printing.PrintStr("Welcome Page initialized");
    }
    @FXML
    public void goToRegister(ActionEvent event) throws IOException {
        m.changeScene("register.fxml");
    }
}
