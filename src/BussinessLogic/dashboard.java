package BussinessLogic;

import ApplicationUI.Main;
import DBHandler.DBHandler;
import Utils.Printing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class dashboard {
    static private ArrayList<user> userList=null;
    static private ArrayList<team> teamList=null;
    static private ArrayList<finalYearProject> fypList=null;
    static private ArrayList<task> taskList=null;

    public static ArrayList<supervisor> getSupervisorList() {
        return supervisorList;
    }

    public static void setSupervisorList(ArrayList<supervisor> supervisorList) {
        dashboard.supervisorList = supervisorList;
    }
    // Why needed a new list for supervisor, we have user already
    static private ArrayList<supervisor> supervisorList=null;
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

    public void createTeam(DBHandler dbh,String name, String details,Integer fypID){
        dbh.saveNewTeamInDB(name,details,fypID);
        Main.initializeLists();
    }
    public void createProject(DBHandler dbh,String projectName, String projectStatus){
        dbh.saveNewProjectInDB(projectName,projectStatus);
        Main.initializeLists();
    }
    public void updateProjectDetail(DBHandler dbh,String fypName,String fypStatus,String fypID) throws SQLException {
        dbh.updateProjectDetails(fypName,fypStatus,fypID);
        Main.initializeLists();
    }

    public void deleteProject(String fypID) throws SQLException{
        finalYearProject fyp = new finalYearProject();
        fyp.deleteProject(fypID);
    }
    public ObservableList<finalYearProject> displaySupervisorProjects(DBHandler dbh){
        ObservableList<finalYearProject> arr = dbh.getDataforSupervisorProjects();
        return arr;
    }
    public static ObservableList<user> displayAllUsers(){
        ObservableList<user> data = FXCollections.observableArrayList();
        for (user u:userList) {
            data.add(u);
        }
        return data;
    }
    public static ObservableList<team> displayAllTeams(){
        ObservableList<team> data = FXCollections.observableArrayList();
        for (team t:teamList) {
            data.add(t);
        }
        return data;
    }
    public static ArrayList<user> getUsersByType(String userType){
        ArrayList<user> filteredUsers= new ArrayList<user>();
        for (user u:userList) {
            if(u.getType().equals(userType)){
                filteredUsers.add(u);
            }
        }
        return filteredUsers;
    }
    public static ArrayList<finalYearProject> getFYP(){
        return fypList;
    }


    public ArrayList<String> generateShortProjectProgressReport(String teamID){
        return supervisor.generateShortProjectProgressReport(teamID);
    }

    public ArrayList<team> fetchTeamData(){
        ArrayList<team> arr;
        arr=team.returnTeamList();
        return arr;
    }
}
