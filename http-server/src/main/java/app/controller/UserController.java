package app.controller;

import app.model.EditUser;
import app.model.User;
import app.service.UserService;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.core.JsonProcessingException;
import http.ContentType;
import http.HttpStatus;
import server.Request;
import server.Response;

import java.util.UUID;

public class UserController extends Controller {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Response addUser(Request request) {
        User user = null;
        try {
            user = this.getObjectMapper().readValue(request.getBody(), User.class);

            user.setToken("Basic " + user.getUsername() + "-mtcgToken");
            user.setId(UUID.randomUUID().toString());
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

    public Response loginUser(Request request) {
        User user = null;
        try {
            user = this.getObjectMapper().readValue(request.getBody(), User.class);
            if (user != null) {
                boolean loginstate = this.userService.getUserdata(user.getUsername()).isLoggedin();
                if (!loginstate) {
                    //links das ungehashte password und rechts das gehashete pw aus der db
                    BCrypt.Result result = BCrypt.verifyer().verify(user.getPassword().toCharArray(), this.userService.getUserdata(user.getUsername()).getPassword());
                    if (result.verified) {

                        this.userService.login(user.getUsername());
                        return new Response(
                                HttpStatus.OK,
                                ContentType.JSON,
                                "{ \"message\" : \"Successfully logged in\" }"
                        );
                    }
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{ \"message\" : \"Failed to Log in\" }"
        );

    }

    ;

    public Response printUserProfile(Request request) {

        this.userService.printUserProfile(request.getUsernameInToken());
        return new Response(
                HttpStatus.CREATED,
                ContentType.JSON,
                """
                                                          \s
                        ---------------------------------
                         SUCCESS:
                         Successfully printed User Profile
                        ---------------------------------
                                                         \s
                        """
        );


    }

    public Response editUserProfile(Request request) {
        EditUser editUser;
        try {
            editUser = this.getObjectMapper().readValue(request.getBody(), EditUser.class);
            this.userService.editUserProfile(request.getUsernameInToken(), editUser);
            return new Response(
                    HttpStatus.CREATED,
                    ContentType.JSON,
                    """
                                                              \s
                            ---------------------------------
                             SUCCESS:
                             Successfully edited User Profile
                            ---------------------------------
                                                             \s
                            """

            );

        } catch (JsonProcessingException e) {
            e.printStackTrace();

        }

        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                """
                                    \s
                        -----------
                        FAILED:
                        SERVER ERROR
                        -----------
                                   \s
                        """
        );
    }
    public Response printstats(Request request){
        this.userService.printStats(request.getUsernameInToken());
        return new Response(
                HttpStatus.OK,
                ContentType.JSON,
                """
                                               \s
                        ------------------------
                         SUCCESS:
                         Successfully printed stats
                        ------------------------
                                                \s
                        """
        );

    }

    public Response printScoreboard(){
        this.userService.printScoreboard();
        return new Response(
                HttpStatus.OK,
                ContentType.JSON,
                """
                                                          \s
                        ---------------------------------
                         SUCCESS:
                         Successfully printed Scoreboard
                        ---------------------------------
                                                         \s
                        """
        );


    }

}


