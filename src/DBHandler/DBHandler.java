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

    public ArrayList<finalYearProject> readFyps(){

        ArrayList<finalYearProject> arr = new ArrayList<finalYearProject>();
        ResultSet rs = executeGenericSelectQueryAndGetResultSet("select* from finalYearProject");

        try {
            while (rs.next()) {
                finalYearProject fyp = new finalYearProject(Integer.parseInt(rs.getString("fypID")), rs.getString("fypName"),rs.getString("fypStatus"));
                arr.add(fyp);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return arr;
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

    public ArrayList<team> readTeams(){

        ArrayList<team> arr = new ArrayList<team>();
        ResultSet rs = executeGenericSelectQueryAndGetResultSet("select* from team;");

        try {
            while (rs.next()) {
                team t = new team(rs.getInt(1),rs.getString("teamName"),rs.getString("fypID"),"NULL");
                arr.add(t);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return arr;
    }

    public ArrayList<task> readTasks(){

        ArrayList<task> arr = new ArrayList<task>();
        ResultSet rs = executeGenericSelectQueryAndGetResultSet("select* from task;");

        try {
            while (rs.next()) {
                task t = new task(rs.getInt("taskID"),rs.getString("taskName"),rs.getString("taskDetail"),
                        rs.getString("taskStatus"),rs.getInt("fypID"),rs.getInt("memberID"));
                arr.add(t);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return arr;
    }

    public ArrayList<teamMember> readMembers(){

        ArrayList<teamMember> arr = new ArrayList<teamMember>();
        ResultSet rs = executeGenericSelectQueryAndGetResultSet("select* from teammember m inner join users u on m.memberID=u.userID;");

        try {
            while (rs.next()) {
                teamMember t = new teamMember(rs.getInt(1),rs.getString("userName"),
                        rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("password"),rs.getString("userType"),rs.getInt(2));
                arr.add(t);
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
            stmt.setString(5, u.getUserType());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("A user was added...");
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

    public void executeGenericInsertQuery(String query){
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Data added to DB");
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

    public void saveNewProjectInDB(String fypName,String fypStatus){
        String query="insert into finalYearProject (fypName,fypStatus) values ("+ "'" +fypName + "'" + "," + "'" + fypStatus + "'" + ")";
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

            rs = executeGenericSelectQueryAndGetResultSet("select count(t.taskID) from task t inner join team tm on t.fypID=tm.fypID where tm.teamID=" + teamID + " and t.taskStatus='Done';");
            rs.next();
            arr.add(rs.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arr;
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
        String user_id = u.getUserId().toString();
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


    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
