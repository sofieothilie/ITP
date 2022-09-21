package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap; 


public class UserTest {
    User testUser = new User("username", "password"); 

        Movie m1 = new Movie("Cinderella", "fantasy");
        Movie m2 = new Movie("Snowwhite", "fantasy");
    
    @Test
    public void testGetters(){
        //testing username
        assertEquals("username", testUser.getUsername());
        //testing password
        assertEquals("password", testUser.getPassword());
    }

    @Test
    public void testGetRatedMovies(){
        System.out.println(m1.getMovieRegister());
        testUser.rateMovie(m1, 4);
        HashMap<Movie, Integer> compare = new HashMap<>();
        compare.put(m1, 4);
        assertEquals(compare, testUser.getRatedMovies());
    }

    @Test
    public void assertRateMovie(){
        testUser.rateMovie(m1, 3);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testUser.rateMovie(m1, 4);; 
        }, "The Movie is already rated");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testUser.rateMovie(m2, 6);; 
        }, "Rating must be an integer from 1 to 5");

        
    }
   

    
}
