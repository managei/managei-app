package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import BussinessLogic.finalYearProject;
import BussinessLogic.user;
import Utils.Printing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class createTeamController {

    private Integer selectedFYPId;
    @FXML
    private Label label_info;

    @FXML
    private MenuButton selectFYP_MenuButton;

    @FXML
    private TextField teamName_textField;

    @FXML
    private TextArea teamDetails_textArea;
    @FXML
    void createTeam(ActionEvent event) {
        if(selectedFYPId.equals(null)||teamDetails_textArea.getText().isEmpty()||teamName_textField.getText().toString().isEmpty())
        {
            label_info.setText("Please fill all fields");
            label_info.setTextFill(Color.color(1, 0, 0));
        }else{

            Main.getDashBoard().createTeam(Main.getDBHandler(),teamName_textField.getText(),teamDetails_textArea.getText(),selectedFYPId);
            label_info.setText("Team Created Successfully");
            label_info.setTextFill(Color.color(0, 1, 0));
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Main.changeScene("adminDashBoard.fxml");
    }

    @FXML
    void initialize() {

        for (finalYearProject u:
                Main.getDashBoard().getFYP()) {
            MenuItem item = new MenuItem(u.getName());
            item.setId(u.getId().toString());
            item.setOnAction(actionEvent -> {
                selectFYP(actionEvent);
            });
            selectFYP_MenuButton.getItems().add(item);
        }

    }
    void selectFYP(ActionEvent event) {
        final MenuItem source = (MenuItem) event.getSource();
        String id = source.getId();
        selectedFYPId=Integer.parseInt(id);
        selectFYP_MenuButton.setText(source.getText());
    }
}
