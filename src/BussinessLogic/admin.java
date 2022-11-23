package BussinessLogic;

public class admin extends user {
    private Integer adminId;
    public admin(Integer adminId, Integer userId, String userName, String firstName, String lastName,String password, String userType) {
        super(userId, userName, firstName, lastName,password, userType);
        this.adminId=adminId;
    }

//    @Override
//    public String toString() {
//        return "admin{" +
//                "adminId=" + adminId +
//                "} " + super.toString();
//    }
}
