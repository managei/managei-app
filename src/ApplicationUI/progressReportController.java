package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import BussinessLogic.supervisor;
import BussinessLogic.team;
import BussinessLogic.user;
import DBHandler.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class progressReportController {

    private String currentReportType="Short";
    public static Integer getFypReportteamID() {
        return fypReportteamID;
    }

    public static void setFypReportteamID(Integer fypReportteamID) {
        progressReportController.fypReportteamID = fypReportteamID;
    }

    private static Integer fypReportteamID=0;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button generateReportButton;

    @FXML
    private Button goBackButton;

    @FXML
    private AnchorPane leftBanner;

//    @FXML
//    private MenuButton reportTypeMenu;

    @FXML
    private TableColumn<team, Integer> teamIDColumn;

    @FXML
    private TextField teamIDTextField;

    @FXML
    private Label teamIdLabel;

    @FXML
    private Label teamIdLabel1;

    @FXML
    private TableColumn<team, String> teamNameColumn;

    @FXML
    private TableView<team> teamTable;

    @FXML
    private Text errorTextField;

    @FXML
    private Button reportTypeButton;
    @FXML
    void generateProjectProgressReport(ActionEvent event) {

        try {
            fypReportteamID = Integer.parseInt(teamIDTextField.getText());
        }catch (Exception e){
            errorTextField.setText("Team ID must be an Integer");
            fypReportteamID=0;
            return;
        }

        if(fypReportteamID<=0){
            errorTextField.setText("Team ID must be > 0");
            return;
        }
        Main.initializeLists();

        if(dashboard.getFypList()==null || fypReportteamID>dashboard.getFypList().size()){
            errorTextField.setText("No such team");
            return;
        }

        if(currentReportType.equals("Short")){
            try {
                Main.changeScene("shortReport.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                Main.changeScene("longReport.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void changeReportType(ActionEvent event) {
        if(currentReportType.equals("Short")){
            currentReportType="Long";
        }else currentReportType="Short";

        reportTypeButton.setText(currentReportType);
    }
    @FXML
    void goToTeamDashboard(ActionEvent event) {
        Main.logOutUser();
        try {
            Main.changeScene("supervisorDashboard.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private RadioButton bottomRadio;

    @FXML
    private RadioButton topRadio;

    @FXML
    void updateTeamTable(ActionEvent event) {
        dashboard d=new dashboard();
//        user currentUser = Main.getLoggedUser();
        Main.initializeLists();
        ArrayList<team> arr = d.fetchTeamData();

        teamIDColumn.setCellValueFactory(new PropertyValueFactory<team,Integer>("id"));
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<team,String>("name"));

//        String currentUserName = Main.getLoggedUser().getUserName();
        int currentUserID =  Main.getLoggedUser().getId();
        supervisor currentSupervisor = null;

        for(int i=0; i<dashboard.getSupervisorList().size(); i++){
            if(dashboard.getSupervisorList().get(i).getId()==currentUserID){
                currentSupervisor=dashboard.getSupervisorList().get(i);
//                System.out.println("Current Supervisor ID: " + currentSupervisor.getUserId().toString());
            }
        }

        ObservableList<team> toDisp = FXCollections.observableArrayList();

        if(topRadio.isSelected()){
            for(int i=0; i<arr.size(); i++){
                if(arr.get(i).getId()==currentSupervisor.getAssignedTeamId()){
                    toDisp.add(arr.get(i));
                }
            }

        }else{
            for(int i=0; i<arr.size(); i++){
                toDisp.add(arr.get(i));
                System.out.println(arr.get(i).getName());
            }
        }
        teamTable.setItems(toDisp);
    }

    @FXML
    void initialize() {
        assert generateReportButton != null : "fx:id=\"generateReportButton\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert leftBanner != null : "fx:id=\"leftBanner\" was not injected: check your FXML file 'progressReport.fxml'.";
//        assert reportTypeMenu != null : "fx:id=\"reportTypeMenu\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamIDColumn != null : "fx:id=\"teamIDColumn\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamIDTextField != null : "fx:id=\"teamIDTextField\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamIdLabel != null : "fx:id=\"teamIdLabel\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamIdLabel1 != null : "fx:id=\"teamIdLabel1\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamNameColumn != null : "fx:id=\"teamNameColumn\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamTable != null : "fx:id=\"teamTable\" was not injected: check your FXML file 'progressReport.fxml'.";


//        MenuItem i1= new MenuItem();
//        i1.setText("Short Report");
//        MenuItem i2= new MenuItem();
//        i2.setText("Long Report");
//
//        reportTypeMenu.getItems().set(0,i1);
//        reportTypeMenu.getItems().set(1,i2);

        updateTeamTable(null);
    }

}
