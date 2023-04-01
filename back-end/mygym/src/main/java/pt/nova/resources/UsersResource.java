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
        
        // Check if userId exists, if not, return HTTP CONFLICT (409)
		if(users.containsKey(user.getUserId())) {
			Log.info("User already exists.");
			throw new WebApplicationException( Status.CONFLICT );
		}

        //Add the user to the map of users
		users.put(user.getUserId(), user);
		
		return user.getUserId();
    }

    @Override
    public User getUser(String userId, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    @Override
    public User updateUser(String userId, String password, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public User deleteUser(String userId, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
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
    
}
