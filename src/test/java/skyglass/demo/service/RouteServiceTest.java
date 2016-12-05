package skyglass.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import skyglass.demo.confg.BaseIntegrationTest;
import skyglass.demo.data.model.route.Route;
import skyglass.demo.data.model.route.Station;
import skyglass.demo.service.error.ServiceException;
import skyglass.demo.service.route.RouteService;

public class RouteServiceTest extends BaseIntegrationTest {
    
    @Autowired
    private RouteService routeService;  

    @Test
    public void testGetAllRoutes(){
    	Iterable<Route> routeList = routeService.findAll();
    	assertEquals(10, ((Collection<Route>)routeList).size());
    }

    @Test
    public void testGetRoute(){
        Route route = routeService.findOne(1L);
    	assertNotNull(route);
        assertEquals("1", route.getName());
        assertEquals(1l, route.getId().longValue());
        
        route = routeService.findOne(2L);
    	assertNotNull(route);
        assertEquals("2", route.getName());
        assertEquals(2l, route.getId().longValue());
    }

    @Test
    public void createRoute() throws ServiceException {
        Route route = new Route("test");
        route.addStation(new Station("test1"))
        	.addStation(new Station("test2"));
        Route createdRoute = routeService.save(route);
    	assertNotNull(createdRoute);
        assertEquals("test", createdRoute.getName());
        assertEquals(2, createdRoute.getStations().size());
        assertEquals("test1", createdRoute.getStations().get(0).getName());
        assertEquals("test2", createdRoute.getStations().get(1).getName());
    }
}

