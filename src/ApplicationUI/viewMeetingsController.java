package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BussinessLogic.meetingSchedule;
import BussinessLogic.team;
import DBHandler.DBHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewMeetingsController {

    private DBHandler dbh;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableView<meetingSchedule> ViewTable;

    @FXML
    private TableColumn<meetingSchedule, String> dateColumn;

    @FXML
    private TableColumn<meetingSchedule, String> detailsColumn;

    @FXML
    private TableColumn<meetingSchedule, Integer> instructorIDColumn;

    @FXML
    private TableColumn<meetingSchedule, String> locationColumn;

    @FXML
    private TableColumn<meetingSchedule, Integer> meetingIDColumn;

    @FXML
    private TableColumn<meetingSchedule, String> nameColumn;

    @FXML
    private TableColumn<meetingSchedule, Integer> supervisorIDColumn;

    @FXML
    private TableColumn<meetingSchedule, Integer> teamIDColumn;

    @FXML
    private TableColumn<meetingSchedule, String> timeColumn;
    @FXML
    void goBack(ActionEvent event) {
        if(Main.getLoggedInUser().getType().equals("headOfDepartment")){
            try {
                Main.changeScene("HODDashboard.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                Main.changeScene("supervisorDashboard.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void initialize() {
        ObservableList<meetingSchedule> arr = Main.getDashBoard().getMeetings(Main.getDBHandler());
        meetingIDColumn.setCellValueFactory(new PropertyValueFactory<meetingSchedule,Integer>("id"));
        instructorIDColumn.setCellValueFactory(new PropertyValueFactory<meetingSchedule,Integer>("instructorId"));
        supervisorIDColumn.setCellValueFactory(new PropertyValueFactory<meetingSchedule,Integer>("supervisorId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<meetingSchedule,String>("meetingName"));
        teamIDColumn.setCellValueFactory(new PropertyValueFactory<meetingSchedule, Integer>("teamId"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<meetingSchedule,String>("details"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<meetingSchedule,String>("location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<meetingSchedule,String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<meetingSchedule,String>("time"));
        ViewTable.setItems(arr);
    }

}
