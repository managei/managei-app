package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import BussinessLogic.finalYearProject;
import DBHandler.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class updateProjectController {

    private DBHandler dbh=null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text createErrorMsg;

    @FXML
    private TableColumn<finalYearProject, Integer> fypIDCol;

    @FXML
    private TextField fypIDTextField;

    @FXML
    private TableColumn<finalYearProject, String> fypNameCol;

    @FXML
    private TextField fypNameField;

    @FXML
    private TableColumn<finalYearProject, String> fypStatusCol;

    @FXML
    private TextField fypStatusField;

    @FXML
    private Button goBackButton;

    @FXML
    private Label taskNameLabel;

    @FXML
    private Label taskNameLabel1;

    @FXML
    private Label taskNameLabel2;

    @FXML
    private Button updateButton;

    @FXML
    private Button modifyTeamButton;

    @FXML
    private TableView<finalYearProject> tableView;
    @FXML
    void gotoSupervisorDashboard(ActionEvent event) {
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
    void modifyTeam(ActionEvent event) {
        try {
            Main.changeScene("modifyTeam.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void updateProjectdetails(ActionEvent event) {
        dashboard d = new dashboard();
        String fypName = fypNameField.getText();
        String fypStatus = fypStatusField.getText();
        String fypId = fypIDTextField.getText();

        if(fypName.equals("") || fypStatus.equals("") || fypId.equals("")){
            createErrorMsg.setText("Enter all details");
            return;
        }
        int fypIdInt=0;
        try {
            fypIdInt=Integer.parseInt(fypId);
        }catch(Exception e){
            createErrorMsg.setText("Enter correct ID number");
            return;
        }

        if(fypIdInt<0){
            createErrorMsg.setText("Wrong ID");
            return;
        }

        try {
            d.updateProjectDetail(dbh,fypName,fypStatus,fypId);
        } catch (SQLException e) {
            createErrorMsg.setText("Check Details");
        }

        ObservableList<finalYearProject> arr= d.displaySupervisorProjects(dbh);
        tableView.setItems(arr);
    }

    @FXML
    void initialize() {
        assert createErrorMsg != null : "fx:id=\"createErrorMsg\" was not injected: check your FXML file 'createProject.fxml'.";
        assert fypIDCol != null : "fx:id=\"fypIDCol\" was not injected: check your FXML file 'createProject.fxml'.";
        assert fypIDTextField != null : "fx:id=\"fypIDTextField\" was not injected: check your FXML file 'createProject.fxml'.";
        assert fypNameCol != null : "fx:id=\"fypNameCol\" was not injected: check your FXML file 'createProject.fxml'.";
        assert fypNameField != null : "fx:id=\"fypNameField\" was not injected: check your FXML file 'createProject.fxml'.";
        assert fypStatusCol != null : "fx:id=\"fypStatusCol\" was not injected: check your FXML file 'createProject.fxml'.";
        assert fypStatusField != null : "fx:id=\"fypStatusField\" was not injected: check your FXML file 'createProject.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'createProject.fxml'.";
        assert taskNameLabel != null : "fx:id=\"taskNameLabel\" was not injected: check your FXML file 'createProject.fxml'.";
        assert taskNameLabel1 != null : "fx:id=\"taskNameLabel1\" was not injected: check your FXML file 'createProject.fxml'.";
        assert taskNameLabel2 != null : "fx:id=\"taskNameLabel2\" was not injected: check your FXML file 'createProject.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'createProject.fxml'.";


        dbh=new DBHandler();

        fypIDCol.setCellValueFactory(new PropertyValueFactory<finalYearProject,Integer>("id"));
        fypNameCol.setCellValueFactory(new PropertyValueFactory<finalYearProject,String>("name"));
        fypStatusCol.setCellValueFactory(new PropertyValueFactory<finalYearProject,String>("status"));
        dashboard d = new dashboard();
        ObservableList<finalYearProject> arr= d.displaySupervisorProjects(dbh);
        tableView.setItems(arr);
    }

}
