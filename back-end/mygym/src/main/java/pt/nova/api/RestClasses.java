package pt.nova.api;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import pt.nova.api.entities.GymClass;

/**
 * API of the rest classes.
 */
@Path(RestClasses.PATH)
public interface RestClasses {
    static final String PATH="/classes";

    /**
     * Creates a new class.
     * 
     * @param userId
     * @param password
     * @param cls
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    String createClass(@QueryParam("userId") String userId, @QueryParam("password") String password, GymClass cls);
}