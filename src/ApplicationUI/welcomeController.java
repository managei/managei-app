package ApplicationUI;

import BussinessLogic.dashboard;
import BussinessLogic.user;
import Utils.Printing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class welcomeController {
    dashboard d= new dashboard();
//    private DBHandler dbh;
    @FXML
    private TextField textBox_userName;
    @FXML
    private TextField textBox_password;
    @FXML
    private Label label_info;
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
    public void login(ActionEvent event) throws IOException {
        if (textBox_userName.getText().isEmpty() || textBox_password.getText().isEmpty())
        {
            label_info.setText("Enter Data in all fields");
            label_info.setTextFill(Color.color(1, 0, 0));

        } else {
            label_info.setText("Please Wait...");
            user u = d.loginUser(textBox_userName.getText(),textBox_password.getText());
            if(u!=null) {
                Main.setLoggedInUser(u);
                Main.gotoDashboard(u.getType());
            }else {
                label_info.setText("Username or Password, does not match");
                label_info.setTextFill(Color.color(1, 0, 0));
            }
        }
    }
    @FXML
    public void goToRegister(ActionEvent event) throws IOException {
        Main.changeScene("register.fxml");
    }
}
