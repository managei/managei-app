import DBHandler.DBHandler;
import Utils.Printing;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stg;

    @Override
    public void start(Stage primaryStage) {
        try {
            stg = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("ApplicationUI/welcome.fxml"));
            Scene scene = new Scene(root, 600, 400);
            primaryStage.setTitle("Managei");
            primaryStage.setScene(scene);
            primaryStage.show();
            Printing.PrintStr("Main Started");
            DBHandler db = new DBHandler();
            db.readTable("users");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {

        launch(args);

    }
}
