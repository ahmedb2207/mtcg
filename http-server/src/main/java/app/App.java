package app;

import app.controller.UserController;
import app.controller.WeatherController;
import app.model.User;
import app.service.UserService;
import app.service.WeatherService;
import http.ContentType;
import http.HttpStatus;
import http.Method;
import server.Request;
import server.Response;
import server.ServerApp;

public class App implements ServerApp {
    private final WeatherController weatherController;
    private final UserController userController;


    public App() {
        this.weatherController = new WeatherController(new WeatherService());
        this.userController = new UserController(new UserService());
    }

    @Override
    public Response handleRequest(Request request) {
        if (request.getPathname().equals("/weather") && request.getMethod() == Method.GET) {
            return this.weatherController.getWeather();
        } else if (request.getPathname().equals("/weather") && request.getMethod() == Method.POST) {
            return this.weatherController.addWeather(request);

        }else if (request.getPathname().equals("/users") && request.getMethod() == Method.POST) {
            return this.userController.addUser(request);}

        else if (request.getPathname().equals("/sessions") && request.getMethod() == Method.POST) {
            return this.userController.loginUser(request);
        }
        else if (request.getPathname().equals("/packages") && request.getMethod() == Method.POST) {
            return this.userController.loginUser(request);
        }
        return new Response(
            HttpStatus.BAD_REQUEST,
            ContentType.JSON,
            "[]"
        );
    }
}
