package pt.nova.resources;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import jakarta.inject.Singleton;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;
import pt.nova.api.RestUsers;
import pt.nova.api.entities.User;

/**
 * Implementation of the users rest resource.
 */
@Singleton
public class UsersResource implements RestUsers {

    private final Map<String, User> users = new ConcurrentHashMap<>();
    private static Logger Log = Logger.getLogger(UsersResource.class.getName());

    public UsersResource() {}

    @Override
    public String createUser(User user) {
        Log.info("createUser : " + user);

        // Check if the user is valid, if not, return HTTP BAD REQUEST (400)
		validateFields(user.getUserId(), user.getPassword(), user.getFullName(), user.getWeight());

        //Add the user to the map of users
		User oldUser = users.putIfAbsent(user.getUserId(), user);

        // Check if the user already exists, if yes, return HTTP CONFLICT (409)
		if(oldUser != null) {
			Log.info("User already exists.");
			throw new WebApplicationException( Status.CONFLICT );
		}
		
		return user.getUserId();
    }

    @Override
    public User getUser(String userId, String password) {
        Log.info("getUser : user = " + userId + "; pwd = " + password);

		return fetchUser(userId, password);
    }

    @Override
    public User updateUser(String userId, String password, User user) {
		Log.info("getUser : user = " + userId + "; pwd = " + password + "; fields = " + user.toString());

		User newUser = fetchUser(userId, password);

		// Change the fields of the user where applicable
		if (user.getUsername() != null) newUser.setUsername(user.getUsername());
		if (user.getFullName() != null) newUser.setFullName(user.getFullName());
		if (user.getPassword() != null) newUser.setPassword(user.getPassword());
		if (user.getWeight() >= 0f) newUser.setWeight(user.getWeight());

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

    @Override
    public List<User> searchUsers(String pattern) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchUsers'");
    }

    /**
	 * Checks if the fields are invalid and if so throws appropriate request.
	 *
	 * @param userId    the user id
     * @param password  user password
	 * @param fullname  user fullname
     * @param weight    user weight
	 * @return
	 */
	private void validateFields(String userId, String password, String fullname, float weight){

		if (userId == null || password == null || fullname == null) {
			Log.info("One or more fields are null.");
			throw new WebApplicationException( Status.BAD_REQUEST );
		}
        if (weight <= 0f) {
            Log.info("The weight is not valid.");
            throw new WebApplicationException( Status.BAD_REQUEST );
        }
        
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
