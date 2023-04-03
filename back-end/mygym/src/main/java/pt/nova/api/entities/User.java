package pt.nova.api.entities;

import pt.nova.data.UserDTO;

/**
 * Represents a user in the app.
 */
public class User {

    private String userId;
    private String username;
    private String password;
    private String fullName;
    private float weight;
    private String type;
    // TODO
    // May need more fields, getters and setters.

    public User() {}

    public User(String userId, String username, String password, String fullName, float weight, String type) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.weight = weight;
        this.type = type;
    }

    public User(String userId, UserDTO user) {
        super();
        this.userId = userId;
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.fullName = user.getFullName();
        this.weight = user.getWeight();
        this.type = user.getType();
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

    /**
     * Changes the applicable fields of this object according to the fields of the data object.
     *
     * @param user the object with the fields to set
     */
    public void setFields(UserDTO user) {
        if (user.getUsername() != null) this.setUsername(user.getUsername());
        if (user.getFullName() != null) this.setFullName(user.getFullName());
        if (user.getPassword() != null) this.setPassword(user.getPassword());
        if (user.getWeight() >= 0f) this.setWeight(user.getWeight());
        if (user.getType() != null) this.setType(user.getType());
    }

    @Override
    public String toString() {
        return String.format("User [userid=%s, username=%s, fullName=%s, password=%s, weight=%.1f, type=%s]",
                             this.userId, this.username, this.fullName, this.password, this.weight, this.type);
    }

}