package skyglass.demo.controller.route;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import skyglass.demo.confg.BaseIntegrationTest;
import skyglass.demo.confg.TestConfig;
import skyglass.demo.data.model.route.Route;

public class RouteControllerTest extends BaseIntegrationTest{

    public static final String ROUTE_RESOURCE_URL = TestConfig.SERVER_URL + "/rest/route";

    @Test
    public void testGetAllRoutes(){
		TestRestTemplate template = new TestRestTemplate("admin", "admin");
    	List<Route> routeList = Arrays.asList(template.getForEntity(ROUTE_RESOURCE_URL, Route[].class).getBody());
    	assertEquals(5, (routeList).size());
    }

    @Test
    public void testGetRoute(){
		TestRestTemplate template = new TestRestTemplate("admin", "admin");
        Route route = template.getForEntity(ROUTE_RESOURCE_URL + "/1", Route.class).getBody();
    	assertNotNull(route);
    	assertEquals(3, route.getStations().size());
    	assertEquals("1", route.getStations().get(0).getName());
    	assertEquals("2", route.getStations().get(1).getName());
    	assertEquals("3", route.getStations().get(2).getName());
        assertEquals("1", route.getName());
        assertEquals(1l, route.getId().longValue());
        
        route = template.getForEntity(ROUTE_RESOURCE_URL + "/2", Route.class).getBody();
    	assertNotNull(route);
        assertEquals("2", route.getName());
        assertEquals(2l, route.getId().longValue());
    }
}
