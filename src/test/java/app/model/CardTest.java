package app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void setCardtypetrue() {
    Card card= new Card();
    CardType cardType= CardType.Goblin;
    card.setCardtype(cardType);
    assertEquals(card.getCardtype(),CardType.Goblin);
    }
    @Test
    void setCardtypefalse() {
        Card card = new Card();
        CardType cardType = CardType.Goblin;
        card.setCardtype(cardType);
        assertNotEquals(card.getCardtype(), CardType.Elf);
    }
    @Test
    void testConstructorDamage(){
        Card testCard=new Card("lkgrjo9rjfoi4uorwir","WaterGoblin",40);
        int testdamage =testCard.getDamage();
        assertEquals(40,testdamage);
    }
    @Test
    void testConstructorId(){
        Card testCard=new Card("lkgrjo9rjfoi4uorwir","WaterGoblin",40);
        String testid=testCard.getId();
        assertEquals("lkgrjo9rjfoi4uorwir",testid);
    }
    @Test
    void testConstructorName() {
        Card testCard = new Card("lkgrjo9rjfoi4uorwir", "WaterGoblin", 40);
        String testname = testCard.getName();
        assertEquals("WaterGoblin", testname);
    }
    @Test
    void testConstructorDamageFalse(){
        Card testCard=new Card("lkgrjo9rjfoi4uorwir","WaterGoblin",40);
        int testdamage =testCard.getDamage();
        assertNotEquals(9,testdamage);
    }
    @Test
    void testConstructorIdFalse(){
        Card testCard=new Card("lkgrjo9rjfoi4uorwir","WaterGoblin",40);
        String testid=testCard.getId();
        assertNotEquals("lkgrjo9rjfoi",testid);
    }
    @Test
    void testConstructorNameFalse() {
        Card testCard = new Card("lkgrjo9rjfoi4uorwir", "WaterGoblin", 40);
        String testname = testCard.getName();
        assertNotEquals("Watelin", testname);
    }



}