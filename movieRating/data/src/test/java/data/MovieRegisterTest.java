package data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
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

        assertEquals(m1.getTitle(), register.getMovie("Cinderella", "fantasy").getTitle());
        assertEquals(m1.getGenre(), register.getMovie("Cinderella", "fantasy").getGenre());

     
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
        List<Movie> moviesFound = register.searchMovieTitle("fantasy");
        assertEquals(testList.size(), moviesFound.size());
        assertEquals(m1.getTitle(),moviesFound.get(0).getTitle() );
        assertEquals(m2.getTitle(),moviesFound.get(1).getTitle() );
        assertEquals(m1.getGenre(),moviesFound.get(0).getGenre() );
        assertEquals(m2.getGenre(),moviesFound.get(1).getGenre() );

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
        assertEquals(m1.getTitle(),moviesFound.get(0).getTitle() );
        assertEquals(m4.getTitle(),moviesFound.get(1).getTitle() );
        assertEquals(m1.getGenre(),moviesFound.get(0).getGenre() );
        assertEquals(m4.getGenre(),moviesFound.get(1).getGenre() );
    }

    @DisplayName("Testing to get a movie")
    @Test
    public void testGetMovie(){
        MovieRegister register = new MovieRegister();
        register.addMovie(m1);
        register.addMovie(m2);

        //Test getMovie
        assertEquals(m1.getTitle(), register.getMovie("Cinderella", "fantasy").getTitle());
        assertEquals(m1.getGenre(), register.getMovie("Cinderella", "fantasy").getGenre());

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
