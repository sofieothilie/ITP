package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class MovieTest {
   
    Movie m1 = new Movie("Cinderella", "fantasy");
    User testUser = new User("user1", "password");
    User testUser2 = new User("user2", "password");

    @DisplayName("Testing the constructor")
    @Test
    public void testConstructor(){ // tester konstruktøren
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Movie("", "fantasy");
        }, "Title cannot be empty");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Movie("Snowwhite", "fairytale"); 
        }, "Not a valid genre");

        Movie m2 = new Movie("Star Wars", "action");
        assertEquals("Star Wars", m2.getTitle());
        assertEquals("action", m2.getGenre());
    }

    @DisplayName("Testing  getTitle and getGenre")
    @Test
    public void testGetters(){ // tester get-metodene
        assertEquals("Cinderella", m1.getTitle());
        assertEquals("fantasy", m1.getGenre());
    }

    @DisplayName("Testing getAllRatings")
    @Test
    public void testGetAllRatings(){ // tester getAllRatings
        m1.addRating(3);
        m1.addRating(4);
        m1.addRating(5);
        assertEquals(3, m1.getAllRatings().size());
    }

    @DisplayName("Testing addRating")
    @Test
    public void testAddRating(){ // tester addRating-metoden
        m1.addRating(3);
        m1.addRating(4);
        m1.addRating(5);
        assertEquals(3, m1.getAllRatings().size());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            m1.addRating(6);
        }, "Not a valid rating");
    }

    @DisplayName("Testing getAverageRating")
    @Test
    public void testGetAverageRating(){ // tester getAverageRating-metoden
        m1.addRating(3);
        m1.addRating(4);
        m1.addRating(5);
        assertEquals(4.0, m1.averageRating());
    }

    @DisplayName("Testing toString")
    @Test
    public void testToString(){ // tester toString-metoden
        m1.addRating(3);
        m1.addRating(4);
        m1.addRating(5);
        assertEquals("Cinderella; fantasy; 4,00", m1.toString());
    }



    
}
