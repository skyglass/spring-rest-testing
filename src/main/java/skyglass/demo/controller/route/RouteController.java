package skyglass.demo.controller.route;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import skyglass.demo.controller.model.BooleanWrapper;
import skyglass.demo.controller.model.route.RoutePathWrapper;
import skyglass.demo.controller.utils.ControllerUtils;
import skyglass.demo.data.model.route.Route;
import skyglass.demo.data.model.route.Station;
import skyglass.demo.service.error.ServiceException;
import skyglass.demo.service.route.RouteService;
import skyglass.demo.service.route.StationService;

@RestController
@RequestMapping("/rest/route")
public class RouteController {

    @Autowired
    protected RouteService routeService;
    
    @Autowired
    protected StationService stationService;
    
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Iterable<Route> getAllRoutes()
    throws Exception {
        Iterable<Route> result = routeService.findAll();
        return result;
    }
    
    @RequestMapping(method = RequestMethod.GET, path  = "/{routeId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Route getRoute(@PathVariable Long routeId)
    throws Exception {
        Route result = routeService.findOne(routeId);
        return result;
    }   
    
    
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Route> saveRoute(@RequestBody Route route, HttpServletResponse response)
    throws Exception {
    	try {
            return new ResponseEntity<Route>(routeService.save(route), HttpStatus.OK);    		
    	} catch (ServiceException se) {
    		ControllerUtils.sendError(response, se.getError());
    		return null;
    	}

    }  
    
    @RequestMapping(method = RequestMethod.GET, path  = "/{routeId}/stations")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public Iterable<Station> getRouteStations(@PathVariable Long routeId)
    throws Exception {
    	Route route = routeService.findOne(routeId);
        return route.getStations();
    }    
    
    @RequestMapping(method = RequestMethod.POST, path  = "/{routeId}/setStations")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Route> setUserRoles(@PathVariable Long routeId, @RequestBody StationsWrapper stationsWrapper)
    throws Exception {
    	Route route = routeService.setStations(routeId, stationsWrapper.stationIds);
        return new ResponseEntity<Route>(route, HttpStatus.OK);
    }       
    
    @RequestMapping(method = RequestMethod.DELETE, path  = "/{routeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> deleteRoute(@PathVariable Long routeId)
    throws Exception {
    	routeService.delete(routeId);
        return new ResponseEntity<Long>(routeId, HttpStatus.OK);
    }
    
    
    @RequestMapping(method = RequestMethod.GET, path  = "/directRoute/{startId}/{endId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public BooleanWrapper hasDirectRoute(@PathVariable Long startId, @PathVariable Long endId)
    throws Exception {
    	Station start = stationService.findOne(startId);
    	Station end = stationService.findOne(endId);
        return new BooleanWrapper(routeService.hasDirectRoute(start, end));
    }
    
    @RequestMapping(method = RequestMethod.GET, path  = "/minimalDirectRoute/{startId}/{endId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public RoutePathWrapper getMinimalDirectRoute(@PathVariable Long startId, @PathVariable Long endId)
    throws Exception {
    	Station start = stationService.findOne(startId);
    	Station end = stationService.findOne(endId);
        return new RoutePathWrapper(routeService.getMinimalDirectRoute(start, end));
    }    
    
    @RequestMapping(method = RequestMethod.GET, path  = "/minimalRoute/{startId}/{endId}")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    public RoutePathWrapper getMinimalRoute(@PathVariable Long startId, @PathVariable Long endId)
    throws Exception {
    	Station start = stationService.findOne(startId);
    	Station end = stationService.findOne(endId);
        return new RoutePathWrapper(routeService.getMinimalRoute(start, end));
    }      
    
    private static class StationsWrapper {
		public Long[] stationIds;
    }
    


}

 
