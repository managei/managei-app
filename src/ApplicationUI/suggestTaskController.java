package ApplicationUI;

import BussinessLogic.dashboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class suggestTaskController {

    @FXML
    private AnchorPane leftBanner;

    @FXML
    private Button suggestTaskButton;

    @FXML
    private TextArea taskDetailsField;

    @FXML
    private Label taskDetailsLabel;

    @FXML
    private TextField taskNameField;

    @FXML
    private Text errorTextField;

    @FXML
    private Label taskNameLabel;

    @FXML
    private Button viewOwnTasksButton;

    @FXML
    void gotoTeamDashboard(ActionEvent event) {
        Main m = new Main();
        try {
            m.changeScene("teamDashboard.fxml");
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

    @FXML
    void suggestTask(ActionEvent event) {

        if(taskNameField.getText().equals("") || taskDetailsField.getText().equals("")){
            errorTextField.setText("Fill all fields first");
        }
        dashboard d = new dashboard();
        d.selectSuggestNewTask(taskNameField.getText(),taskDetailsField.getText());
        errorTextField.setText("Task suggested successfully");
    }

}
