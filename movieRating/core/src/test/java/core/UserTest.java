package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap; 


public class UserTest {
    //TODO: teste equals
    //TODO: oppdatere i henhold til JaCoCo og endringene i User
    //TODO: test message
    
    //Setup
    User testUser = new User("username", "password"); 
    Movie m1 = new Movie("Cinderella", "fantasy");
    Movie m2 = new Movie("Snowwhite", "fantasy");
    
    @DisplayName("Testing validation of username and password")
    @Test
    public void testValidUsernameAndPassword(){  
        new User("Username1234", "Password1234");
        
    }

    @DisplayName("Testing invalid usernames and passwords")
    @Test
    public void testInvalidUsernameAndPassword(){ 
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User("username-!", "password");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User("username", "pass#");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User("", "password");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new User("username-!", "");
        });
    }

    @DisplayName("Testing getUsername and getPassword")
    @Test
    public void testGetters(){ 
        assertEquals("username", testUser.getUsername());
        assertEquals("password", testUser.getPassword());
    }

    @DisplayName("Testing getRatedMovies")
    @Test
    public void testGetRatedMovies(){ 
        testUser.rateMovie(m1, 4);
        testUser.rateMovie(m2, 3);
        HashMap<Movie, Integer> compare = new HashMap<>();
        compare.put(m1, 4);
        compare.put(m2, 3);

        assertEquals(compare, testUser.getRatedMovies());
    }

    @DisplayName("Testing rateMovie")
    @Test
    public void rateMovie(){ 
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testUser.rateMovie(m2, 6);; 
        }, "Rating must be an integer from 1 to 5");

        testUser.rateMovie(m1, 2);
        testUser.rateMovie(m2, 4);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testUser.rateMovie(m1, 4);; 
        }, "The Movie is already rated");
        
        HashMap<Movie, Integer> compare = new HashMap<>();
        compare.put(m1, 2);
        compare.put(m2, 4);
        assertEquals(compare, testUser.getRatedMovies());
    }
    
}
