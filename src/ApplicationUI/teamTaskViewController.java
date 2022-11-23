package ApplicationUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class teamTaskViewController {

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
    private TableColumn<?, ?> memberNameColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> taskColumn;

    @FXML
    private TableView<?> taskViewTable;

    @FXML
    private TableColumn<?, ?> tasknameColumn;

    @FXML
    void goToTeamDashboard(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'teamTaskView.fxml'.";
        assert detailColumn != null : "fx:id=\"detailColumn\" was not injected: check your FXML file 'teamTaskView.fxml'.";
        assert leftBanner != null : "fx:id=\"leftBanner\" was not injected: check your FXML file 'teamTaskView.fxml'.";
        assert memberNameColumn != null : "fx:id=\"memberNameColumn\" was not injected: check your FXML file 'teamTaskView.fxml'.";
        assert statusColumn != null : "fx:id=\"statusColumn\" was not injected: check your FXML file 'teamTaskView.fxml'.";
        assert taskColumn != null : "fx:id=\"taskColumn\" was not injected: check your FXML file 'teamTaskView.fxml'.";
        assert taskViewTable != null : "fx:id=\"taskViewTable\" was not injected: check your FXML file 'teamTaskView.fxml'.";
        assert tasknameColumn != null : "fx:id=\"tasknameColumn\" was not injected: check your FXML file 'teamTaskView.fxml'.";

    }

}
