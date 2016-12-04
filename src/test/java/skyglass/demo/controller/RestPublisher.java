package skyglass.demo.controller;

public interface RestPublisher {

    <T> T doGet(Class<T> clazz, String resourceURL);

    <T> T doPost(String resourceURL, Object requestBody,  Class<T> clazz);




}
