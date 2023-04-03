package pt.nova.data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a data object with information of a gym class.
 */
public class GymClassDTO {
    
    private String teacherId;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<String> enrolledUsers;

    public GymClassDTO() {}

    public GymClassDTO(String teacherId, LocalTime startTime, LocalTime endTime) {
        super();
        this.teacherId = teacherId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.enrolledUsers = new ArrayList<>();
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