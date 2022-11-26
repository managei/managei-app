package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import BussinessLogic.team;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class updateTeamController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<team, Integer> fypIDCol;

    @FXML
    private Button goBackButton;

    @FXML
    private Label label_info;

    @FXML
    private MenuButton selectFYP_MenuButton;

    @FXML
    private TableView<team> tableView;

    @FXML
    private TableColumn<team, String> teamDetailCol;

    @FXML
    private TextArea teamDetails_textArea;

    @FXML
    private TableColumn<team, Integer> teamIDCol;

    @FXML
    private TextField teamID_textField;

    @FXML
    private TableColumn<team, String> teamNameCol;

    @FXML
    private TextField teamName_textField;

    @FXML
    private Button updateButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Main.changeScene("adminDashboard.fxml");
    }

    @FXML
    void updateTeam(ActionEvent event) {

    }

    @FXML
    void initialize() {
        ObservableList<team> arr = dashboard.displayAllTeams();
        teamIDCol.setCellValueFactory(new PropertyValueFactory<team,Integer>("id"));
        fypIDCol.setCellValueFactory(new PropertyValueFactory<team,Integer>("fypId"));
        teamNameCol.setCellValueFactory(new PropertyValueFactory<team,String>("name"));
        teamDetailCol.setCellValueFactory(new PropertyValueFactory<team,String>("detail"));
        tableView.setItems(arr);
    }

}
