package ApplicationUI;

import DBHandler.DBHandler;
import Utils.Printing;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stg;

    @Override
    public void start(Stage primaryStage) {
        try {
            stg = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("completeTask.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Managei");
            primaryStage.setScene(scene);
//            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../images/Managei.png")));
            primaryStage.show();
            root.requestFocus();
            Printing.PrintStr("ApplicationUI.Main Started");
            DBHandler db = new DBHandler();
//            db.readTable("users");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
        stg.show();
        Scene scene = new Scene(pane);
        stg.setScene(scene);
        pane.requestFocus();
    }
    public void createScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        Scene scene = new Scene(pane);
        stg.setScene(scene);
        pane.requestFocus();
    }
    public static void main(String[] args) {

        launch(args);

    }
}
