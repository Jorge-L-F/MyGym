package pt.nova.api.entities;

/**
 * Represents a user in the app.
 */
public class User {

    private String userId;
    private String username;
    private String password;
    private String fullName;
    private float weight;
    // TODO
    // May need more fields, getters and setters.

    public User() {}

    public User(String userId, String username, String password, String fullName, float weight) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.weight = weight;
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

    @Override
    public String toString() {
        return String.format("User [userid=%s, username=%s, fullName=%s, password=%s, weight=%.1f]",
                             this.userId, this.username, this.fullName, this.password, this.weight);
    }

}