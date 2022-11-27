package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import BussinessLogic.teamMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class progressGraphController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button goBackButton;

    @FXML
    private AnchorPane leftBanner;

    @FXML
    private BarChart<String, Integer> progressGraph;

    @FXML
    void toTeamDashboard(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("teamDashboard.fxml");
        }catch(IOException ie){

        }
    }

    void drawGraph(){
        XYChart.Series<String,Integer> s1 = new XYChart.Series<String,Integer>();
        s1.setName("Project Progress Graph");
        XYChart.Series<String,Integer> data = dashboard.getTeamGraphData();

        progressGraph.getData().add(data);
    }

    @FXML
    void initialize() {
        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'progressGraph.fxml'.";
        assert leftBanner != null : "fx:id=\"leftBanner\" was not injected: check your FXML file 'progressGraph.fxml'.";
        assert progressGraph != null : "fx:id=\"progressGraph\" was not injected: check your FXML file 'progressGraph.fxml'.";

        drawGraph();
    }

}
