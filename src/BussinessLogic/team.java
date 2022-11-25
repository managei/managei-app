package BussinessLogic;

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
}
