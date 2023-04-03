package pt.nova.resources;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import jakarta.inject.Singleton;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import pt.nova.api.RestUsers;
import pt.nova.api.entities.User;
import pt.nova.data.UserDTO;

/**
 * Implementation of the users rest resource.
 */
@Singleton
public class UsersResource implements RestUsers {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private static Logger Log = Logger.getLogger(UsersResource.class.getName());

    public UsersResource() {}

    @Override
    public String createUser(UserDTO user) {
        Log.info("createUser : " + user);

        // Check if the user is valid, if not, return HTTP BAD REQUEST (400)
		validateFields(user.getUsername(), user.getPassword(), user.getFullName(), user.getWeight());

        // Create a random id for the user and add them to the map of users
		User newUser = new User(generateUserID(), user);
		
		return newUser.getUserId();
    }

    @Override
    public User getUser(String userId, String password) {
        Log.info("getUser : user = " + userId + "; pwd = " + password);

		return fetchUser(userId, password);
    }

    @Override
    public User updateUser(String userId, String password, UserDTO user) {
		Log.info("getUser : user = " + userId + "; pwd = " + password + "; fields = " + user.toString());

		User newUser = fetchUser(userId, password);

		newUser.setFields(user);

        return newUser;
    }

    @Override
    public User deleteUser(String userId, String password) {
		Log.info("deleteUser : user = " + userId + "; pwd = " + password);

		User user = fetchUser(userId, password);

		// Removes the user
		users.remove(userId, user);

		return user;
    }

    /**
	 * Checks if the fields are invalid and if so throws appropriate request.
	 *
	 * @param username  the user name
     * @param password  user password
	 * @param fullname  user fullname
     * @param weight    user weight
	 * @return
	 */
	private void validateFields(String username, String password, String fullname, float weight){

		if (username == null || password == null || fullname == null) {
			Log.info("One or more fields are null.");
			throw new WebApplicationException( Status.BAD_REQUEST );
		}
        if (weight <= 0f) {
            Log.info("The weight is not valid.");
            throw new WebApplicationException( Status.BAD_REQUEST );
        }
        
	}

	private String generateUserID() {
		String id;
		do {
			UUID uuid = UUID.randomUUID();
			id = uuid.toString();
		} while (users.containsKey(id));

		return id;
	}

    /**
	 * Fetches a user given it's userId.
	 * If the id does not exist, return HTTP NOT FOUND (404).
	 * If the password is incorrect, return HTTP FORBIDDEN (403).
	 *
	 * @param userId   the user id
	 * @param password the authentication password
	 * @return user
	 */
	private User fetchUser(String userId, String password) {

		User user;

		// Check if user exists
		if (userId == null || (user = users.get(userId)) == null) {
			Log.info("User does not exist.");
			throw new WebApplicationException( Status.NOT_FOUND );
		}

		//Check if the password is correct
		if (!user.getPassword().equals(password)) {
			Log.info("Password is incorrect.");
			throw new WebApplicationException( Status.FORBIDDEN );
		}

		return user;

	}
    
}
