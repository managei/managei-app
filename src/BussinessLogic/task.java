package BussinessLogic;

public class task {
    private Integer id;
    private String name;
    private String detail;
    private String status;
    private Integer fypId;
    private Integer memberId;

    public task(Integer id, String name, String detail, String status, Integer fypId, Integer memberId) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.status = status;
        this.fypId = fypId;
        this.memberId = memberId;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getFypId() {
        return fypId;
    }

    public void setFypId(Integer fypId) {
        this.fypId = fypId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
