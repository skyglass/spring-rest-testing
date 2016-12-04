package skyglass.demo.controller.model.route;

import skyglass.demo.service.model.route.RoutePath;

public class RoutePathWrapper {
	public RoutePath routePath;
	public String path;
	
	public RoutePathWrapper() {
		
	}
	
	public RoutePathWrapper(RoutePath routePath) {
		this.routePath = routePath;
		this.path = routePath.getPath();
	}
}