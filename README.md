* Demo project for finding minimal route between two stations, based on Spring Boot, Spring REST, Spring Security, Spring Data JPA and REST Assured testing framework

#Project Description:

1. Main Service API: src/main/java/skyglass.demo.service.route.impl.RouteServiceImpl
2. Main REST API: src/main/java/skyglass.demo.controller.route.RouteController
3. Main REST Testing API: src/test/java/skyglass.demo.controller.route.RoutePathControllerTest
4. Test SQL Data: src/test/resource/data.sql
5. Test Config: src/test/java/skyglass.demo.config.TestConfig
6. Base Integration Test: src/test/java/skyglass.demo.config.BaseIntegrationTest

#REST Service API:

1. RouteController.getAllRoutes - returns all routes
2. RouteController.getRoute - returns route by id
3. RouteController.saveroute - saves new or updated route
4. RouteController.getRouteStations - returns all ordered stations of the given route
5. RouteController.setRouteStations - sets new ordered stations of the given route
6. RouteController.deleteRoute - deletes route by id
7. RouteController.hasDirectRoute - returns true if there exists direct route between two given stations, and false otherwiise
8. RouteController.getMinimalDirectRoute - returns minimal direct route between two given stations in the following format: "Route {name}: {startStation} -> {station2} -> ... -> {endStation}". If there are no direct routes, "No Direct Connections" message is returned.
9. RouteController.getMinimalRoute - returns minimal route between two given stations, possibly indirect ones, in the following format: "Minimal Route: [Route {start}: {station1} -> ...] --> [Route{next}: {station1} -> ...] --> ... [Route{end}: {station1} -> ...}]". If there are no routes between given stations, "No Connections" message is returned.

All these REST services are tested in RouteControllerTest and RoutePathControllerTest classes using REST-Assure Testing Framework and Spring REST test configuration. Test Routes, Stations and Route Graphs are created with src/test/resource/data.sql

REST security is tested in SecurityTests class.
Test users and their permissions are created in src/test/resource/data.sql

