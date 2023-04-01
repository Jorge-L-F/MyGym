package pt.nova.resources;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import jakarta.inject.Singleton;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
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
    
}
