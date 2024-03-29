package ApplicationUI;

import BussinessLogic.*;
import DBHandler.DBHandler;
import DBHandler.PersistantHandlerClass;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static PersistantHandlerClass db;
    private static dashboard d=new dashboard();
    private static Stage stg;
    private static user loggedInUser=null;
    public static PersistantHandlerClass getDBHandler ()
    {
        return db;
    }
    public static dashboard getDashBoard ()
    {
        return d;
    }

    public static user getLoggedUser ()
    {
        return loggedInUser;
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            stg = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Managei");
            primaryStage.setScene(scene);
//            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("../images/Managei.png")));
            primaryStage.show();
            root.requestFocus();
            PersistantHandlerClass.setPh(new DBHandler());
            initializeLists();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setPersistantHandler(){
        PersistantHandlerClass.setPh(new DBHandler());
    }

    public static void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(Main.class.getResource(fxml));
        Scene scene = new Scene(pane);
        stg.setScene(scene);
        pane.requestFocus();
        initializeLists();
    }
    public static void logOutUser()
    {
        loggedInUser=null;
    }
    public static void gotoDashboard(String userRole) throws IOException {
        switch (userRole) {
            case "admin" -> changeScene("adminDashboard.fxml");
            case "supervisor" ->  changeScene("supervisorDashboard.fxml");
            case "teamMember" ->  changeScene("teamDashboard.fxml");
            case "headOfDepartment" ->  changeScene("HODDashboard.fxml");
            case "fypLabInstructor" ->  changeScene("fypIDashboard.fxml");
        };
    }
    public static void setLoggedInUser(user loggedInUser) {
        Main.loggedInUser = loggedInUser;
    }
    public static user getLoggedInUser() {
        return loggedInUser;
    }
    public static void initializeLists()
    {
        db = new PersistantHandlerClass();
        dashboard.setFypList(db.readFYP());
        dashboard.setTaskList(db.readTasks());
        dashboard.setUserList(db.readUsers());
        dashboard.setTeamList(db.readTeams());
        dashboard.setSupervisorList(db.readSupervisors());
        dashboard.setTeamMembersList(db.readTeamMembers());
    }
    public static void main(String[] args) {
        launch(args);
    }
}
