package BussinessLogic;

public class teamMember extends user{
    private Integer teamId;

    public teamMember(Integer userId, String userName, String firstName, String lastName, String password, String userType) {
        super(userId, userName, firstName, lastName, password, userType);
    }
    public teamMember(Integer userId, String userName, String firstName, String lastName, String password, String userType,Integer teamId) {
        super(userId, userName, firstName, lastName, password, userType);
        this.teamId=teamId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
