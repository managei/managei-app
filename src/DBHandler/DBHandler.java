package DBHandler;


import ApplicationUI.Main;
import BussinessLogic.*;
import Utils.Printing;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {

    Connection con; // connection object

    public DBHandler() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ManageiTaskManagementSystem", "root", "root");
            Printing.PrintStr("Connection Done");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public ArrayList<finalYearProject> readFYP() {
        Statement stm;
        ArrayList<finalYearProject> fypList = new ArrayList<finalYearProject>();
        try {
            stm = con.createStatement();
            String query = "SELECT * FROM finalYearProject";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                finalYearProject fyp = new finalYearProject(rs.getInt("fypID"), rs.getString("fypName"), rs.getString("fypStatus"));
                if (fyp != null)
                    fypList.add(fyp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fypList;
    }
    public ArrayList<team> readTeams() {
        Statement stm;
        ArrayList<team> teamList = new ArrayList<team>();
        try {
            stm = con.createStatement();
            String query = "SELECT * FROM team";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                team t = new team(rs.getInt("teamID"), rs.getString("teamName"),rs.getString("teamDetails"), rs.getInt("fypId"));
                if (t != null)
                    teamList.add(t);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return teamList;
    }
    public ArrayList<task> readTasks() {
        Statement stm;
        ArrayList<task> tasksList = new ArrayList<task>();
        try {
            stm = con.createStatement();
            String query = "SELECT * FROM task";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                task t = new task(rs.getInt("taskID"), rs.getString("taskName"), rs.getString("taskDetail"), rs.getString("taskStatus"),rs.getInt("fypID"),rs.getInt("memberID"));
                if (t != null)
                    tasksList.add(t);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tasksList;
    }
    public ArrayList<user> readUsers() {
        Statement stm;
        ArrayList<user> userList = new ArrayList<user>();
        try {
            stm = con.createStatement();

            String query = "SELECT * FROM users";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                user u = switch (rs.getString("userType")) {
                    case "admin" ->
                            new admin(rs.getInt("userId"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("password"), rs.getString("userType"));
                    case "supervisor" ->
                            new supervisor(rs.getInt("userId"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("password"), rs.getString("userType"));
                    case "teamMember" ->
                            new teamMember(rs.getInt("userId"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("password"), rs.getString("userType"));
                    case "headOfDepartment" ->
                            new headOfDepartment(rs.getInt("userId"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("password"), rs.getString("userType"));
                    case "fypLabInstructor" ->
                            new fypLabInstructor(rs.getInt("userId"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("password"), rs.getString("userType"));
                    default -> null;
                };
                if (u != null)
                    userList.add(u);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return userList;
    }
    public ArrayList<supervisor> readSupervisors(){

        ArrayList<supervisor> arr = new ArrayList<supervisor>();
        ResultSet rs = executeGenericSelectQueryAndGetResultSet("select* from supervisor s inner join users u on s.supervisorID=u.userID;");

        try {
            while (rs.next()) {
                supervisor s = new supervisor(
                        rs.getInt("supervisorID"),rs.getString("userName"),
                        rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("password"),rs.getString("userType"));
                arr.add(s);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return arr;
    }
    public ArrayList<teamMember> readTeamMembers(){

        ArrayList<teamMember> arr = new ArrayList<teamMember>();
        ResultSet rs = executeGenericSelectQueryAndGetResultSet("select * from teamMember s inner join users u on s.memberID=u.userID;");
        try {
            while (rs.next()) {
                teamMember s = new teamMember(
                        rs.getInt("userID"),rs.getString("userName"),
                        rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("password"),rs.getString("userType"),
                        rs.getInt("teamID"));
                arr.add(s);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return arr;
    }
    public void saveUser(user u) {
        try {

            String query = "INSERT INTO users (userName,password,firstName,lastName,userType) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, u.getUserName());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getFirstName());
            stmt.setString(4, u.getLastName());
            stmt.setString(5, u.getType());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("A user was added...");
                String query2;
                ResultSet rs = executeGenericSelectQueryAndGetResultSet("SELECT userID AS LastID FROM users WHERE userID = @@Identity;");
                rs.next();
                switch (u.getType())
                {
                    case "supervisor" ->{
                        query2="insert into supervisor (supervisorID) values ("+ rs.getInt(1) +")";
                        System.out.println(query2);
                        executeGenericInsertQuery(query2);
                    }
                    case "teamMember" ->
                    {
                        query2="insert into teamMember (memberID) values ("+ "'" + rs.getInt(1) + "'" + ")";
                        System.out.println(query2);
                        executeGenericInsertQuery(query2);
                    }
                    case "headOfDepartment" ->
                    {
                        query2="insert into headOfDepartment (hodID) values ("+ "'" + rs.getInt(1) + "'" + ")";
                        System.out.println(query2);
                        executeGenericInsertQuery(query2);
                    }
                    case "FYPLabInstructor"->{
                        query2="insert into labInstructor (instructorID) values ("+ "'" + rs.getInt(1) + "'" + ")";
                        System.out.println(query2);
                        executeGenericInsertQuery(query2);
                    }
                    default -> {
                    }
                }
            } else
                System.out.println("User register failed...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void readTable(String table) {
        Statement stm;
        try {
            stm = con.createStatement();

            String query = "SELECT * FROM " + table;
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Printing.PrintStr(rs.getString("userName"));
                Printing.PrintStr("-----------------");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String executeGenericInsertQuery(String query){
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Data added to DB");
        return "Operation Success";
    }

    public void executeGenericUpdateDeleteQuery(String query) throws SQLException{
        Statement st = null;
//        try {
            st = con.createStatement();
            st.executeUpdate(query);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        System.out.println("Data updated/Deleted in DB");
    }
    public ResultSet executeGenericSelectQueryAndGetResultSet(String q) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(q);
            return rs;
        } catch (SQLException sqe) {
            System.out.println("Query Execution Error");
        }
        return null;
    }
    public String addToTeam(Integer memberID,Integer teamID){
        String query="update teamMember set teamID= "+ "'" +teamID + "'" + " Where memberID = " + "'" + memberID + "';";
        System.out.println(query);
        return executeGenericInsertQuery(query);
    }
    public void saveNewProjectInDB(String fypName,String fypStatus){
        String query="insert into finalYearProject (fypName,fypStatus) values ("+ "'" +fypName + "'" + "," + "'" + fypStatus + "'" + ")";
//        System.out.println(query);
        executeGenericInsertQuery(query);
    }
    public void saveNewTeamInDB(String teamName,String teamDetails,Integer fypId){
        String query="insert into team (teamName,teamDetails,fypID) values ("+ "'" +teamName + "'" + "," + "'" + teamDetails + "'" + ","+fypId+")";
        System.out.println(query);
        executeGenericInsertQuery(query);
    }

    public void saveTask(String taskName,String taskDetail,String fypID,String memberID,String taskStatus){
        String query="insert into task (fypID,memberID,taskName,taskDetail,taskStatus) values ("
                 + fypID + ", " + memberID + ", " + "'" + taskName + "', " + "'" + taskDetail + "', '" + taskStatus + "');";
        System.out.println(query);
        executeGenericInsertQuery(query);
    }

    public void updateProjectDetails(String fypName,String fypStatus,String fypID) throws SQLException{
        String query= "UPDATE manageitaskmanagementsystem.finalyearproject" +
                " SET manageitaskmanagementsystem.finalyearproject.fypName='" + fypName +"', manageitaskmanagementsystem.finalyearproject.fypStatus='" +
                fypStatus +"' WHERE manageitaskmanagementsystem.finalyearproject.fypID=" + fypID + ";";
        executeGenericUpdateDeleteQuery(query);
    }

    public void deleteProject(String fypID) throws SQLException{
        String query= "delete from manageitaskmanagementsystem.finalyearproject where manageitaskmanagementsystem.finalyearproject.fypID=" + fypID + ";";
        executeGenericUpdateDeleteQuery(query);
    }

    public ArrayList<String> generateShortReportData(String teamID){
        ArrayList<String> arr = new ArrayList<String>();

        ResultSet rs = executeGenericSelectQueryAndGetResultSet("select f.fypID, f.fypName, f.fypStatus from finalYearProject f inner join team t on f.fypID=t.fypID where t.teamID='" + teamID + "';");

        try {
            rs.next();
            arr.add(rs.getString(1));
            arr.add(rs.getString(2));
            arr.add(rs.getString(3));

            rs = executeGenericSelectQueryAndGetResultSet("select count(t.taskID) from task t inner join team tm on t.fypID=tm.fypID where tm.teamID=" + teamID + ";");
            rs.next();
            arr.add(rs.getString(1));

            rs = executeGenericSelectQueryAndGetResultSet("select count(t.taskID) from task t inner join team tm on t.fypID=tm.fypID where tm.teamID=" + teamID + " and t.taskStatus='complete';");
            rs.next();
            arr.add(rs.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arr;
    }

    public void updateTaskStatus(String taskID,String status) throws SQLException{
        String query= "update task set taskStatus='" + status + "' where taskID=" + taskID + ";";
        executeGenericUpdateDeleteQuery(query);
    }

//    public ObservableList<ObservableList<String>> getDataforTableUsingQuery(String query){
//        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
//
//        ResultSet rs = executeGenericSelectQueryAndGetResultSet(query);
//        ObservableList<String> currentRow = FXCollections.observableArrayList();
//        try{
//            while(rs.next()) {
//                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                    currentRow.add(rs.getString(i));
//                }
//                data.add(currentRow);
//            }
//        }catch (SQLException sql){
//            sql.printStackTrace();
//        }
//
//        return data;
//    }

    public ObservableList<finalYearProject> getDataforSupervisorProjects(){
        ObservableList<finalYearProject> data = FXCollections.observableArrayList();

        user u= Main.getLoggedInUser();
        String user_id = u.getId().toString();
        ResultSet rs = executeGenericSelectQueryAndGetResultSet(
                "select f.fypID,f.fypName,f.fypStatus,t.teamID from manageitaskmanagementsystem.finalyearproject f inner join manageitaskmanagementsystem.team t on f.fypID=t.fypID inner join manageitaskmanagementsystem.supervisor s on s.assignedTeamID=t.teamID where s.supervisorID=" + user_id + ";");
        try{
            while(rs.next()) {
                finalYearProject fyp = new finalYearProject(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                data.add(fyp);
            }
        }catch (SQLException sql){
            sql.printStackTrace();
        }

        return data;
    }
    public ObservableList<user> getDataforAllUsers(){
        ObservableList<user> data = FXCollections.observableArrayList();
        ResultSet rs = executeGenericSelectQueryAndGetResultSet(
                "select * from manageitaskmanagementsystem.users");
        try{
            while(rs.next()) {
                user newUser = user.returnUserByType( rs.getInt("userId"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("password"), rs.getString("userType"));
                data.add(newUser);
            }
        }catch (SQLException sql){
            sql.printStackTrace();
        }

        return data;
    }


    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
