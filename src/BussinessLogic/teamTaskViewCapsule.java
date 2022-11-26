package BussinessLogic;

public class teamTaskViewCapsule
{
    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public Integer getMemberID() {
        return memberID;
    }

    public void setMemberID(Integer memberID) {
        this.memberID = memberID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    private Integer taskID;
    private Integer memberID;
    private String taskName;
    private String detail;
    private String status;

    public teamTaskViewCapsule(Integer taskID, Integer memberID, String taskName, String detail, String status) {
        this.taskID = taskID;
        this.memberID = memberID;
        this.taskName = taskName;
        this.detail = detail;
        this.status = status;
    }



}
