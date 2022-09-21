package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class MovieTest {
   
    Movie m1 = new Movie("Cinderella", "fantasy");
    User testUser = new User("user1", "password");
    User testUser2 = new User("user2", "password");
    


    @Test
    public void testCunstructor(){
        testUser.rateMovie(m1, 2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Movie("Cinderella", "fantasy");
        }, "Movie already in register");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Movie("Snowwhite", "fairytale"); 
        }, "Not a valid genre");
    }

    @Test
    public void testGetters(){
        testUser.rateMovie(m1, 3);
        testUser2.rateMovie(m1, 3);
        assertEquals("Cinderella", m1.getTitle());
        assertEquals("fantasy", m1.getGenre());
        assertEquals(3, m1.getRating()); 
    }
}
