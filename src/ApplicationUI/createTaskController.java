package ApplicationUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BussinessLogic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class createTaskController {
    private Integer selectedTMId;
    private Integer selectedFYPId;
    @FXML
    private TableColumn<task, Integer> assignedToColumn;

    @FXML
    private TableColumn<task, String> detailsColumn;

    @FXML
    private TableColumn<task, Integer> fypIDColumn;

    @FXML
    private Label label_info;

    @FXML
    private TableColumn<task, String > nameColumn;

    @FXML
    private MenuButton selectFYP_MenuButton;

    @FXML
    private MenuButton selectTM_MenuButton;

    @FXML
    private TableColumn<task, String> statusColumn;

    @FXML
    private TextArea taskDetails_textArea;

    @FXML
    private TableColumn<task, Integer> taskIdColumn;

    @FXML
    private TextField taskName_textField;

    @FXML
    private TableView<task> ViewTable;

    @FXML
    void createTask(ActionEvent event) throws SQLException {
        if(selectedTMId.equals(null)||taskName_textField.getText().isEmpty()||taskDetails_textArea.getText().isEmpty())
        {
            label_info.setText("Please fill all fields");
            label_info.setTextFill(Color.color(1, 0, 0));
        }else{
            Main.getDashBoard().createTask(Main.getDBHandler(),taskName_textField.getText(),taskDetails_textArea.getText(),selectedTMId);
            label_info.setText("Meeting Scheduled Successfully");
            label_info.setTextFill(Color.color(0, 1, 0));
            initialize();
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Main.changeScene("supervisorDashboard.fxml");
    }

    @FXML
    void initialize() {
        ObservableList<task> data = FXCollections.observableArrayList();
        for (task t:Main.getDashBoard().getTaskList()) {
            data.add(t);
        }
        taskIdColumn.setCellValueFactory(new PropertyValueFactory<task,Integer>("id"));
        fypIDColumn.setCellValueFactory(new PropertyValueFactory<task,Integer>("fypId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<task,String>("name"));
        detailsColumn.setCellValueFactory(new PropertyValueFactory<task,String>("detail"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<task, String>("status"));
        assignedToColumn.setCellValueFactory(new PropertyValueFactory<task, Integer>("memberId"));
        ViewTable.setItems(data);

        for (user u:
                Main.getDashBoard().getSupervisorsTeamMembers(Main.getDBHandler(),Main.getLoggedUser().getId())) {
            MenuItem item = new MenuItem(u.getFirstName()+" "+u.getLastName());
            item.setId(u.getId().toString());
            item.setOnAction(actionEvent -> {
                selectTM(actionEvent);
            });
            selectTM_MenuButton.getItems().add(item);
        }
    }

    void selectTM(ActionEvent event) {
        final MenuItem source = (MenuItem) event.getSource();
        String id = source.getId();
        selectedTMId=Integer.parseInt(id);
        selectTM_MenuButton.setText(source.getText());
    }
}
