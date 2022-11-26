package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BussinessLogic.finalYearProject;
import BussinessLogic.user;
import Utils.Printing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class createTeamController {

    private Integer selectedFYPId;
    private Integer selectedSUPId;
    @FXML
    private Label label_info;

    @FXML
    private MenuButton selectFYP_MenuButton;

    @FXML
    private MenuButton selectSUP_MenuButton;
    @FXML
    private TextField teamName_textField;
    @FXML
    private TextArea teamDetails_textArea;
    @FXML
    void createTeam(ActionEvent event) throws SQLException {
        if(selectedFYPId.equals(null)||selectedSUPId.equals(null)||teamDetails_textArea.getText().isEmpty()||teamName_textField.getText().toString().isEmpty())
        {
            label_info.setText("Please fill all fields");
            label_info.setTextFill(Color.color(1, 0, 0));
        }else{
            Main.getDashBoard().createTeam(Main.getDBHandler(),teamName_textField.getText(),teamDetails_textArea.getText(),selectedFYPId,selectedSUPId);
            label_info.setText("Team Created Successfully");
            label_info.setTextFill(Color.color(0, 1, 0));
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        if(Main.getLoggedUser().getType().equals("admin"))
            Main.changeScene("adminDashBoard.fxml");
        else if(Main.getLoggedUser().getType().equals("supervisor"))
            Main.changeScene("supervisorDashboard.fxml");
        else if(Main.getLoggedUser().getType().equals("headOfDepartment"))
            Main.changeScene("HODDashboard.fxml");
    }

    @FXML
    void initialize() {

        for (finalYearProject u:
                Main.getDashBoard().getFYP()) {
            MenuItem item = new MenuItem(u.getName());
            item.setId(u.getId().toString());
            item.setOnAction(actionEvent -> {
                selectFYP(actionEvent);
            });
            selectFYP_MenuButton.getItems().add(item);
        }
        for (user u:
                Main.getDashBoard().getUsersByType("supervisor")) {
            MenuItem item = new MenuItem(u.getFirstName()+" "+u.getLastName());
            item.setId(u.getId().toString());
            item.setOnAction(actionEvent -> {
                selectSUP(actionEvent);
            });
            selectSUP_MenuButton.getItems().add(item);
        }

    }
    void selectFYP(ActionEvent event) {
        final MenuItem source = (MenuItem) event.getSource();
        String id = source.getId();
        selectedFYPId=Integer.parseInt(id);
        selectFYP_MenuButton.setText(source.getText());
    }
    void selectSUP(ActionEvent event) {
        final MenuItem source = (MenuItem) event.getSource();
        String id = source.getId();
        selectedSUPId=Integer.parseInt(id);
        selectSUP_MenuButton.setText(source.getText());
    }
    @FXML
    void goToCreateFYP(ActionEvent event) throws IOException {
        Main.changeScene("createProject.fxml");
    }
}
