package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BussinessLogic.dashboard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;

public class projectLongReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text fypCompletedTaskCount;

    @FXML
    private Text fypIDText;

    @FXML
    private Text fypNameText;

    @FXML
    private Text fypStatusText;

    @FXML
    private Text fypTaskCount;

    @FXML
    private Button goBackButton;

    @FXML
    private PieChart progressGraph;

    public static Integer getTeamID() {
        return teamID;
    }

    public static void setTeamID(Integer teamID) {
        projectLongReportController.teamID = teamID;
    }

    private static Integer teamID=1;
    void drawGraph(){
        XYChart.Series<String,Integer> s1 = new XYChart.Series<String,Integer>();
        s1.setName("Project Progress Graph");
        XYChart.Series<String,Integer> data = dashboard.getTeamGraphData();
        ObservableList<XYChart.Data<String,Integer>> arr = data.getData();
        ObservableList<PieChart.Data> pieArr = FXCollections.observableArrayList();
        for(int i=0; i<arr.size();i++){
            XYChart.Data<String,Integer> d = arr.get(i);
            pieArr.add(new PieChart.Data(d.getXValue(),d.getYValue()));
        }
        progressGraph.setTitle("Distribution of completed tasks");
        progressGraph.setData(pieArr);
//        progressGraph.getData().forEach(val ->{
//            String percent = String.format("%.2f%",(val.getPieValue()/100));
//            Tooltip t = new Tooltip(percent);
//            Tooltip.install(val.getNode(),t);
//        });
    }

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
//        assert fypCompletedTaskCount != null : "fx:id=\"fypCompletedTaskCount\" was not injected: check your FXML file 'projectLongReport.fxml'.";
//        assert fypIDText != null : "fx:id=\"fypIDText\" was not injected: check your FXML file 'projectLongReport.fxml'.";
//        assert fypNameText != null : "fx:id=\"fypNameText\" was not injected: check your FXML file 'projectLongReport.fxml'.";
//        assert fypStatusText != null : "fx:id=\"fypStatusText\" was not injected: check your FXML file 'projectLongReport.fxml'.";
//        assert fypTaskCount != null : "fx:id=\"fypTaskCount\" was not injected: check your FXML file 'projectLongReport.fxml'.";
//        assert goBackButton != null : "fx:id=\"goBackButton\" was not injected: check your FXML file 'projectLongReport.fxml'.";
//        assert progressGraph != null : "fx:id=\"progressGraph\" was not injected: check your FXML file 'projectLongReport.fxml'.";

        dashboard d = new dashboard();
        ArrayList<String> arr = d.generateShortProjectProgressReport(progressReportController.getFypReportteamID().toString());
        System.out.println("Size was: " + arr.size());
        if(arr.size()==5){
            fypIDText.setText(arr.get(0));
            fypNameText.setText(arr.get(1));
            fypStatusText.setText(arr.get(2));
            fypTaskCount.setText(arr.get(3));
            fypCompletedTaskCount.setText(arr.get(4));
        }

        drawGraph();
    }

}
