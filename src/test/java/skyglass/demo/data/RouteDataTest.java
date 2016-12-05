package skyglass.demo.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import skyglass.demo.confg.BaseIntegrationTest;
import skyglass.demo.data.model.route.Route;
import skyglass.demo.data.model.route.Station;
import skyglass.demo.data.route.RouteData;
import skyglass.demo.service.error.ServiceException;

public class RouteDataTest extends BaseIntegrationTest{

    @Autowired
    private RouteData routeData;

    @Test
    public void testFindAll() throws Exception {
        List<Route> routes = routeData.findAll();
        assertEquals(10, routes.size());
    }

    @Test
    public void testFindByName(){
        Route route = routeData.findByName("1");
        assertEquals("1", route.getName());
        assertEquals(3, route.getStations().size());

        route = routeData.findByName("2");
        assertEquals("2", route.getName());
        assertEquals(3, route.getStations().size());
    }
    
    @Test
    public void createRoute() throws ServiceException {
        Route route = new Route("test");
        route.addStation(new Station("test1"))
        	.addStation(new Station("test2"));
        Route createdRoute = routeData.save(route);
    	assertNotNull(createdRoute);
        assertEquals("test", createdRoute.getName());
        assertEquals(2, createdRoute.getStations().size());
        assertEquals("test1", createdRoute.getStations().get(0).getName());
        assertEquals("test2", createdRoute.getStations().get(1).getName());
    }    
}