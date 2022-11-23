package DBHandler;


import Utils.Printing;

import java.sql.*;
import java.util.ArrayList;

public class DBHandler {

    Connection con; // connection object

    public DBHandler() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ManageiTaskManagementSystem", "root", "Razi.432");
            Printing.PrintStr("Connection Done");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void readTable(String table) {
        Statement stm;
        try {
            stm = con.createStatement();

            String query = "SELECT * FROM "+table;
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
        }
        catch (SQLException sqe){
            System.out.println("Query Execution Error");
        }
        return null;
    }

//    public boolean getUserFromDBTrueFalse(String username, String Password) {
//        String query = "select* from users where username='" + username + "'";
//        ResultSet rs = executeGenericSelectQueryAndGetResultSet(query);
//
//        ArrayList<String> arr=new ArrayList<String>();
//        try {
//            while (rs.next()) {
//                 arr.add(rs.getString(3));
//            }
//        }catch (SQLException sqe){
//            return false;
//        }
//
//        if(arr.get(0).equals(Password)) return true;
//        else return  false;
//    }

//    public void saveStudent(Student s) {
//        try {
//
//            String query = "INSERT INTO students (roll_no, name,batch,section) VALUES (?,?,?,?)";
//            PreparedStatement stmt = con.prepareStatement(query);
//            stmt.setInt(1, s.getRoll_no());
//            stmt.setString(2, s.getName());
//            stmt.setString(3, s.getBatch());
//            stmt.setString(4, s.getSection());
//
//            int rows = stmt.executeUpdate();
//            if (rows > 0) {
//                System.out.println("A student was added...");
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }


    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
