package BussinessLogic;

import ApplicationUI.Main;
import DBHandler.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class team {
    private Integer id;
    private String name;
    private String detail;
    private Integer fypId;

    public team(Integer id, String name,String detail, Integer fypId) {
        this.id = id;
        this.name = name;
        this.fypId = fypId;
        this.detail = detail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFypId() {
        return fypId;
    }

    public void setFypId(Integer fypId) {
        this.fypId = fypId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public static ArrayList<team> returnTeamList(){
        return dashboard.getTeamList();
    }

    public static ObservableList<teamTaskViewCapsule> getTeamTaskAndProgress(){
        DBHandler dbh = new DBHandler();
        Main.initializeLists();
        teamMember currentMember =null;

        for(int i=0;i<dashboard.getTeamMembersList().size(); i++){
            if(dashboard.getTeamMembersList().get(i).getMemberId()==Main.getLoggedInUser().getUserId()){
                currentMember=dashboard.getTeamMembersList().get(i);
                break;
            }
        }

        if(currentMember==null) return null;
        System.out.println("Selected: " + currentMember.getMemberId());
        return dbh.generateTeamTaskView(currentMember.getTeamId().toString());
    }

    public static ObservableList<task> selectViewAllTask(){
        ObservableList<task> arr = FXCollections.observableArrayList();
        DBHandler dbh = new DBHandler();
        Main.initializeLists();
        for(int i=0; i<dashboard.getTaskList().size(); i++){
            arr.add(dashboard.getTaskList().get(i));
        }
        return arr;
    }

}
