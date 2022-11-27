package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BussinessLogic.*;
import BussinessLogic.teamMember;
import Utils.Printing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class manageMembersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addMemberButton;

    @FXML
    private TableView<teamMember> allMembersTableView;

    @FXML
    private Button deleteMemberButton;

    @FXML
    private Button goBackButton;

    @FXML
    private Label label_info;

    @FXML
    private TableColumn<teamMember, Integer> memberIDCol;

    @FXML
    private TextField memberID_textField;

    @FXML
    private TableColumn<teamMember, String > memberNameIDCol;

    @FXML
    private TableColumn<teamMember, Integer> teamIDCol;


    @FXML
    private TextField teamID_textField;

    @FXML
    void addMember(ActionEvent event) {
        if(teamID_textField.getText().isEmpty()||memberID_textField.getText().isEmpty())
        {
            label_info.setText("Please fill all fields");
            label_info.setTextFill(Color.color(1, 0, 0));
        }else {
            Integer memberId=Integer.parseInt(memberID_textField.getText());
            Integer teamId=Integer.parseInt(teamID_textField.getText());
            if(Main.getDashBoard().addTeamMember(memberId,teamId).equals("Operation Success"))
            {
                label_info.setText("Add to Team Successfully");
                label_info.setTextFill(Color.color(0, 1, 0));
                memberID_textField.setText("");
                teamID_textField.setText("");
                initialize();
            }
            else
            {
                label_info.setText("Add to Team Failed.");
                label_info.setTextFill(Color.color(1, 0, 0));
            }
        }
    }

    @FXML
    void deleteMember(ActionEvent event) {
        if(teamID_textField.getText().isEmpty()||memberID_textField.getText().isEmpty())
        {
            label_info.setText("Please fill all fields");
            label_info.setTextFill(Color.color(1, 0, 0));
        }else {
            Integer memberId=Integer.parseInt(memberID_textField.getText());
            Integer teamId=Integer.parseInt(teamID_textField.getText());
            if(Main.getDashBoard().removeTeamMember(memberId,teamId).equals("Operation Success"))
            {
                label_info.setText("Removed from Team Successfully");
                label_info.setTextFill(Color.color(0, 1, 0));
                memberID_textField.setText("");
                teamID_textField.setText("");
                initialize();
            }
            else
            {
                label_info.setText("Remove from Team Failed.");
                label_info.setTextFill(Color.color(1, 0, 0));
            }
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        if(Main.getLoggedUser().getType().equals("admin"))
            Main.changeScene("adminDashBoard.fxml");
        else if(Main.getLoggedUser().getType().equals("headOfDepartment"))
            Main.changeScene("HODDashboard.fxml");
        else if(Main.getLoggedUser().getType().equals("supervisor"))
            Main.changeScene("supervisorDashboard.fxml");
    }

    @FXML
    void initialize() {
        ObservableList<teamMember> data = FXCollections.observableArrayList();
        for (teamMember t:dashboard.getTeamMembersList()) {
            Printing.PrintStr(t.getTeamId().toString());
            data.add(t);
        }
        ObservableList<teamMember> arr = data;
        memberIDCol.setCellValueFactory(new PropertyValueFactory<teamMember,Integer>("id"));
        memberNameIDCol.setCellValueFactory(new PropertyValueFactory<teamMember,String>("userName"));
        teamIDCol.setCellValueFactory(new PropertyValueFactory<teamMember,Integer>("teamId"));
        allMembersTableView.setItems(arr);
    }

}
