package BussinessLogic;

import java.util.Date;

public class meetingSchedule {
    private Integer id;
    private Integer instructorId;
    private Integer supervisorId;
    private Integer teamId;
    private String time;
    private String date;
    private String location;
    private String meetingName;
    private String details;


    public meetingSchedule(Integer id, Integer instructorId, Integer supervisorId, Integer teamId, String time, String date, String location, String meetingName, String details) {
        this.id = id;
        this.instructorId = instructorId;
        this.supervisorId = supervisorId;
        this.teamId = teamId;
        this.time = time;
        this.date = date;
        this.location = location;
        this.meetingName = meetingName;
        this.details = details;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
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
