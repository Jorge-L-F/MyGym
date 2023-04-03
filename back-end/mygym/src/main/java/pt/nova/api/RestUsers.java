package pt.nova.api;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import pt.nova.api.entities.User;
import pt.nova.data.UserDTO;

/**
 * API of the rest users.
 */
@Path(RestUsers.PATH)
public interface RestUsers {
    static final String PATH="/users";

    /**
	 * Creates a new user.
	 * 
	 * @param user UserDTO object.
	 * @return 200 the userId.
	 *         400 otherwise.
	 */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String createUser(UserDTO user);

    /**
	 * Obtains the information on the user identified by name.
	 * @param userId 	the userId of the user
	 * @param password 	password of the user
	 * @return 200 the user object, if the userId exists and password matches the existing
	 *         password
	 *         403 if the password is incorrect
	 *         404 if no user exists with the provided userId
	 */
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	User getUser(@PathParam("userId") String userId, @QueryParam("password") String password);
    
    /**
	 * Modifies the information of a user. Values of null in any field of the user will be 
	 * considered as if the the fields is not to be modified (the id cannot be modified).
	 * @param userId 	the userId of the user
	 * @param password 	password of the user
	 * @param user 		data object with new information
	 * @return 200 the updated user object, if the name exists and password matches the
	 *         existing password 
	 *         403 if the password is incorrect
	 *         404 if no user exists with the provided userId
	 *         400 otherwise.
	 */
	@PUT
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	User updateUser(@PathParam("userId") String userId, @QueryParam("password") String password, UserDTO user);
	
	/**
	 * Deletes the user identified by userId. The spreadsheets owned by the user should be eventually removed (asynchronous
	 * deletion is ok).
	 * @param nauserId the userId of the user
	 * @param password password of the user
	 * @return 200 the deleted user object, if the name exists and pwd matches the
	 *         existing password 
	 *         403 if the password is incorrect
	 *         404 if no user exists with the provided userId
	 */
	@DELETE
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	User deleteUser(@PathParam("userId") String userId, @QueryParam("password") String password);
	
}
