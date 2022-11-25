package BussinessLogic;

import DBHandler.DBHandler;

public class teamMember extends user{
    private Integer teamId;

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
}
