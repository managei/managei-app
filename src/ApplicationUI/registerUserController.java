package ApplicationUI;

import BussinessLogic.dashboard;
import BussinessLogic.user;
import Utils.Printing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;

public class registerUserController {
    private String selectedRole;
    @FXML
    private Label label_info;
    @FXML
    private MenuItem fypLabInstructor;
    @FXML
    private MenuItem admin;
    @FXML
    private TextField firstName_textField;
    @FXML
    private MenuItem headOfDepartment;
    @FXML
    private TextField lastName_textField;
    @FXML
    private TextField password_textField;
    @FXML
    private MenuItem supervisor;
    @FXML
    private MenuButton selectRole_MenuButton;
    @FXML
    private MenuItem teamMember;
    @FXML
    private TextField username_textField;
    @FXML
    void selectRole(ActionEvent event) {
        final MenuItem source = (MenuItem) event.getSource();
        String id = source.getId();
        selectedRole = id;
        selectRole_MenuButton.setText(id);
    }
    @FXML
    void goBack(ActionEvent event) throws IOException {
        Main.changeScene("adminDashboard.fxml");
    }
    @FXML
    void registerUser(ActionEvent event) throws IOException {
        if (firstName_textField.getText().isEmpty() || lastName_textField.getText().isEmpty() || username_textField.getText().isEmpty() || password_textField.getText().isEmpty() || selectedRole.isEmpty()) {
            label_info.setText("Enter Data in all fields");
            label_info.setTextFill(Color.color(1, 0, 0));
        } else {
            label_info.setText("Please Wait...");
            Main.getDashBoard().registerUser(1, username_textField.getText(), firstName_textField.getText(), lastName_textField.getText(), password_textField.getText(), selectedRole);
            Main.initializeLists();
            label_info.setTextFill(Color.color(0, 1, 0));
            label_info.setText("User Saved Successfully");
            firstName_textField.setText("");
            lastName_textField.setText("");
            username_textField.setText("");
            password_textField.setText("");
            selectRole_MenuButton.setText("Select Role");
            selectedRole = "";
        }
    }

}
