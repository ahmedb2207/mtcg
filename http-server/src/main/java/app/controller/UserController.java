package app.controller;

import app.model.User;
import app.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import http.ContentType;
import http.HttpStatus;
import server.Request;
import server.Response;

public class UserController extends Controller {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Response addUser(Request request) {
        User user = null;
        try {
            user = this.getObjectMapper().readValue(request.getBody(), User.class);
            user.setToken("Basic " + user.getUsername()+ "-mtcgToken");
            this.userService.addUser(user);

            return new Response(
                    HttpStatus.CREATED,
                    ContentType.JSON,
                    "{ message: \"Success\" }"
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{ \"message\" : \"Internal Server Error\" }"
        );

    }
    public Response loginUser(Request request){
        User user= null;
        try {
            user = this.getObjectMapper().readValue(request.getBody(), User.class);
            if(user != null){
            boolean loginstate = this.userService.getUserdb(user.getUsername()).isLoggedin();
            if (!loginstate) {
                if (this.userService.getUserdb(user.getUsername()).getPassword().equals(user.getPassword())){
                    this.userService.login(user.getUsername());
                    return new Response(
                            HttpStatus.OK,
                            ContentType.JSON,
                            "{ \"message\" : \"Successfully logged in\" }"
                    );
                }
            } }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{ \"message\" : \"Failed to Log in\" }"
        );

    };
}


