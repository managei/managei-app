package ApplicationUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class progressReportController {

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

    @FXML
    private MenuButton reportTypeMenu;

    @FXML
    private TableColumn<?, ?> teamIDColumn;

    @FXML
    private TextField teamIDTextField;

    @FXML
    private Label teamIdLabel;

    @FXML
    private Label teamIdLabel1;

    @FXML
    private TableColumn<?, ?> teamNameColumn;

    @FXML
    private TableView<?> teamTable;

    @FXML
    void generateProjectProgressReport(ActionEvent event) {

    }

    @FXML
    void goToTeamDashboard(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert generateReportButton != null : "fx:id=\"generateReportButton\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert leftBanner != null : "fx:id=\"leftBanner\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert reportTypeMenu != null : "fx:id=\"reportTypeMenu\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamIDColumn != null : "fx:id=\"teamIDColumn\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamIDTextField != null : "fx:id=\"teamIDTextField\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamIdLabel != null : "fx:id=\"teamIdLabel\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamIdLabel1 != null : "fx:id=\"teamIdLabel1\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamNameColumn != null : "fx:id=\"teamNameColumn\" was not injected: check your FXML file 'progressReport.fxml'.";
        assert teamTable != null : "fx:id=\"teamTable\" was not injected: check your FXML file 'progressReport.fxml'.";


        MenuItem i1= new MenuItem();
        i1.setText("Short Report");
        MenuItem i2= new MenuItem();
        i2.setText("Long Report");
        reportTypeMenu.getItems().set(0,i1);
        reportTypeMenu.getItems().set(1,i2);
    }

}
