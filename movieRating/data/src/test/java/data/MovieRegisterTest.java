package data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import core.Movie;
import core.User;

public class MovieRegisterTest {

    User user1 = new User("ellica", "ellica123");
    Movie m1 = new Movie("Cinderella", "fantasy");
    Movie m2 = new Movie("Snowhite", "fantasy");
    Movie m3 = new Movie("The Notebook", "romance");
    Movie m4 = new Movie("Cinderella", "romance");
    
    @DisplayName("Testing to add a movie to the register")
    @Test
    public void testAddMovie(){
        MovieRegister register = new MovieRegister();
        register.addMovie(m1);

        assertEquals(m1, register.getMovie("Cinderella", "fantasy"));
     
        //Test IllegalArgumentException when movie alreday exists
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.addMovie(m1);
        }, "Movie already added to the register");
    }

    @DisplayName("Testing update movie method")
    @Test
    public void testUpdateMovie(){
        MovieRegister register = new MovieRegister();

        //Test IllegalArgumentException if register is empty
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.updateMovie(m1);
        }, "Register empty");

        register.addMovie(m1);

        //Test IllegalArgumentException if the movie is not added to the register
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.updateMovie(m2);
        }, "Movie not in register");

        user1.rateMovie(m1, 4);
        //Test that the movies ratings is updated in the register
        register.updateMovie(m1);
        assertEquals(4, m1.getAllRatings().get(-1));
    }

    @DisplayName("Testing to search movies by genre")
    @Test
    public void testSearchGenre(){
        MovieRegister register = new MovieRegister();
        register.addMovie(m1);
        register.addMovie(m2);
        register.addMovie(m3);
        List<Movie> testList = new ArrayList<>();
        testList.add(m1);
        testList.add(m3);

        //Test if method returns all movies with given genre
        assertEquals(testList, register.searchGenre("fantasy"));
    }

    @DisplayName("Testing to search movies by title")
    @Test
    public void testSearchMovieTitle(){
        MovieRegister register = new MovieRegister();
        register.addMovie(m1);
        register.addMovie(m2);
        register.addMovie(m4);
        List<Movie> testList = new ArrayList<>();
        testList.add(m1);
        testList.add(m2);

        //Test if the method returns all movies with given title
        assertEquals(testList, register.searchMovieTitle("Cinderella"));
    }

    @DisplayName("Testing to get a movie")
    @Test
    public void testGetMovie(){
        MovieRegister register = new MovieRegister();
        register.addMovie(m1);
        register.addMovie(m2);

        //Test getMovie
        assertEquals(m1, register.getMovie("Cinderella", "fantasy"));

        //IllegalArgumentexception if movie is not added to the register
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.getMovie("The Notebook", "romance");
        }, "Movie not in register");
    }    
}
