package skyglass.demo.controller;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestPublisherImpl  implements RestPublisher{

    public static final String REQUEST_BODY_TYPE = "application/json";
    private String serverUrl;

    private RequestSpecification requestSpecification;

    public RestPublisherImpl(String serverUrl, String login, String password) {
        this.serverUrl = serverUrl;
        requestSpecification = given().auth().basic(login, password).contentType(ContentType.JSON);

    }

    public <T> T doGet(Class<T> clazz, String resourceURL) {
        Response response = requestSpecification.get(serverUrl + resourceURL);
        return response.as(clazz);
    }

    public <T> T doPost(String resourceURL, Object requestBody, Class<T> clazz) {
        return requestSpecification.body(requestBody).post(resourceURL).as(clazz);
    }

}
