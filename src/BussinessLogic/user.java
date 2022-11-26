package BussinessLogic;

public abstract class user {
    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String type;

    @Override
    public String toString() {
        return "user{" +
                "userId=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + type + '\'' +
                '}';
    }

    public user(Integer userId, String userName, String firstName, String lastName,String password, String userType) {
        this.id = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.type = userType;
    }
    public boolean checkCredentials(String userName,String password)
    {
        if (this.userName.equals(userName)&&this.password.equals(password))
            return true;
        else
            return false;

    }
    public Integer getUserId() {
        return id;
    }

    public void setUserId(Integer userId) {
        this.id = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String lastName) {
        this.password = password;
    }

    public String getUserType() {
        return type;
    }

    public void setUserType(String userType) {
        this.type = userType;
    }
}
