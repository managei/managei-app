package ApplicationUI;

import BussinessLogic.dashboard;
import BussinessLogic.team;
import BussinessLogic.user;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class viewTeamController {

    @FXML
    private Button backButton;
    @FXML
    private TableColumn<team, Integer> fypIDColumn;

    @FXML
    private TableColumn<team, String > teamDetailsColumn;

    @FXML
    private TableColumn<team, Integer> teamIdColumn;

    @FXML
    private TableColumn<team, String> teamnameColumn;

    @FXML
    private TableView<team> teamViewTable;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Main.changeScene("adminDashboard.fxml");
    }

    @FXML
    void initialize() {
        ObservableList<team> arr = dashboard.displayAllTeams();
        teamIdColumn.setCellValueFactory(new PropertyValueFactory<team,Integer>("id"));
        fypIDColumn.setCellValueFactory(new PropertyValueFactory<team,Integer>("fypId"));
        teamnameColumn.setCellValueFactory(new PropertyValueFactory<team,String>("name"));
        teamDetailsColumn.setCellValueFactory(new PropertyValueFactory<team,String>("detail"));
        teamViewTable.setItems(arr);
    }

}
