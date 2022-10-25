package data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import core.Movie;
import core.User;

public class MovieRegisterTest {
    //TODO: teste MovieHandler her
    //TODO: sjekke Jacoco-test
    //TODO: legge til test message
    //TODO: legge til System.getProperty("user.home")

    Movie m1, m2, m3, m4;
    User user1;
    MovieRegister register;

    @BeforeEach
    public void setUp(){
        user1 = new User("ellica", "ellica123");
        m1 = new Movie("Cinderella", "fantasy");
        m2 = new Movie("Snowhite", "fantasy");
        m3 = new Movie("The Notebook", "romance");
        m4 = new Movie("Cinderella", "romance");
        register = new MovieRegister();
    }
    
    @DisplayName("Testing to add a movie to the register")
    @Test
    public void testAddMovie(){
        MovieRegister register = new MovieRegister();
        register.addMovie(m1);


        assertEquals(m1, register.getMovie("Cinderella", "fantasy"), "movies were not equal.");
     
        //Test IllegalArgumentException when movie alreday exists
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.addMovie(m1);
        }, "Movie already added to the register");
    }

    @DisplayName("Testing update movie method")
    @Test
    public void testUpdateMovie(){

        //Test IllegalArgumentException if register is empty
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.updateMovie(m1);
        }, "Register empty");

        register.addMovie(m1);

        //Test IllegalArgumentException if the movie is not added to the register
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.updateMovie(m2);
        }, "Movie not in register");
        List<Integer> ratings = m1.getAllRatings();
        user1.rateMovie(m1, 4);
        //Test that the movies ratings is updated in the register
        ratings.add(4);
        register.updateMovie(m1);
        

        assertEquals(ratings, m1.getAllRatings());
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
        testList.add(m2);

        //Test if method returns all movies with given genre
        List<Movie> moviesFound = register.searchGenre("fantasy");
        assertEquals(testList.size(), moviesFound.size());
        assertEquals(m1,moviesFound.get(0), "movie added was not equal to movie found by getting all movies.");
        assertEquals(m2,moviesFound.get(1), "movie added was not equal to movies found by getting all movies.");

    }

    @DisplayName("Testing to search movies by title")
    @Test
    public void testSearchMovieTitle(){
        register.addMovie(m1);
        register.addMovie(m2);
        register.addMovie(m4);
        List<Movie> testList = new ArrayList<>();
        testList.add(m1);
        testList.add(m2);

        //Test if the method returns all movies with given title
        List<Movie> moviesFound = register.searchMovieTitle("Cinderella");
        assertEquals(testList.size(), moviesFound.size());
        assertEquals(m1,moviesFound.get(0), "movie added was not equal to movies found by search.");
        assertEquals(m4,moviesFound.get(1), "movie added was not equal to movies found by search." );
    }

    @DisplayName("Testing to get a movie")
    @Test
    public void testGetMovie(){
        MovieRegister register = new MovieRegister();
        register.addMovie(m1);
        register.addMovie(m2);

        //Test getMovie
        assertEquals(m1, register.getMovie("Cinderella", "fantasy"), "movie added was not equal to movies found by get movie");

        //IllegalArgumentexception if movie is not added to the register
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            register.getMovie("The Notebook", "romance");
        }, "Movie not in register");
    }  
    
    @AfterEach
    public void tearDown(){
        MovieHandler handler = new MovieHandler();
        try{
            Files.delete(handler.getFile().toPath());
            //PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(MovieHandler.getFile(), false)));
            //pw.println("");
        }
        catch (IOException e){
            throw new IllegalArgumentException();
        }
    }  
}
