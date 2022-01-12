package app;

import app.controller.WeatherController;
import app.service.WeatherService;
import http.ContentType;
import http.HttpStatus;
import http.Method;
import server.Request;
import server.Response;
import server.ServerApp;

public class App implements ServerApp {
    private final UserController userController;

    public App() {
        this.userController = new UserController(new UserService());
    }

    @Override
    public Response handleRequest(Request request) {
        if (request.getPathname().equals("/weather") && request.getMethod() == Method.GET) {
            return this.userController.getUser();
        } else if (request.getPathname().equals("/weather") && request.getMethod() == Method.POST) {
            return this.userController.addUser(request);
        }

        return new Response(
            HttpStatus.BAD_REQUEST,
            ContentType.JSON,
            "[]"
        );
    }
}
