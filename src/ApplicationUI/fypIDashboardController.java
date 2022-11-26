package ApplicationUI;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.image.ImageView;
        import javafx.scene.input.MouseEvent;

public class fypIDashboardController {

    @FXML
    private Button approveButton;

    @FXML
    private Button createButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ImageView logOutButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button viewButton;

    @FXML
    void approveMeeting(ActionEvent event) {
        try {
            Main.changeScene("approveMeeting.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void createMeeting(ActionEvent event) {
        try {
            Main.changeScene("createMeeting.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deleteMeeting(ActionEvent event) {
        try {
            Main.changeScene("deleteMeeting.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logOut(MouseEvent event) {
        Main.logOutUser();
        try {
            Main.changeScene("welcome.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void updateMeeting(ActionEvent event) {
        try {
            Main.changeScene("updateMeeting.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void viewMeetings(ActionEvent event) {
        try {
            Main.changeScene("viewMeetings.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {

    }

}
