package pt.nova.api.entities;

public class Teacher extends User {
    
    public Teacher() {
        super();
    }

    public Teacher(String userId, String username, String password, String fullName, float weight) {
        super(userId, username, password, fullName, weight);
    }

}