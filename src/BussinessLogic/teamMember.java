package BussinessLogic;

import ApplicationUI.Main;
import DBHandler.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class teamMember extends user{
    private Integer teamId;

    public ArrayList<teamMember> getTeamMembersList() {
        return teamMembersList;
    }

    public void setTeamMembersList(ArrayList<teamMember> teamMembersList) {
        this.teamMembersList = teamMembersList;
    }

    private static ArrayList<teamMember> teamMembersList = null;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    private Integer memberId;

    public teamMember(Integer userId, String userName, String firstName, String lastName, String password, String userType) {
        super(userId, userName, firstName, lastName, password, userType);
        memberId=userId;
        teamMembersList=dashboard.getTeamMembersList();
    }

    public teamMember(Integer userId, String userName, String firstName, String lastName, String password, String userType,Integer teamId) {
        super(userId, userName, firstName, lastName, password, userType);
        memberId=userId;
        this.teamId=teamId;
    }
    public static void suggestNewTask(String taskName,String taskDetail,String fypID,String userID){
        DBHandler dbh = new DBHandler();
        dbh.saveTask(taskName,taskDetail,fypID,userID,"suggested");
    }

    public static ObservableList<task> viewOwnTasks(){
        Main.initializeLists();
        ObservableList<task> t = FXCollections.observableArrayList();

        for(int i=0; i<dashboard.getTaskList().size(); i++){
            if(dashboard.getTaskList().get(i).getMemberId()==Main.getLoggedInUser().getUserId()){
                t.add(dashboard.getTaskList().get(i));
            }
        }
        return t;
    }

    public static Integer getTasksWithStatus(String status){
        Main.initializeLists();
        int count=0;
        teamMember curMember=null;

        for(int i=0; i<dashboard.getTeamMembersList().size(); i++){
            if(dashboard.getTeamMembersList().get(i).getMemberId()==Main.getLoggedInUser().getUserId()){
                curMember=dashboard.getTeamMembersList().get(i);
                break;
            }
        }

//        System.out.println("Selected member:" + curMember.getMemberId().toString());

        if(curMember==null) return -1;

        team curTeam=null;

        for(int i=0; i<dashboard.getTeamList().size(); i++){
            if(dashboard.getTeamList().get(i).getId()==curMember.getTeamId()){
                curTeam=dashboard.getTeamList().get(i);
                break;
            }
        }

        if(curTeam==null) return -2;

//        System.out.println("Selected team:" + curTeam.getId().toString());
//        System.out.println("Selected fypID:" + curTeam.getFypId());


        for(int i=0; i<dashboard.getTaskList().size(); i++){
//            System.out.println("Checking task with fypID:" + dashboard.getTaskList().get(i).getFypId() + ", Member ID:" + dashboard.getTaskList().get(i).getMemberId());
            if(dashboard.getTaskList().get(i).getFypId().toString().equals(curTeam.getFypId()) && dashboard.getTaskList().get(i).getStatus().equals(status)){
//                System.out.println("Checking: " + dashboard.getTaskList().get(i).getId());
                if(dashboard.getTaskList().get(i).getMemberId()==curMember.getMemberId()){
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean completeTask(String taskID){
        DBHandler dbh = new DBHandler();
        try {
            dbh.updateTaskStatus(taskID,"complete");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
