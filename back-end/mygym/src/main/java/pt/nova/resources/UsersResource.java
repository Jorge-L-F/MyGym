package pt.nova.resources;

import java.util.List;
import java.util.logging.Logger;

import pt.nova.api.RestUsers;
import pt.nova.api.entities.User;

/**
 * Implementation of the users rest resource.
 */
public class UsersResource implements RestUsers {
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
