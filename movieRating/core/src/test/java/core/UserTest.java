package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap; 


public class UserTest {
    
    //Oppsett som kjøres før hver test
    User testUser = new User("username", "password"); 
    Movie m1 = new Movie("Cinderella", "fantasy");
    Movie m2 = new Movie("Snowwhite", "fantasy");
    
    @Test
    public void testValidUsernameAndPassword(){ //test for å sjekke gyldige brukernavn og passord 
        new User("Username1234", "Password1234");
        
    }
    @Test
    public void testInvalidUsernameAndPassword(){ //test for å sjekke ugyldige og tomme brukernavn og passord
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User testUser2 = new User("username-!", "password");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User testUser3 = new User("username", "pass#");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User testUser4 = new User("", "password");
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            User testUser5 = new User("username-!", "");
        });
    }

    @Test
    public void testGetters(){ //test for å sjekke getUsername og getPassword
        assertEquals("username", testUser.getUsername());
        assertEquals("password", testUser.getPassword());
    }

    @Test
    public void testGetRatedMovies(){ //test for å sjekke getRatedMovies
        testUser.rateMovie(m1, 4);
        testUser.rateMovie(m2, 3);
        HashMap<Movie, Integer> compare = new HashMap<>();
        compare.put(m1, 4);
        compare.put(m2, 3);

        assertEquals(compare, testUser.getRatedMovies());
    }

    @Test
    public void rateMovie(){ //test for å sjekke rateMovie
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
