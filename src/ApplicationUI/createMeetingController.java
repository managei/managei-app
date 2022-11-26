package ApplicationUI;

import BussinessLogic.team;
import BussinessLogic.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.SQLException;

public class createMeetingController {
    private Integer selectedSUPId;
    private Integer selectedTeamId;
    private Integer selectedINSId;

    @FXML
    private Button createProjectButton;

    @FXML
    private TextField date_textField;

    @FXML
    private TextArea details_textArea;

    @FXML
    private Button goBackButton;

    @FXML
    private Label label_info;

    @FXML
    private TextArea location_textArea1;

    @FXML
    private TextField name_textField;

    @FXML
    private MenuButton selectSUP_MenuButton;
    @FXML
    private MenuButton selectINS_MenuButton;

    @FXML
    private MenuButton selectTeam_MenuButton;

    @FXML
    private TextField time_textField;

    @FXML
    void createMeeting(ActionEvent event) throws SQLException {
        if(selectedTeamId.equals(null)||selectedINSId.equals(null)||selectedSUPId.equals(null)||name_textField.getText().isEmpty()||location_textArea1.getText().isEmpty()||details_textArea.getText().isEmpty()||date_textField.getText().isEmpty()||time_textField.getText().isEmpty())
        {
            label_info.setText("Please fill all fields");
            label_info.setTextFill(Color.color(1, 0, 0));
        }else{
            Main.getDashBoard().createMeeting(Main.getDBHandler(),name_textField.getText(),details_textArea.getText(),location_textArea1.getText(),date_textField.getText(),time_textField.getText(),selectedSUPId,selectedTeamId,selectedINSId);
            label_info.setText("Meeting Scheduled Successfully");
            label_info.setTextFill(Color.color(0, 1, 0));
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        if(Main.getLoggedUser().getType().equals("admin"))
            Main.changeScene("adminDashBoard.fxml");
        else if(Main.getLoggedUser().getType().equals("fypLabInstructor"))
            Main.changeScene("fypIDashboard.fxml");
        else if(Main.getLoggedUser().getType().equals("supervisor"))
            Main.changeScene("supervisorDashboard.fxml");
    }

    @FXML
    void initialize() {
        for (user u:
                Main.getDashBoard().getUsersByType("supervisor")) {
            MenuItem item = new MenuItem(u.getFirstName()+" "+u.getLastName());
            item.setId(u.getId().toString());
            item.setOnAction(actionEvent -> {
                selectSUP(actionEvent);
            });
            selectSUP_MenuButton.getItems().add(item);
        }
        for (user u:
                Main.getDashBoard().getUsersByType("fypLabInstructor")) {
            MenuItem item = new MenuItem(u.getFirstName()+" "+u.getLastName());
            item.setId(u.getId().toString());
            item.setOnAction(actionEvent -> {
                selectINS(actionEvent);
            });
            selectINS_MenuButton.getItems().add(item);
        }
        for (team u:
                Main.getDashBoard().getTeamList()) {
            MenuItem item = new MenuItem(u.getName());
            item.setId(u.getId().toString());
            item.setOnAction(actionEvent -> {
                selectTeam(actionEvent);
            });
            selectTeam_MenuButton.getItems().add(item);
        }

    }
    void selectSUP(ActionEvent event) {
        final MenuItem source = (MenuItem) event.getSource();
        String id = source.getId();
        selectedSUPId=Integer.parseInt(id);
        selectSUP_MenuButton.setText(source.getText());
    }
    void selectINS(ActionEvent event) {
        final MenuItem source = (MenuItem) event.getSource();
        String id = source.getId();
        selectedINSId=Integer.parseInt(id);
        selectINS_MenuButton.setText(source.getText());
    }
    void selectTeam(ActionEvent event) {
        final MenuItem source = (MenuItem) event.getSource();
        String id = source.getId();
        selectedTeamId=Integer.parseInt(id);
        selectTeam_MenuButton.setText(source.getText());
    }
}
