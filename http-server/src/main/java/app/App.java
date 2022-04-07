package app;

import app.controller.UserController;
import app.controller.WeatherController;
import app.model.User;
import app.service.UserService;
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
        if (request.getPathname().equals("/users") && request.getMethod() == Method.POST) {
            return this.userController.addUser(request);}

        else if (request.getPathname().equals("/sessions") && request.getMethod() == Method.POST) {
            return this.userController.loginUser(request);
        }
        else if (request.getPathname().equals("/packages") && request.getMethod() == Method.POST) {
            return this.cardController.CreatePackageCards(request);
        }else if (request.getPathname().equals("/transactions/packages") && request.getMethod() == Method.POST) {
            return this.cardController.AcquirePackage(request);
        }
        else if (request.getPathname().equals("/cards") && request.getMethod() == Method.GET) {
            return this.cardController.printAcquiredCards(request);}

        else if (request.getPathname().equals("/deck") && request.getMethod() == Method.GET) {
            return this.cardController.printDeck(request);}
        else if (request.getPathname().equals("/deck") && request.getMethod() == Method.PUT) {
            return this.cardController.configureDeck(request);}
        else if(request.getPathname().equals("/deck?format=plain") && request.getMethod() == Method.GET) {
            return this.cardController.printDeckOtherFormat(request);}
        else if(request.getPathname().equals("/users/"+request.getUsernameInToken()) && request.getMethod() == Method.GET) {
            return this.userController.printUserProfile(request);}
        else if(request.getPathname().equals("/users/"+request.getUsernameInToken()) && request.getMethod() == Method.PUT) {
            return this.userController.editUserProfile(request);}

        else if(request.getPathname().equals("/stats") && request.getMethod() == Method.GET) {
            return this.userController.printstats(request);}

        else if(request.getPathname().equals("/score") && request.getMethod() == Method.GET) {
            return this.userController.printScoreboard();}
        else if(request.getPathname().equals("/battles") && request.getMethod() == Method.POST) {
            return this.battleController.startBattle(request);}

        return new Response(
                HttpStatus.BAD_REQUEST,
                ContentType.JSON,
                "[REQUEST FAILED]"
        );
    }
}
