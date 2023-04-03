package pt.nova.data;

/**
 * Represents a data object with information of a user.
 */
public class UserDTO {

    private String username;
    private String password;
    private String fullName;
    private float weight;
    private String type;
    // TODO
    // May need more fields, getters and setters.

    public UserDTO() {}

    public UserDTO(String username, String password, String fullName, float weight, String type) {
        super();
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.weight = weight;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("User [username=%s, fullName=%s, password=%s, weight=%.1f, type=%s]",
                             this.username, this.fullName, this.password, this.weight, this.type);
    }
}