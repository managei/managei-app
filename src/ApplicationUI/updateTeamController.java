package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import BussinessLogic.finalYearProject;
import BussinessLogic.team;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class updateTeamController {
    private Integer selectedFYPId;

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
    void updateTeam(ActionEvent event) throws SQLException {
        if(selectedFYPId.equals(null)||teamID_textField.getText().isEmpty()||teamDetails_textArea.getText().isEmpty()||teamName_textField.getText().toString().isEmpty())
        {
            label_info.setText("Please fill all fields");
            label_info.setTextFill(Color.color(1, 0, 0));
        }else{
            Main.getDashBoard().updateTeam(Main.getDBHandler(),Integer.parseInt(teamID_textField.getText()),teamName_textField.getText(),teamDetails_textArea.getText(),selectedFYPId);
            label_info.setText("Team Updated Successfully");
            label_info.setTextFill(Color.color(0, 1, 0));
            initialize();
        }
    }

    @FXML
    void initialize() {
        ObservableList<team> arr = dashboard.displayAllTeams();
        teamIDCol.setCellValueFactory(new PropertyValueFactory<team,Integer>("id"));
        fypIDCol.setCellValueFactory(new PropertyValueFactory<team,Integer>("fypId"));
        teamNameCol.setCellValueFactory(new PropertyValueFactory<team,String>("name"));
        teamDetailCol.setCellValueFactory(new PropertyValueFactory<team,String>("detail"));
        tableView.setItems(arr);

        for (finalYearProject u:
                Main.getDashBoard().getFYP()) {
            MenuItem item = new MenuItem(u.getName());
            item.setId(u.getId().toString());
            item.setOnAction(actionEvent -> {
                selectFYP(actionEvent);
            });
            selectFYP_MenuButton.getItems().add(item);
        }
    }
    void selectFYP(ActionEvent event) {
        final MenuItem source = (MenuItem) event.getSource();
        String id = source.getId();
        selectedFYPId=Integer.parseInt(id);
        selectFYP_MenuButton.setText(source.getText());
    }
}
