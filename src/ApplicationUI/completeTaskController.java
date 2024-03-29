package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import BussinessLogic.task;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class completeTaskController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button completeTaskButton;

    @FXML
    private TableColumn<task, String> detailColumn;

    @FXML
    private AnchorPane leftBanner;

    @FXML
    private TableColumn<task, String> nameColumn;

    @FXML
    private TableColumn<task, String> statusColumn;

    @FXML
    private TableColumn<task, Integer> taskColumn;

    @FXML
    private TableView<task> taskViewTable;

    @FXML
    private TextField taskIDField;

    @FXML
    private Text errorTextField;
    @FXML
    void goToTeamDashboard(ActionEvent event) {
        try {
            Main.changeScene("teamDashboard.fxml");
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

    @FXML
    void completeAssignedTask(ActionEvent event) {
        Main.initializeLists();
        int taskID=0;

        try{
            taskID=Integer.parseInt(taskIDField.getText());
        }catch (Exception e){
            errorTextField.setText("Wrong ID format.");
        }

        if(taskIDField.getText().equals("") || taskID>dashboard.getTaskList().get(dashboard.getTaskList().size()-1).getId()){
            errorTextField.setText("ID out of range");
        }

        dashboard d = new dashboard();
        if(d.completeTask(taskIDField.getText())==false){
            errorTextField.setText("Update error check details");
        }
        updateTaskTable();
    }

    void updateTaskTable(){
        dashboard d = new dashboard();
        ObservableList<task> arr =d.selectViewOwnTasks();
        taskViewTable.setItems(arr);
    }

    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert detailColumn != null : "fx:id=\"detailColumn\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert leftBanner != null : "fx:id=\"leftBanner\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert statusColumn != null : "fx:id=\"statusColumn\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert taskColumn != null : "fx:id=\"taskColumn\" was not injected: check your FXML file 'completeTask.fxml'.";
        assert taskViewTable != null : "fx:id=\"taskViewTable\" was not injected: check your FXML file 'completeTask.fxml'.";

        taskColumn.setCellValueFactory(new PropertyValueFactory<task,Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<task,String>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<task,String>("status"));
        detailColumn.setCellValueFactory(new PropertyValueFactory<task,String>("detail"));
        updateTaskTable();
    }

}
