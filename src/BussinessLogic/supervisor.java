package BussinessLogic;

import DBHandler.PersistantHandlerClass;

import java.util.ArrayList;

public class supervisor extends user {
    public Integer getAssignedTeamId() {
        return assignedTeamId;
    }

    public void setAssignedTeamId(Integer assignedTeamId) {
        this.assignedTeamId = assignedTeamId;
    }

    private Integer assignedTeamId;

    public supervisor(Integer userId, String userName, String firstName, String lastName, String password, String userType) {
        super(userId, userName, firstName, lastName, password, userType);
    }
    public supervisor(Integer userId, String userName, String firstName, String lastName, String password, String userType,Integer assignedTeamId) {
        super(userId, userName, firstName, lastName, password, userType);
        this.assignedTeamId=assignedTeamId;
    }
    public static ArrayList<String> generateShortProjectProgressReport(String teamID){
        PersistantHandlerClass dbh = new PersistantHandlerClass();
        ArrayList<String> arr = dbh.generateShortReportData(teamID);
        return arr;
    }
}
