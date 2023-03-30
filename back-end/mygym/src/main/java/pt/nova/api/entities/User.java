package pt.nova.api.entities;

public class User {

    private String userId;
    private String name;
    private float weight;

    public User(){}

    public User(String userId, String name, float weight) {
        super();
        this.userId = userId;
        this.name = name;
        this.weight = weight;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public float getUserWeight() {
        return weight;
    }

    public void setUserWeight(float weight) {
        this.weight = weight;
    }

}