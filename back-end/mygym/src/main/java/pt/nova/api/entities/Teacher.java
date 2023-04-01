package pt.nova.api.entities;

/**
 * Represents a teacher in the app.
 */
public class Teacher extends User {
    
    public Teacher() {
        super();
    }

    public Teacher(String userId, String username, String password, String fullName, float weight) {
        super(userId, username, password, fullName, weight);
    }

}