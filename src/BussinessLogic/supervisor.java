package BussinessLogic;

import DBHandler.DBHandler;

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

    public supervisor(){
        super(-1,"","","","","supervisor");
    }

    public static ArrayList<String> generateShortProjectProgressReport(String teamID){
        DBHandler dbh = new DBHandler();
        ArrayList<String> arr = dbh.generateShortReportData(teamID);
        return arr;
    }
}
