package skyglass.demo.service.model.route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoutePath {
	
	public static String NO_CONNECTION_MESSAGE = "There is no connection between these stations!";
	
	public static final String MINIMAL_ROUTE_PREFIX = "Minimal Route: [";
	
	public static final String MINIMAL_ROUTE_DELIMITER = "] --> [";
	
	public static final String MINIMAL_ROUTE_SUFFIX = "]";
	
	private List<SubRoute> routes = new ArrayList<SubRoute>();
	
	private Set<Long> routeIds = new HashSet<Long>();
	
	public RoutePath() {
		
	}
	
	public List<SubRoute> getRoutes() {
		return routes;
	}
	
	public boolean alreadyBeenThere(Long routeId) {
		//if it's true then it means that we came back to the same place and
		// and need to skip this path
		return routeIds.contains(routeId);
	}
	
	public void addRoute(SubRoute route) {
		routes.add(route);
		routeIds.add(route.getRoute().getId());
	}
	
	public void addRoutePath(RoutePath routePath) {
		for (SubRoute subRoute: routePath.routes) {
			addRoute(subRoute);
		}
	}
	
	public int getLength() {
		int count = 0;
		for (SubRoute subRoute: routes) {
			count += subRoute.getLength();
		}
		return count;
	}
	
	public String getPath() {
		StringBuilder sb = new StringBuilder();
		if (routes.size() == 0) {
			sb.append(getNoConnectionMessage());
			return sb.toString();
		}
		sb.append(MINIMAL_ROUTE_PREFIX);
		for (int i = 0; i < routes.size(); i++) {
			sb.append(routes.get(i).getPath());
			if (i < routes.size() - 1) {
				sb.append(MINIMAL_ROUTE_DELIMITER);
			}			
		}
		sb.append(MINIMAL_ROUTE_SUFFIX);
		return sb.toString();
	}
	
	protected String getNoConnectionMessage() {
		return NO_CONNECTION_MESSAGE;
	}
	
	public RoutePath copy() {
		RoutePath result = new RoutePath();
		for (SubRoute route: this.routes) {
			result.addRoute(route);
		}
		result.routeIds = new HashSet<Long>();
		for (Long routeId: this.routeIds) {
			result.routeIds.add(routeId);			
		}
		return result;
	}

}
