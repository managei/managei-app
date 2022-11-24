package ApplicationUI;
import BussinessLogic.*;
import Utils.Printing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class registerController {
    dashboard d= new dashboard();
//    private DBHandler dbh;
    @FXML
    private Label label_info;
    @FXML
    private TextField textBox_firstName;
    @FXML
    private TextField textBox_lastName;
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
        Printing.PrintStr("Register Page initialized");
    }
    @FXML
    public void registerAdmin(ActionEvent event) throws IOException {
        if (textBox_firstName.getText().isEmpty() || textBox_lastName.getText().isEmpty() || textBox_userName.getText().isEmpty()||textBox_password.getText().isEmpty())
        {
            label_info.setText("Enter Data in all fields");
            label_info.setTextFill(Color.color(1, 0, 0));

        } else {
            label_info.setText("Please Wait...");
            user u = d.registerUser(1,textBox_userName.getText(),textBox_firstName.getText(),textBox_lastName.getText(),textBox_password.getText(),"admin");
            Main.setLoggedInUser(u);
            Main.gotoDashboard(u.getUserType());
        }
    }

    @FXML
    public void goToWelcome(ActionEvent event) throws IOException {
        Main.changeScene("welcome.fxml");
    }
}
