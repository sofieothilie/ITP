package data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.Movie;
import core.User;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovieRegisterTest {
  private Movie m1, m2, m3, m4;
  private User user1;
  private MovieRegister movieRegister;
  private final String filename = "testMovie";

  @BeforeEach
  public void setUp() {
    user1 = new User("ellica", "ellica123");
    m1 = new Movie("Cinderella", "fantasy");
    m2 = new Movie("Snowhite", "fantasy");
    m3 = new Movie("The Notebook", "romance");
    m4 = new Movie("Cinderella", "romance");
    movieRegister = new MovieRegister(filename);
  }

  @DisplayName("Tests trying to create a invalid constructor")
  @Test
  public void testConstructor(){
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> {
        new MovieRegister("n0!V4lid");
      },
      "Filename should not be valid for register"
    );
  }


  @DisplayName("Testing to add a movie to the register")
  @Test
  public void testAddMovie() {
    movieRegister.addMovie(m1.getTitle(), m1.getGenre());

    assertEquals(
      m1,
      movieRegister.getMovie("Cinderella", "fantasy"),
      "movies were not equal."
    );

    //Test IllegalArgumentException when movie alreday exists
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> {
        movieRegister.addMovie(m1.getTitle(), m1.getGenre());
      },
      "Movie already added to the register"
    );
  }

  @DisplayName("Testing update movie method")
  @Test
  public void testUpdateMovie() {
    //Test IllegalArgumentException if register is empty
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> {
        movieRegister.updateMovie(m1);
      },
      "Register empty"
    );

    movieRegister.addMovie(m1.getTitle(), m1.getGenre());

    //Test IllegalArgumentException if the movie is not added to the register
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> {
        movieRegister.updateMovie(m2);
      },
      "Movie not in register"
    );
    List<Integer> ratings = m1.getAllRatings();
    user1.rateMovie(m1, 4);
    //Test that the movies ratings is updated in the register
    ratings.add(4);
    movieRegister.updateMovie(m1);

    assertEquals(ratings, m1.getAllRatings());
  }

  @DisplayName("Testing to search movies by genre")
  @Test
  public void testSearchGenre() {
    movieRegister.addMovie(m1.getTitle(), m1.getGenre());
    movieRegister.addMovie(m2.getTitle(), m2.getGenre());
    movieRegister.addMovie(m3.getTitle(), m3.getGenre());
    List<Movie> testList = new ArrayList<>();
    testList.add(m1);
    testList.add(m2);

    //Test if method returns all movies with given genre
    List<Movie> moviesFound = movieRegister.searchGenre("fantasy");
    assertEquals(testList.size(), moviesFound.size());
    assertEquals(
      m1,
      moviesFound.get(0),
      "movie added was not equal to movie found by getting all movies."
    );
    assertEquals(
      m2,
      moviesFound.get(1),
      "movie added was not equal to movies found by getting all movies."
    );
  }

  @DisplayName("Testing to search movies by title")
  @Test
  public void testSearchMovieTitle() {
    movieRegister.addMovie(m1.getTitle(), m1.getGenre());
    movieRegister.addMovie(m2.getTitle(), m2.getGenre());
    movieRegister.addMovie(m4.getTitle(), m4.getGenre());
    List<Movie> testList = new ArrayList<>();
    testList.add(m1);
    testList.add(m4);

    //Test if the method returns all movies with given title
    List<Movie> moviesFound = movieRegister.searchMovieTitle("Cinderella");
    assertEquals(
      testList.size(),
      moviesFound.size(),
      "The number of movies found with given title is not equal to expected number"
    );
    assertEquals(
      m1,
      moviesFound.get(0),
      "movie added was not equal to movies found by search."
    );
    assertEquals(
      m4,
      moviesFound.get(1),
      "movie added was not equal to movies found by search."
    );
  }

  @DisplayName("Testing to get a movie")
  @Test
  public void testGetMovie() {
    movieRegister.addMovie(m1.getTitle(), m1.getGenre());
    movieRegister.addMovie(m2.getTitle(), m2.getGenre());

    //Test getMovie
    assertEquals(
      m1,
      movieRegister.getMovie("Cinderella", "fantasy"),
      "movie added was not equal to movies found by get movie"
    );

    //IllegalArgumentexception if movie is not added to the register
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> {
        movieRegister.getMovie("The Notebook", "romance");
      },
      "Movie not in register"
    );
  }

  @AfterEach
  public void tearDown() {
    MovieHandler handler = new MovieHandler(filename);
    try {
      if (handler.fileExists()) {
        Files.delete(handler.getFile().toPath());
      }
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }
}
