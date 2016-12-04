package skyglass.demo.controller.route;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

import skyglass.demo.confg.BaseIntegrationTest;
import skyglass.demo.confg.TestConfig;
import skyglass.demo.controller.model.BooleanWrapper;
import skyglass.demo.controller.model.route.RoutePathWrapper;
import skyglass.demo.service.model.route.DirectRoutePath;
import skyglass.demo.service.model.route.RoutePath;

public class RoutePathControllerTest extends BaseIntegrationTest{

    public static final String ROUTE_RESOURCE_URL = TestConfig.SERVER_URL + "/rest/route";

    @Test
    public void testHasDirectRoute(){
		TestRestTemplate template = new TestRestTemplate("admin", "admin");
        BooleanWrapper result = template.getForEntity(ROUTE_RESOURCE_URL + "/directRoute/1/4", BooleanWrapper.class).getBody();
    	assertEquals(Boolean.TRUE, result.value);
       
        result = template.getForEntity(ROUTE_RESOURCE_URL + "/directRoute/2/4", BooleanWrapper.class).getBody();
    	assertEquals(Boolean.FALSE, result.value);
    }
    
    @Test
    public void testGetMinimalDirectRoute(){
		TestRestTemplate template = new TestRestTemplate("admin", "admin");
        RoutePathWrapper result = template.getForEntity(ROUTE_RESOURCE_URL + "/minimalDirectRoute/1/4", RoutePathWrapper.class).getBody();
    	assertEquals( 
    			RoutePath.MINIMAL_ROUTE_PREFIX 
    			+ "Route 2: (1 -> 4)" 
    			+ RoutePath.MINIMAL_ROUTE_SUFFIX,
    			result.path);        
    	assertEquals(1, result.routePath.getRoutes().size());
    	assertEquals(1, result.routePath.getRoutes().get(0).getLength());
    	assertEquals("2", result.routePath.getRoutes().get(0).getRoute().getName());
    	assertEquals(1, result.routePath.getRoutes().get(0).getStartIndex());
    	assertEquals(2, result.routePath.getRoutes().get(0).getEndIndex());
       
        result = template.getForEntity(ROUTE_RESOURCE_URL + "/minimalDirectRoute/2/4", RoutePathWrapper.class).getBody();
    	assertEquals(result.path, DirectRoutePath.NO_DIRECT_CONNECTION_MESSAGE);
    	assertEquals(0, result.routePath.getRoutes().size());
    }  
    
    @Test
    public void testGetMinimalRoute(){
		TestRestTemplate template = new TestRestTemplate("admin", "admin");
        RoutePathWrapper result = template.getForEntity(ROUTE_RESOURCE_URL + "/minimalRoute/1/4", RoutePathWrapper.class).getBody();
    	assertEquals( 
    			RoutePath.MINIMAL_ROUTE_PREFIX 
    			+ "Route 2: (1 -> 4)" 
    			+ RoutePath.MINIMAL_ROUTE_SUFFIX,
    			result.path);
       
        result = template.getForEntity(ROUTE_RESOURCE_URL + "/minimalRoute/2/4", RoutePathWrapper.class).getBody();
    	assertEquals(
    			RoutePath.MINIMAL_ROUTE_PREFIX 
    			+ "Route 1: (2 -> 1)" 
    			+ RoutePath.MINIMAL_ROUTE_DELIMITER
    			+ "Route 2: (1 -> 4)"
    			+ RoutePath.MINIMAL_ROUTE_SUFFIX,
    			result.path);  
    	
        result = template.getForEntity(ROUTE_RESOURCE_URL + "/minimalRoute/2/5", RoutePathWrapper.class).getBody();
    	assertEquals(RoutePath.NO_CONNECTION_MESSAGE, result.path);    
    	
        result = template.getForEntity(ROUTE_RESOURCE_URL + "/minimalRoute/1/6", RoutePathWrapper.class).getBody();
    	assertEquals(
    			RoutePath.MINIMAL_ROUTE_PREFIX 
    			+ "Route 2: (1 -> 4)" 
    			+ RoutePath.MINIMAL_ROUTE_DELIMITER
    			+ "Route 4: (4 -> 6)"
    			+ RoutePath.MINIMAL_ROUTE_SUFFIX,
    			result.path);    

    }     
    
    @Test
    public void testGetMinimalRoute2(){  	
		TestRestTemplate template = new TestRestTemplate("admin", "admin");
    	RoutePathWrapper result = template.getForEntity(ROUTE_RESOURCE_URL + "/minimalRoute/1/8", RoutePathWrapper.class).getBody();
    	assertEquals(
    			RoutePath.MINIMAL_ROUTE_PREFIX 
    			+ "Route 2: (1 -> 4)" 
    			+ RoutePath.MINIMAL_ROUTE_DELIMITER
    			+ "Route 4: (4 -> 6 -> 8)"
    			+ RoutePath.MINIMAL_ROUTE_SUFFIX,
    			result.path);     	
 
    }      

}

