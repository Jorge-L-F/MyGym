package pt.nova.api;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pt.nova.api.entities.User;

/**
 * API of the rest users.
 */
@Path(RestUsers.PATH)
public interface RestUsers {
    static final String PATH="/users";

    // TOGO
    // Endpoints should be defined below this point

    /**
	 * Creates a new user.
	 * 
	 * @param user User to be created.
	 * @return 200 the userId.
	 *         409 if the user id already exists. 
	 *         400 otherwise.
	 */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String createUser(User user);
    
}
