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
