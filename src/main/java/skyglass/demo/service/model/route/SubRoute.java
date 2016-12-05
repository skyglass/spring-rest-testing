package skyglass.demo.service.model.route;

import skyglass.demo.data.model.route.Route;
import skyglass.demo.data.model.route.Station;
import skyglass.demo.service.error.ServiceException;

public class SubRoute {
	
	private Route route;
	
	private int startIndex;
	
	private int endIndex;
	
	public SubRoute() {
		
	}
	
	public SubRoute(Route route, int startIndex, int endIndex) throws ServiceException {
		if (startIndex == endIndex) {
			throw new ServiceException("routeServiceException", "new SubRoute(..) exception");
		}
		this.route = route;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	public SubRoute(Route route, Station startStation, Station endStation) throws ServiceException {
		this.route = route;
		for (int i = 0; i < route.getStations().size(); i++) {
			if (route.getStations().get(i).getId().equals(startStation.getId())) {
				this.startIndex = i;
			}
			if (route.getStations().get(i).getId().equals(endStation.getId())) {
				this.endIndex = i;
			}
		}
		if (startIndex == endIndex) {
			throw new ServiceException("routeServiceException", "new SubRoute(..) exception");
		}
	}	
	
	public Station getStation(int index) {
		return route.getStations().get(index);
	}
	
	public int getNextIndex(int currentIndex) {
		if (startIndex < endIndex) {
			return currentIndex + 1;
		} 
		return currentIndex - 1;
	}	
	
	public int getLength() {
		return Math.abs(endIndex - startIndex);
	}
	
	public String getPath() {
		StringBuilder sb = new StringBuilder();
		sb.append("Route " + route.getName() + ": (");
		if (startIndex < endIndex) {
			for (int i = startIndex; i <= endIndex; i++) {
				sb.append(route.getStations().get(i).getName());
				if (i < endIndex) {
					sb.append(" -> ");
				}
			}			
		} else {
			for (int i = startIndex; i >= endIndex; i--) {
				sb.append(route.getStations().get(i).getName());
				if (i > endIndex) {
					sb.append(" -> ");
				}
			}			
		}
		sb.append(")");

		return sb.toString();
	}
	
	public Route getRoute() {
		return route;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}	

}
