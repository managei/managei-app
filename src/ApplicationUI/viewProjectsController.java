package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import BussinessLogic.finalYearProject;
import BussinessLogic.supervisor;
import DBHandler.DBHandler;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewProjectsController {

    private DBHandler dbh;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<finalYearProject, Integer> fypIDColumn;

    @FXML
    private TableColumn<finalYearProject, String> fypNameColumn;

    @FXML
    private TableColumn<finalYearProject, String> fypStatusColumn;

    @FXML
    private TableView<finalYearProject> taskViewTable;

    @FXML
    private TableColumn<finalYearProject, Integer> teamIDColumn;

    @FXML
    void goToSupervisorDashboard(ActionEvent event) {
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
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'viewProjects.fxml'.";
        assert fypIDColumn != null : "fx:id=\"fypIDColumn\" was not injected: check your FXML file 'viewProjects.fxml'.";
        assert fypNameColumn != null : "fx:id=\"fypNameColumn\" was not injected: check your FXML file 'viewProjects.fxml'.";
        assert fypStatusColumn != null : "fx:id=\"fypStatusColumn\" was not injected: check your FXML file 'viewProjects.fxml'.";
        assert taskViewTable != null : "fx:id=\"taskViewTable\" was not injected: check your FXML file 'viewProjects.fxml'.";
        assert teamIDColumn != null : "fx:id=\"teamIDColumn\" was not injected: check your FXML file 'viewProjects.fxml'.";

        dbh= new DBHandler();

        dashboard d = new dashboard();
        ObservableList<finalYearProject> arr = d.displaySupervisorProjects(dbh);
        fypIDColumn.setCellValueFactory(new PropertyValueFactory<finalYearProject,Integer>("id"));
        fypNameColumn.setCellValueFactory(new PropertyValueFactory<finalYearProject,String>("name"));
        fypStatusColumn.setCellValueFactory(new PropertyValueFactory<finalYearProject,String>("status"));
        teamIDColumn.setCellValueFactory(new PropertyValueFactory<finalYearProject,Integer>("teamID"));

        taskViewTable.setItems(arr);
    }

}
