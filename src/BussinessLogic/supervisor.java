package BussinessLogic;

public class supervisor extends user {
    private Integer assignedTeamId;

    public supervisor(Integer userId, String userName, String firstName, String lastName, String password, String userType) {
        super(userId, userName, firstName, lastName, password, userType);
    }
}
