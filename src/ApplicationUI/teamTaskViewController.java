package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import BussinessLogic.teamTaskViewCapsule;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class teamTaskViewController {
    @FXML
    private Button backButton;

    @FXML
    private TableColumn<teamTaskViewCapsule, String> detailColumn;

    @FXML
    private TableColumn<teamTaskViewCapsule, Integer> memberIDColumn;

    @FXML
    private TableColumn<teamTaskViewCapsule, String> statusColumn;

    @FXML
    private TableColumn<teamTaskViewCapsule, Integer> taskIDColumn;

    @FXML
    private TableView<teamTaskViewCapsule> taskViewTable;

    @FXML
    private TableColumn<teamTaskViewCapsule, String> tasknameColumn;

    @FXML
    void goToTeamDashboard(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("teamDashboard.fxml");
        }catch(IOException ie){

        }
    }

    private void openTaskView(){
        dashboard d= new dashboard();
        ObservableList<teamTaskViewCapsule> arr=d.openTaskView();

        taskIDColumn.setCellValueFactory(new PropertyValueFactory<teamTaskViewCapsule,Integer>("taskID"));
        memberIDColumn.setCellValueFactory(new PropertyValueFactory<teamTaskViewCapsule,Integer>("memberID"));
        tasknameColumn.setCellValueFactory(new PropertyValueFactory<teamTaskViewCapsule,String>("taskName"));
        detailColumn.setCellValueFactory(new PropertyValueFactory<teamTaskViewCapsule,String>("detail"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<teamTaskViewCapsule,String>("status"));
        taskViewTable.setItems(arr);
    }

    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'teamTaskView.fxml'.";
        assert detailColumn != null : "fx:id=\"detailColumn\" was not injected: check your FXML file 'teamTaskView.fxml'.";
//        assert leftBanner != null : "fx:id=\"leftBanner\" was not injected: check your FXML file 'teamTaskView.fxml'.";
//        assert memberNameColumn != null : "fx:id=\"memberNameColumn\" was not injected: check your FXML file 'teamTaskView.fxml'.";
        assert statusColumn != null : "fx:id=\"statusColumn\" was not injected: check your FXML file 'teamTaskView.fxml'.";
//        assert taskColumn != null : "fx:id=\"taskColumn\" was not injected: check your FXML file 'teamTaskView.fxml'.";
        assert taskViewTable != null : "fx:id=\"taskViewTable\" was not injected: check your FXML file 'teamTaskView.fxml'.";
        assert taskIDColumn != null : "fx:id=\"taskIDColumn\" was not injected: check your FXML file 'teamTaskView.fxml'.";

        openTaskView();
    }

}
