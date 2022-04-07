package app.service;

import app.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService userService;
    User user;

    @BeforeEach
    void init() {
        userService = new UserService();
        user= new User("altenhof","75e6b97d-80aa-4e26-8dc7-b0dcaced80bd", "$2a$12$g7vBBZ70qrCPT7i3n.Cv5elmDFfwKjkOfTomlRFvz4sw0EhEwyZ36", 0, "Basic altenhof-mtcgToken", true, 0, 0, 0, 100, "Altenhofer", "me codin...", ":-D");
//75e6b97d-80aa-4e26-8dc7-b0dcaced80bd,altenhof,$2a$12$g7vBBZ70qrCPT7i3n.Cv5elmDFfwKjkOfTomlRFvz4sw0EhEwyZ36,Basic altenhof-mtcgToken,0,true,0,0,0,100,Altenhofer,me codin...,:-D
    }
    @Test
    void addUser() {


    }

    @Test
    void loggedInState() {
        assertTrue(userService.LoggedInState("altenhof","Basic altenhof-mtcgToken"));
    }
    @Test
    void loggedInStateWrongUsername() {
        assertFalse(userService.LoggedInState("alten","Basic altenhof-mtcgToken"));
    }
    @Test
    void loggedInStateWrongToken() {
        assertFalse(userService.LoggedInState("altenhof","Basic alten-mtcgToken"));
    }


    @Test
    void getUserdata() {
        assertNotNull( userService.getUserdata("altenhof"));
    }
    @Test
    void getUserdataFalse() {
        assertNull( userService.getUserdata("alten"));
    }

    @Test
    void getUserdataBio() {
        assertEquals(user.getBio(), userService.getUserdata("altenhof").getBio());
    }
    @Test
    void getUserdataCoins() {
        assertEquals(user.getCoins(), userService.getUserdata("altenhof").getCoins());
    }
    @Test
    void getUserdataName() {
        assertEquals(user.getName(), userService.getUserdata("altenhof").getName());
    }



}