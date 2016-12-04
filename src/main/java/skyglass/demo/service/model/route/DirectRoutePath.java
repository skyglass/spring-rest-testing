package skyglass.demo.service.model.route;

public class DirectRoutePath extends RoutePath {
	
	public static String NO_DIRECT_CONNECTION_MESSAGE = "No direct connection between these stations!";
	
	@Override
	public String getNoConnectionMessage() {
		return NO_DIRECT_CONNECTION_MESSAGE;
	}

}
