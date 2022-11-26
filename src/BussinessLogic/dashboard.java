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

    public static ArrayList<teamMember> getTeamMembersList() {
        return teamMembersList;
    }

    public static void setTeamMembersList(ArrayList<teamMember> teamMembersList) {
        dashboard.teamMembersList = teamMembersList;
    }

    static private ArrayList<teamMember> teamMembersList = null;

    public static ArrayList<supervisor> getSupervisorList() {
        return supervisorList;
    }

    public static void setSupervisorList(ArrayList<supervisor> supervisorList) {
        dashboard.supervisorList = supervisorList;
    }

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

    public void deleteProject(String fypID) throws SQLException{
        finalYearProject fyp = new finalYearProject();
        fyp.deleteProject(fypID);
    }
    public ObservableList<finalYearProject> displaySupervisorProjects(DBHandler dbh){
        ObservableList<finalYearProject> arr = dbh.getDataforSupervisorProjects();
        return arr;
    }

    public ArrayList<String> generateShortProjectProgressReport(String teamID){
        return supervisor.generateShortProjectProgressReport(teamID);
    }

    public ArrayList<team> fetchTeamData(){
        ArrayList<team> arr;
        arr=team.returnTeamList();
        return arr;
    }
    public void selectSuggestNewTask(String taskName,String taskDetail){
        user currentUser = Main.getLoggedInUser();

        teamMember currentTeamMember = null;

        for(int i=0; i<teamMembersList.size(); i++){
            System.out.println(teamMembersList.get(i).getUserId());
            System.out.println(currentUser.getUserId());

            if(teamMembersList.get(i).getUserId()==currentUser.getUserId()){
                currentTeamMember=teamMembersList.get(i);
                System.out.println(teamMembersList.get(i).getFirstName());
            }
        }

        System.out.println("team members: " + teamMembersList.size());
        team curTeam = null;

        for(int i=0; i<teamList.size(); i++){
            if(teamList.get(i).getId()==currentTeamMember.getTeamId()){
                curTeam=teamList.get(i);
            }
        }

        String fypID = curTeam.getFypId();
        teamMember.suggestNewTask(taskName,taskDetail,fypID,Main.getLoggedInUser().getUserId().toString());

    }

    public ObservableList<task> selectViewOwnTasks(){
        return teamMember.viewOwnTasks();
    }

    public Integer getTeamMemberTasksWithStatus(String status){
        return teamMember.getTasksWithStatus(status);
    }
    public boolean completeTask(String taskID){
        return teamMember.completeTask(taskID);
    }
}
