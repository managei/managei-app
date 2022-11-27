package DBHandler;


import ApplicationUI.Main;
import BussinessLogic.*;
import Utils.Printing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class PersistantHandlerClass {

    public static PersistantHandlerClass getPh() {
        return ph;
    }

    public static void setPh(PersistantHandlerClass ph) {
        PersistantHandlerClass.ph = ph;
    }

    private static PersistantHandlerClass ph =null;
    Connection con; // connection object

    public PersistantHandlerClass() {
    }

    public PersistantHandlerClass(String handler) {
        if(handler.equals("db")){
            if(PersistantHandlerClass.getPh()==null) PersistantHandlerClass.setPh(new DBHandler());
        }
    }
    public ArrayList<finalYearProject> readFYP() {
        return PersistantHandlerClass.getPh().readFYP();
    }
    public ArrayList<team> readTeams() {
        return PersistantHandlerClass.getPh().readTeams();
    }
    public ArrayList<task> readTasks() {
        return PersistantHandlerClass.getPh().readTasks();
    }
    public ArrayList<user> readUsers() {
        return  PersistantHandlerClass.getPh().readUsers();
    }
    public ArrayList<supervisor> readSupervisors(){
        return PersistantHandlerClass.getPh().readSupervisors();
    }
    public ArrayList<teamMember> readTeamMembers(){
        return PersistantHandlerClass.getPh().readTeamMembers();
    }
    public ArrayList<meetingSchedule> readMeetings(){
        return PersistantHandlerClass.getPh().readMeetings();
    }
    public void saveUser(user u) {
        PersistantHandlerClass.getPh().saveUser(u);
    }

    public void readTable(String table) {
        PersistantHandlerClass.getPh().readTable(table);
    }

    public String executeGenericInsertQuery(String query){
        return PersistantHandlerClass.getPh().executeGenericInsertQuery(query);
    }

    public void executeGenericUpdateDeleteQuery(String query) throws SQLException{
        PersistantHandlerClass.getPh().executeGenericUpdateDeleteQuery(query);
    }
    public ResultSet executeGenericSelectQueryAndGetResultSet(String q) {
        return PersistantHandlerClass.getPh().executeGenericSelectQueryAndGetResultSet(q);
    }
    public String addToTeam(Integer memberID,Integer teamID){
        return PersistantHandlerClass.getPh().addToTeam(memberID,teamID);
    }
    public String removeFromTeam(Integer memberID,Integer teamID){
        return PersistantHandlerClass.getPh().removeFromTeam(memberID,teamID);
    }
    public void saveNewProjectInDB(String fypName,String fypStatus){
        PersistantHandlerClass.getPh().saveNewProjectInDB(fypName,fypStatus);
    }
    public void saveNewTeamInDB(String teamName,String teamDetails,Integer fypId,Integer supId) throws SQLException {
        PersistantHandlerClass.getPh().saveNewTeamInDB(teamName,teamDetails,fypId,supId);
    }
    public void saveNewMeetingInDB(String name,String details,String location,String date,String time,Integer supId,Integer teamId,Integer instructorId) throws SQLException {
        PersistantHandlerClass.getPh().saveNewMeetingInDB(name,details,location,date,time,supId,teamId,instructorId);
    }
    public void updateTeamInDB(Integer teamID,String teamName,String teamDetails,Integer fypId) throws SQLException {
        PersistantHandlerClass.getPh().updateTeamInDB(teamID,teamName,teamDetails,fypId);
    }
    public void saveTask(String taskName,String taskDetail,String fypID,String memberID,String taskStatus){
        PersistantHandlerClass.getPh().saveTask(taskName,taskDetail,fypID,memberID,taskStatus);
    }
    public void updateTask(Integer taskId, String taskName,String taskDetail,String fypID,String memberID,String taskStatus) throws SQLException {
        PersistantHandlerClass.getPh().updateTask(taskId,taskName,taskDetail,fypID,memberID,taskStatus);
    }
    public void updateProjectDetails(String fypName,String fypStatus,String fypID) throws SQLException{
        PersistantHandlerClass.getPh().updateProjectDetails(fypName,fypStatus,fypID);
    }

    public void deleteProject(String fypID) throws SQLException{
        PersistantHandlerClass.getPh().deleteProject(fypID);
    }

    public ArrayList<String> generateShortReportData(String teamID){
       return PersistantHandlerClass.getPh().generateShortReportData(teamID);
    }

    public void updateTaskStatus(String taskID,String status) throws SQLException{
        PersistantHandlerClass.getPh().updateTaskStatus(taskID,status);
    }

//    public ObservableList<ObservableList<String>> getDataforTableUsingQuery(String query){
//        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
//
//        ResultSet rs = executeGenericSelectQueryAndGetResultSet(query);
//        ObservableList<String> currentRow = FXCollections.observableArrayList();
//        try{
//            while(rs.next()) {
//                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                    currentRow.add(rs.getString(i));
//                }
//                data.add(currentRow);
//            }
//        }catch (SQLException sql){
//            sql.printStackTrace();
//        }
//
//        return data;
//    }

    public ObservableList<finalYearProject> getDataforSupervisorProjects(){
        return PersistantHandlerClass.getPh().getDataforSupervisorProjects();
    }
    public ObservableList<user> getDataforAllUsers(){
        return PersistantHandlerClass.getPh().getDataforAllUsers();
    }

    public ObservableList<teamTaskViewCapsule> generateTeamTaskView(String teamID){
        return PersistantHandlerClass.getPh().generateTeamTaskView(teamID);
    }

    public void closeConnection() {
        PersistantHandlerClass.getPh().closeConnection();
    }
}
