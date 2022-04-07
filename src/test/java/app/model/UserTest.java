package app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;
    @BeforeEach
    void init(){
        user= new User("ahmed","7843bf3923r2f", "ahmedatmet", 20, "ahmed-mtcgToken", true, 6, 2, 1, 100, "master99", "hallo ich bins", ":-D");
    }
    @Test
    void getId() {
        assertEquals("7843bf3923r2f", user.getId());
    }

    @Test
    void getUsernameFalse() {
        assertNotEquals("ahmedatmet", user.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("ahmedatmet", user.getPassword());
    }

    @Test
    void getCoins() {
        assertEquals(20, user.getCoins());
    }

    @Test
    void isLoggedinFalse() {
        assertNotEquals(false, user.isLoggedin());
    }

    @Test
    void getWinsFalse() {
        assertNotEquals(500, user.getWins());
    }

    @Test
    void getBioFalse() {
        assertNotEquals(":-D", user.getBio());
    }

    @Test
    void getImage() {
        assertEquals(":-D", user.getImage());;
    }

    @Test
    void getName() {
        assertEquals("master99", user.getName());
    }
}