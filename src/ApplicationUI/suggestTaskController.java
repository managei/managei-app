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
import java.sql.SQLException;

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
        try {
            if(Main.getLoggedUser().getType().equals("admin"))
                Main.changeScene("adminDashBoard.fxml");
            else if(Main.getLoggedUser().getType().equals("teamMember"))
                Main.changeScene("teamDashboard.fxml");
            else if(Main.getLoggedUser().getType().equals("supervisor"))
                Main.changeScene("supervisorDashboard.fxml");
        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

    @FXML
    void suggestTask(ActionEvent event) throws SQLException {

        if(taskNameField.getText().equals("") || taskDetailsField.getText().equals("")){
            errorTextField.setText("Fill all fields first");
//            errorTextField.setStyle("-fx-text-fill: red");
            return;
        }
        dashboard d = new dashboard();
        d.selectSuggestNewTask(taskNameField.getText(),taskDetailsField.getText());
//        errorTextField.setStyle("-fx-text-fill: green");
        errorTextField.setText("Task suggested successfully");
        taskDetailsField.setText("");
        taskNameField.setText("");
    }

}
