package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class memberTaskViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<?, ?> detailColumn;

    @FXML
    private AnchorPane leftBanner;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> taskColumn;

    @FXML
    private TableView<?> taskViewTable;

    @FXML
    void goToTeamDashboard(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("teamDashboard.fxml");
        }catch(IOException ie){

        }
    }

    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'memberTaskView.fxml'.";
        assert detailColumn != null : "fx:id=\"detailColumn\" was not injected: check your FXML file 'memberTaskView.fxml'.";
        assert leftBanner != null : "fx:id=\"leftBanner\" was not injected: check your FXML file 'memberTaskView.fxml'.";
        assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'memberTaskView.fxml'.";
        assert statusColumn != null : "fx:id=\"statusColumn\" was not injected: check your FXML file 'memberTaskView.fxml'.";
        assert taskColumn != null : "fx:id=\"taskColumn\" was not injected: check your FXML file 'memberTaskView.fxml'.";
        assert taskViewTable != null : "fx:id=\"taskViewTable\" was not injected: check your FXML file 'memberTaskView.fxml'.";

    }

}
