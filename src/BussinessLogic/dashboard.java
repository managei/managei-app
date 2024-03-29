package BussinessLogic;

import ApplicationUI.Main;
import DBHandler.PersistantHandlerClass;
import Utils.Printing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;
import java.util.ArrayList;

public class dashboard {
    static private ArrayList<user> userList=null;
    static private ArrayList<team> teamList=null;
    static private ArrayList<finalYearProject> fypList=null;
    static private ArrayList<task> taskList=null;
    static private ArrayList<supervisor> supervisorList=null;
    static private ArrayList<teamMember> teamMembersList=null;

    public static ArrayList<teamMember> getTeamMembersList() {
        return teamMembersList;
    }

    public static void setTeamMembersList(ArrayList<teamMember> teamMembersList) {
        dashboard.teamMembersList = teamMembersList;
    }


    public static ArrayList<supervisor> getSupervisorList() {
        return supervisorList;
    }

    public static void setSupervisorList(ArrayList<supervisor> supervisorList) {
        dashboard.supervisorList = supervisorList;
    }
    // Why needed a new list for supervisor, we have user already
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
        PersistantHandlerClass db= new PersistantHandlerClass();
        user ad = switch (userType) {
            case "admin" -> new admin(userId, userName, firstName, lastName, password, userType);
            case "supervisor" -> new supervisor(userId, userName, firstName, lastName, password, userType);
            case "teamMember" -> new teamMember(userId, userName, firstName, lastName, password, userType);
            case "headOfDepartment" -> new headOfDepartment(userId, userName, firstName, lastName, password, userType);
            case "fypLabInstructor" -> new fypLabInstructor(userId, userName, firstName, lastName, password, userType);
            default -> null;
        };
        db.saveUser(ad);
        return ad;
    }
    public String addTeamMember(Integer memberID,Integer teamID)
    {
        return Main.getDBHandler().addToTeam(memberID, teamID);
    }
    public String removeTeamMember(Integer memberID,Integer teamID)
    {
        return Main.getDBHandler().removeFromTeam(memberID, teamID);
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
    public void createMeeting(PersistantHandlerClass dbh, String name, String details, String location, String data, String time, Integer supID, Integer teamID, Integer instructorID) throws SQLException {
    dbh.saveNewMeetingInDB(name,details,location,data,time,supID,teamID,instructorID);
    Main.initializeLists();
}
    public void createTeam(PersistantHandlerClass dbh, String name, String details, Integer fypID, Integer supID) throws SQLException {
        dbh.saveNewTeamInDB(name,details,fypID,supID);
        Main.initializeLists();
    }
    public void createTask(PersistantHandlerClass dbh, String name, String details, Integer memberID) throws SQLException {
        Integer fypId=0;
        Integer teamId=0;
        for (teamMember tm: getTeamMembersList()) {
            if(tm.getMemberId().equals(memberID))
            {
                teamId=tm.getTeamId();
            }
        }
        for (team t:teamList) {
            if(t.getId().equals(teamId)){
                fypId=t.getFypId();
            }
        }
        dbh.saveTask(name,details,fypId.toString(),memberID.toString(),"assigned");
        Main.initializeLists();
    }
    public void updateTask(PersistantHandlerClass dbh, Integer taskId, String name, String details, Integer memberID) throws SQLException {
        Integer fypId=0;
        Integer teamId=0;
        for (teamMember tm: getTeamMembersList()) {
            if(tm.getMemberId().equals(memberID))
            {
                teamId=tm.getTeamId();
            }
        }
        for (team t:teamList) {
            if(t.getId().equals(teamId)){
                fypId=t.getFypId();
            }
        }
        dbh.updateTask(taskId,name,details,fypId.toString(),memberID.toString(),"approved");
        Main.initializeLists();
    }
    public void updateTeam(PersistantHandlerClass dbh, Integer teamID, String name, String details, Integer fypID) throws SQLException {
        dbh.updateTeamInDB(teamID,name,details,fypID);
        Main.initializeLists();
    }
    public void createProject(PersistantHandlerClass dbh, String projectName, String projectStatus){
        dbh.saveNewProjectInDB(projectName,projectStatus);
        Main.initializeLists();
    }
    public void updateProjectDetail(PersistantHandlerClass dbh, String fypName, String fypStatus, String fypID) throws SQLException {
        dbh.updateProjectDetails(fypName,fypStatus,fypID);
        Main.initializeLists();
    }

    public void deleteProject(String fypID) throws SQLException{
        finalYearProject fyp = new finalYearProject();
        fyp.deleteProject(fypID);
    }
    public ObservableList<finalYearProject> displaySupervisorProjects(PersistantHandlerClass dbh){
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
    public ObservableList<meetingSchedule> getMeetings(PersistantHandlerClass dbh){
        ObservableList<meetingSchedule> data = FXCollections.observableArrayList();
        for (meetingSchedule t:dbh.readMeetings()) {
            data.add(t);
        }
        return data;
    }

    public ArrayList<String> generateShortProjectProgressReport(String teamID){
        return supervisor.generateShortProjectProgressReport(teamID);
    }

    public ArrayList<team> fetchTeamData(){
        ArrayList<team> arr;
        arr=team.returnTeamList();
        return arr;
    }
    public ArrayList<teamMember> getSupervisorsTeamMembers(PersistantHandlerClass dbh, Integer supId){
        ArrayList <Integer> teamsList= new ArrayList<Integer>();
        for (supervisor t: dbh.readSupervisors()) {
            if(t.getId()==supId)
            {
                teamsList.add(t.getAssignedTeamId());
            }
        }
        Printing.PrintStr(teamsList.toString());
        ArrayList <teamMember> TMlist= new ArrayList<teamMember>();
        for (teamMember t: getTeamMembersList()) {
            if(teamsList.contains(t.getTeamId()))
            {
                TMlist.add(t);
            }
        }
        return TMlist;
    }
    public void selectSuggestNewTask(String taskName,String taskDetail) throws SQLException {
        user currentUser = Main.getLoggedInUser();

        teamMember currentTeamMember = null;

        for(int i=0; i<teamMembersList.size(); i++){
            System.out.println("TM ID:" + teamMembersList.get(i).getUserId());
            System.out.println("UID: "  + currentUser.getUserId());
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

        String fypID = curTeam.getFypId().toString();
        teamMember.suggestNewTask(taskName,taskDetail,fypID,Main.getLoggedInUser().getUserId().toString());

    }

    public ObservableList<task> selectViewOwnTasks(){
        return teamMember.viewOwnTasks();
    }

    public ObservableList<task> selectViewAllTasks(){
        return team.selectViewAllTask();
    }

    public Integer getTeamMemberTasksWithStatus(String status){
        return teamMember.getTasksWithStatus(status);
    }
    public boolean completeTask(String taskID){
        return teamMember.completeTask(taskID);
    }

    public ObservableList<teamTaskViewCapsule> openTaskView(){
        return teamMember.openTaskView();
    }

    public boolean assignTask(String taskID){
        return teamMember.assignTask(taskID);
    }

    public static XYChart.Series<String,Integer>  getTeamGraphData(){
        return team.getTeamGraphData();
    }

}
