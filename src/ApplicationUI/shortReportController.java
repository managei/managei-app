package ApplicationUI;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class shortReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text createErrorMsg;

    @FXML
    private Text fypCompletedTaskCount;

    @FXML
    private Text fypID;

    @FXML
    private Text fypName;

    @FXML
    private Text fypStatus;

    @FXML
    private Text fypTaskCount;

    @FXML
    private Button goBackButton;

    @FXML
    void gotoSupervisorDashboard(ActionEvent event) {
        try {
            Main.changeScene("supervisorDashboard.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        assert createErrorMsg != null : "fx:id=\"createErrorMsg\" was not injected: check your FXML file 'projectProgressReport.fxml'.";
        assert fypCompletedTaskCount != null : "fx:id=\"fypCompletedTaskCount\" was not injected: check your FXML file 'projectProgressReport.fxml'.";
        assert fypID != null : "fx:id=\"fypID\" was not injected: check your FXML file 'projectProgressReport.fxml'.";
        assert fypName != null : "fx:id=\"fypName\" was not injected: check your FXML file 'projectProgressReport.fxml'.";
        assert fypStatus != null : "fx:id=\"fypStatus\" was not injected: check your FXML file 'projectProgressReport.fxml'.";
        assert fypTaskCount != null : "fx:id=\"fypTaskCount\" was not injected: check your FXML file 'projectProgressReport.fxml'.";
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'projectProgressReport.fxml'.";

        dashboard d = new dashboard();
        ArrayList<String> arr = d.generateShortProjectProgressReport(progressReportController.getFypReportteamID().toString());
        System.out.println("Size was: " + arr.size());
        if(arr.size()==5){
            fypID.setText(arr.get(0));
            fypName.setText(arr.get(1));
            fypStatus.setText(arr.get(2));
            fypTaskCount.setText(arr.get(3));
            fypCompletedTaskCount.setText(arr.get(4));
        }

    }

}
