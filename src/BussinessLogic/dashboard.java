package BussinessLogic;

import ApplicationUI.Main;
import DBHandler.DBHandler;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class dashboard {
    static private ArrayList<user> userList=null;
    static private ArrayList<team> teamList=null;
    static private ArrayList<finalYearProject> fypList=null;
    static private ArrayList<task> taskList=null;
    public user loginUser(String userName,String password)
    {
        for (user u:
             userList) {
            if(u.checkCredentials(userName,password))
                return u;
        }
        return null;
    }
    public user registerUser(Integer userId, String userName, String firstName, String lastName, String password, String userType)
    {
        DBHandler db= new DBHandler();
        user ad = switch (userType) {
            case "admin" -> new admin(userId, userName, firstName, lastName, password, userType);
            case "supervisor" -> new supervisor(userId, userName, firstName, lastName, password, userType);
            case "teamMember" -> new teamMember(userId, userName, firstName, lastName, password, userType);
            case "headOfDepartment" -> new headOfDepartment(userId, userName, firstName, lastName, password, userType);
            case "fypLabInstructor" -> new fypLabInstructor(userId, userName, firstName, lastName, password, userType);
            default -> null;
        };
        userList.add(ad);
        db.saveUser(ad);
        return ad;
    }

    public static ArrayList<user> getUserList() {
        return userList;
    }

    public static void setUserList(ArrayList<user> userList) {
        dashboard.userList = userList;
    }

    public static ArrayList<team> getTeamList() {
        return teamList;
    }

    public static void setTeamList(ArrayList<team> teamList) {
        dashboard.teamList = teamList;
    }

    public static ArrayList<finalYearProject> getFypList() {
        return fypList;
    }

    public static void setFypList(ArrayList<finalYearProject> fypList) {
        dashboard.fypList = fypList;
    }

    public static ArrayList<task> getTaskList() {
        return taskList;
    }

    public static void setTaskList(ArrayList<task> taskList) {
        dashboard.taskList = taskList;
    }

//    public ObservableList<ObservableList<String>> displaySupervisorProjects(DBHandler dbh,String query){
//        ObservableList<ObservableList<String>> arr = dbh.getDataforTableUsingQuery(query);
//        return arr;
//    }

    public void createProject(DBHandler dbh,String projectName, String projectStatus){
//        if(fypList==null) fypList=new ArrayList<finalYearProject>();
//        int sizeOffyp=fypList.size();
//        if(sizeOffyp>0)
//        finalYearProject fyp= new finalYearProject(fypList.get(fypList.size()-1).getId()+1,projectName,projectStatus);
//        fypList.add(fyp);
        dbh.saveNewProjectInDB(projectName,projectStatus);
        Main.initializeLists();
    }

    public void updateProjectDetail(DBHandler dbh,String fypName,String fypStatus,String fypID) throws SQLException {
        dbh.updateProjectDetails(fypName,fypStatus,fypID);
        Main.initializeLists();
    }
    public ObservableList<finalYearProject> displaySupervisorProjects(DBHandler dbh){
        ObservableList<finalYearProject> arr = dbh.getDataforSupervisorProjects();
        return arr;
    }
}
