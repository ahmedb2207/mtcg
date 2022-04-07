package app.service;

import app.model.Card;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardServiceTest {
     CardService cardService;

    @BeforeEach
     void init() {
        cardService = new CardService();
    }
    @Test
    void printAcquiredCards() {
        assertTrue(cardService.printAcquiredCards("altenhof"));
    }
    @Test
    void printAcquiredCardsFalse() {
        assertFalse(cardService.printAcquiredCards("ahmed"));
    }




  /*  @Test
    void getConfiguredCards() {
        List<Card> deck=cardService.getConfiguredCards("altenhof");
        assertEquals("d6e9c720-9b5a-40c7-a6b2-bc34752e3463", );
    }*/


}