package pt.nova;

import java.net.InetAddress;
import java.net.URI;
import java.util.logging.Logger;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import pt.nova.resources.UsersResource;

/**
 * The class to start the server up.
 */
public class Server 
{
    private static Logger Log = Logger.getLogger(Server.class.getName());

    static {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s\n");
	}
	
	public static final int PORT = 8080;

    public static void main( String[] args )
    {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();

			ResourceConfig config = new ResourceConfig();
            config.register(new UsersResource());

            String serverURI = String.format("http://%s:%s/rest", ip, PORT);
			JdkHttpServerFactory.createHttpServer( URI.create(serverURI), config);

            Log.info(String.format("Server ready @ %s\n", serverURI));
        } catch( Exception e) {
			Log.severe(e.getMessage());
		}
    }
}
