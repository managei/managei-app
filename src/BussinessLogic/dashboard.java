package BussinessLogic;

import java.util.ArrayList;

public class dashboard {
    static private ArrayList<user> userList=null;
    static private ArrayList<team> teamList=null;
    static private ArrayList<finalYearProject> fypList=null;
    static private ArrayList<task> taskList=null;
    public user loginUser(String userName,String password)
    {
        for (user u:
             userList) {
            if(u.checkCredentials(userName,password))
            return u;
        }
        return null;
    }
    public user registerUser(Integer userId, String userName, String firstName, String lastName, String password, String userType)
    {
        user ad = switch (userType) {
            case "admin" -> new admin(userId, userName, firstName, lastName, password, userType);
            case "supervisor" -> new supervisor(userId, userName, firstName, lastName, password, userType);
            case "teamMember" -> new teamMember(userId, userName, firstName, lastName, password, userType);
            case "headOfDepartment" -> new headOfDepartment(userId, userName, firstName, lastName, password, userType);
            case "fypLabInstructor" -> new fypLabInstructor(userId, userName, firstName, lastName, password, userType);
            default -> null;
        };
        userList.add(ad);
        return ad;
    }

    public static ArrayList<user> getUserList() {
        return userList;
    }

    public static void setUserList(ArrayList<user> userList) {
        dashboard.userList = userList;
    }

    public static ArrayList<team> getTeamList() {
        return teamList;
    }

    public static void setTeamList(ArrayList<team> teamList) {
        dashboard.teamList = teamList;
    }

    public static ArrayList<finalYearProject> getFypList() {
        return fypList;
    }

    public static void setFypList(ArrayList<finalYearProject> fypList) {
        dashboard.fypList = fypList;
    }

    public static ArrayList<task> getTaskList() {
        return taskList;
    }

    public static void setTaskList(ArrayList<task> taskList) {
        dashboard.taskList = taskList;
    }
}
