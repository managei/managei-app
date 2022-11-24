package BussinessLogic;

public class finalYearProject {
    private Integer id;
    private String name;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeamID() {
        return teamID;
    }

    public void setTeamID(Integer teamID) {
        this.teamID = teamID;
    }

    private Integer teamID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    finalYearProject(){
        id=-1;
        name="";
        status="";
        teamID=-1;
    }

    public finalYearProject(String name, String status){
        this.id=id;
        this.name=name;
        this.status=status;
        this.teamID=teamID;
    }
    public finalYearProject(Integer id, String name, String status, Integer teamID){
        this.id=id;
        this.name=name;
        this.status=status;
        this.teamID=teamID;
    }

    finalYearProject(Integer id, String name, String status){
        this.id=id;
        this.name=name;
        this.status=status;
    }

}
