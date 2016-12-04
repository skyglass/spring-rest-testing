package skyglass.demo.service.route;

import java.util.List;

import skyglass.demo.data.model.route.Route;
import skyglass.demo.data.model.route.Station;
import skyglass.demo.data.route.RouteData;
import skyglass.demo.service.GenericService;
import skyglass.demo.service.error.ServiceException;
import skyglass.demo.service.model.route.RoutePath;
import skyglass.demo.service.model.route.SubRoute;

public interface RouteService extends GenericService<Route, Long, RouteData> {
	
	Route setStations(Long routeId, Long[] stationIds);
	
	boolean hasDirectRoute(Station station1, Station station2) throws ServiceException;
	
	RoutePath getMinimalDirectRoute(Station station1, Station station2) throws ServiceException;
	
	RoutePath getMinimalRoute(Station station1, Station station2) throws ServiceException;
	
	public List<SubRoute> getSubRoutes(Station station1) throws ServiceException;

}
