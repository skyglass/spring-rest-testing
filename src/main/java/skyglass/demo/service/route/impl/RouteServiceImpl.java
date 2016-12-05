package skyglass.demo.service.route.impl;

import static org.springframework.data.jpa.domain.Specifications.where;
import static skyglass.demo.data.route.RouteSpecifications.isMember;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import skyglass.demo.data.model.route.Route;
import skyglass.demo.data.model.route.Station;
import skyglass.demo.data.route.RouteData;
import skyglass.demo.data.route.StationData;
import skyglass.demo.service.GenericServiceImpl;
import skyglass.demo.service.error.ServiceException;
import skyglass.demo.service.model.route.DirectRoutePath;
import skyglass.demo.service.model.route.RoutePath;
import skyglass.demo.service.model.route.SubRoute;
import skyglass.demo.service.route.RouteService;

@Repository
@Transactional(readOnly = true)
public class RouteServiceImpl extends GenericServiceImpl<Route, Long, RouteData> implements RouteService {
	
	@Autowired
	protected StationData stationData;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public Route save(Route route) throws ServiceException {
    	if (route.getId() != null) {
    		Route oldRoute = findOne(route.getId());
    		if (!oldRoute.getName().equals(route.getName())) {
    			checkRouteExists(route);
    		}
    	} else {
			checkRouteExists(route);    		
    	}
    	return super.save(route);
	}
	
	private void checkRouteExists(Route route) throws ServiceException {
		Route test = repository.findByName(route.getName());
		if (test != null) {
	        throw new ServiceException("saveRouteError",
	        		"Route with name '" + route.getName() + "' already exists");
		}		
	}

	@Override
	@Transactional
	public Route setStations(Long routeId, Long[] stationIds) {
    	Route route = findOne(routeId);
		Iterable<Station> stations = stationData.findAll(Arrays.asList(stationIds));
		List<Station> result = new ArrayList<Station>();
		for (Station station: stations) {
			result.add(station);
		}
		route.setStations(result);
		return repository.save(route);
	}
	
	@Override
	public boolean hasDirectRoute(Station station1, Station station2) throws ServiceException {
		List<Route> routes = repository.findAll(where(isMember(station1)).and(isMember(station2)));
		if (routes.size() == 0) {
			return false;
		}
		return true;
	}	

	@Override
	public RoutePath getMinimalDirectRoute(Station station1, Station station2) throws ServiceException {
		RoutePath result = new DirectRoutePath();
		List<Route> routes = repository.findAll(where(isMember(station1)).and(isMember(station2)));
		if (routes.size() == 0) {
			return result;
		}
		Route minRoute = routes.get(0);
		for (int i = 1; i < routes.size(); i++) {
			if (distance(routes.get(i), station1, station2) < distance(routes.get(i), station1, station2)) {
				minRoute = routes.get(i);
			}
		}
		result.addRoute(new SubRoute(minRoute, station1, station2));
		return result;
	}
	
	private int distance(Route route, Station station1, Station station2) throws ServiceException {
		int count = 0;
		boolean started = false;
		for (Station station: route.getStations()) {
			if (station.getId().equals(station1.getId()) || station.getId().equals(station2.getId())) {
				if (started) {
					return count;
				}
				started = true;				
			} else {
				if (started) {
					count++;
				}
			}
		}
		throw new ServiceException("routeError", "Distance Calculation Error!");
	}

	@Override
	public RoutePath getMinimalRoute(Station station1, Station station2) throws ServiceException {
		RoutePath result = getMinimalRoute(new RoutePath(), null, station1, station2);
		if (result == null) {
			return new RoutePath();
		}
		return result;
	}
	
	//recursive method
	private RoutePath getMinimalRoute(RoutePath currentPath, RoutePath minPath, Station station1, Station station2) throws ServiceException {
		RoutePath directRoute = getMinimalDirectRoute(station1, station2);
		if (directRoute.getLength() > 0) {
			currentPath.addRoutePath(directRoute);
			return currentPath;			
		}
		
		List<SubRoute> subRoutes = getSubRoutes(station1);
		for (SubRoute subRoute: subRoutes) {
			if (currentPath.alreadyBeenThere(subRoute.getRoute().getId())) {
				//already been there
				continue;
			}
			int currentIndex = subRoute.getStartIndex();
			do {
				currentIndex = subRoute.getNextIndex(currentIndex);
				SubRoute nextSubRoute = new SubRoute(
						subRoute.getRoute(), subRoute.getStartIndex(), currentIndex);
				RoutePath nextRoutePath = currentPath.copy();
				nextRoutePath.addRoute(nextSubRoute);
				minPath = findNextMinPath(nextRoutePath, minPath, nextSubRoute, station2, currentIndex);

			} while (currentIndex != subRoute.getEndIndex());
		}
		return minPath;
	}	
	
	private RoutePath findNextMinPath(RoutePath currentPath, RoutePath currentMinPath, 
			SubRoute currentSubRoute, Station station2, int currentIndex) throws ServiceException {
		if (currentMinPath != null && currentPath.getLength() >= currentMinPath.getLength()) {
			//stop search in this direction, because there is already a better way
			return currentMinPath;
		}
		
		RoutePath path = getMinimalRoute(currentPath, 
				currentMinPath, currentSubRoute.getStation(currentIndex), station2);
		if (path == null) {
			//dead end
			return currentMinPath;
		}
		if (currentMinPath == null || path.getLength() < currentMinPath.getLength()) {
			return path;
		}	
		return currentMinPath;
	}

	@Override
	public List<SubRoute> getSubRoutes(Station station) throws ServiceException {
		List<Route> routes = repository.findAll(where(isMember(station)));
		if (routes.size() == 0) {
			return new ArrayList<SubRoute>();
		}
		List<SubRoute> result = new ArrayList<SubRoute>();
		for (Route route: routes) {
			int startIndex = getStartIndex(route, station);
			if (startIndex < route.getStations().size() - 1) {
				result.add(new SubRoute(route, startIndex, route.getStations().size() - 1));				
			}
			if (startIndex > 0) {
				result.add(new SubRoute(route, startIndex, 0));				
			}
		}
		return result;
	}
	
	private int getStartIndex(Route route, Station startStation) throws ServiceException {
		for (int i = 0; i < route.getStations().size(); i++) {
			if (route.getStations().get(i).getId().equals(startStation.getId())) {
				return i;
			}
		}
		throw new ServiceException("routeServiceException", "getStartIndex() error");
		
	}
	
	
	
	

}
