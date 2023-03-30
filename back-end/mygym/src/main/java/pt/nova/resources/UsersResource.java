package pt.nova.resources;

import java.util.logging.Logger;

import pt.nova.api.RestUsers;
import pt.nova.api.entities.User;

/**
 * Implementation of the users rest resource.
 */
public class UsersResource implements RestUsers {
    private static Logger Log = Logger.getLogger(UsersResource.class.getName());

    public UsersResource() {}

    // TODO
    // Endpoint overrides should go below this point.
    @Override
    public String createUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }
    
}
