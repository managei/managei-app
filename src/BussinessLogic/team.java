package BussinessLogic;

public class team {
    private Integer id;
    private String name;
    private String fypId;
    private String hodId;

    public team(Integer id, String name, String fypId, String hodId) {
        this.id = id;
        this.name = name;
        this.fypId = fypId;
        this.hodId = hodId;
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

    public String getFypId() {
        return fypId;
    }

    public void setFypId(String fypId) {
        this.fypId = fypId;
    }

    public String getHodId() {
        return hodId;
    }

    public void setHodId(String hodId) {
        this.hodId = hodId;
    }
}
