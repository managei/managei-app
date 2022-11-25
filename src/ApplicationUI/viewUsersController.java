package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import BussinessLogic.finalYearProject;
import BussinessLogic.team;
import BussinessLogic.user;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewUsersController {

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<user, String> firstNameColumn;

    @FXML
    private TableColumn<user, String> lastNameColumn;

    @FXML
    private TableColumn<user, Integer> userIdColumn;

    @FXML
    private TableColumn<user, String> userTypeColumn;

    @FXML
    private TableView<user> userViewTable;

    @FXML
    private TableColumn<user, String> usernameColumn;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Main.changeScene("adminDashboard.fxml");
    }

    @FXML
    void initialize() {
        ObservableList<user> arr = dashboard.displayAllUsers();
        userIdColumn.setCellValueFactory(new PropertyValueFactory<user,Integer>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<user,String>("userName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<user,String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<user,String>("lastName"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<user,String>("type"));
        userViewTable.setItems(arr);
    }

}
