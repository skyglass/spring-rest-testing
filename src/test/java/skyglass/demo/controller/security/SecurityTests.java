package skyglass.demo.controller.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import skyglass.demo.confg.BaseIntegrationTest;
import skyglass.demo.confg.TestConfig;
import skyglass.demo.controller.route.RouteControllerTest;
import skyglass.demo.data.model.route.Route;

public class SecurityTests extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate template;

	@Test
	public void resourceEndpointProtected() {
		ResponseEntity<String> response = template.getForEntity(TestConfig.SERVER_URL + "/resource", String.class);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}
	
	@Test
	public void resourceEndpointSuccess() {
		TestRestTemplate template = new TestRestTemplate("admin", "admin");
		ResponseEntity<String> response = template.getForEntity(TestConfig.SERVER_URL + "/resource", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void routeEndpointProtected() {
		TestRestTemplate template = new TestRestTemplate("admin", "admin2");
		ResponseEntity<String> response = template.getForEntity(RouteControllerTest.ROUTE_RESOURCE_URL, String.class);
		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
	}
	
    @Test
    public void routeEndpointPostProtected(){
		TestRestTemplate template = new TestRestTemplate("customer", "customer");
        Route route = new Route("test");
        ResponseEntity<Route> response = template.postForEntity(RouteControllerTest.ROUTE_RESOURCE_URL, route, Route.class);
		assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());   	
    }	
	
	@Test
	public void routeEndpointSuccess() {
		TestRestTemplate template = new TestRestTemplate("admin", "admin");
		ResponseEntity<String> response = template.getForEntity(RouteControllerTest.ROUTE_RESOURCE_URL, String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}		

}
