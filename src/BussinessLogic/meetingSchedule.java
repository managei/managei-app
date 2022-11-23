package BussinessLogic;

import java.util.Date;

public class meetingSchedule {
    private Integer id;
    private Integer instructorId;
    private Integer supervisorId;
    private Integer teamId;
    private Date time;
    private String location;
    private String details;

    public meetingSchedule(Integer id, Integer instructorId, Integer supervisorId, Integer teamId, Date time, String location, String details) {
        this.id = id;
        this.instructorId = instructorId;
        this.supervisorId = supervisorId;
        this.teamId = teamId;
        this.time = time;
        this.location = location;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Integer instructorId) {
        this.instructorId = instructorId;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
