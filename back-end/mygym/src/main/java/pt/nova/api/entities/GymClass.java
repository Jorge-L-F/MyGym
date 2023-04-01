package pt.nova.api.entities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GymClass {
    
    private String classId;
    private String teacherId;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<String> enrolledUsers;

    public GymClass() {}

    public GymClass(String classId, String teacherId, LocalTime startTime, LocalTime endTime) {
        super();
        this.classId = classId;
        this.teacherId = teacherId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.enrolledUsers = new ArrayList<>();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<String> getEnrolledUsers() {
        return enrolledUsers;
    }

    public void enrollUser(String userId) {
        enrolledUsers.add(userId);
    }

    public boolean removeUser(String userId) {
        return enrolledUsers.remove(userId);
    }

}