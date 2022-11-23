package BussinessLogic;

public class teamMember extends user{
    private Integer teamId;

    public teamMember(Integer userId, String userName, String firstName, String lastName, String password, String userType) {
        super(userId, userName, firstName, lastName, password, userType);
    }
}
