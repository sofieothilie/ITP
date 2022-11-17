package restapi;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.ContextConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import core.Movie;
import core.User;
import data.MovieHandler;
import data.UserHandler;

@ContextConfiguration(classes = { MovieRatingSpringController.class})
@SpringBootTest()
public class MovieRatingSpringControllerTest {

  private MovieRatingSpringController mrsc;
  private final static String movieFileName = "movieTest";
  private final static String userFileName = "userTest";

  private User user1;
  private User user2;
  private Movie m1;
  private Movie m2;
  private Movie m3;
  private Movie m4;
  private List<User> userList = new ArrayList<User>();
  private List<Movie> movieList = new ArrayList<Movie>();


  @BeforeEach
  public void setup(){
    this.mrsc = new MovieRatingSpringController(movieFileName, userFileName);
    this.user1 = new User("ellica", "ellica123");
    this.user2 = new User("spiderman", "spiderman123");
    this.m1 = new Movie("Cinderella", "fantasy");
    this.m2 = new Movie("Snowhite", "fantasy");
    this.m3 = new Movie("The Notebook", "romance");
    this.m4 = new Movie("Cinderella", "romance");
    userList.add(user1);
    userList.add(user2);
    movieList.add(m1);
    movieList.add(m2);
    movieList.add(m3);
    movieList.add(m4);
  }

  @Test
  @DisplayName("Tests adding and fetching movies from server")
  public void testMovie(){
    for (Movie movie : this.movieList) {
      mrsc.addMovie(movie);   
    }
    List<Movie> moviesFromServer = this.mrsc.getMovieRegister();
    assertTrue(moviesFromServer.containsAll(this.movieList), "All movies weren't retrieved from server");
    assertTrue(m1.equals(this.mrsc.getMovie(m1.getTitle(), m1.getGenre())), "m1 should be on server and thus fetchable.");
  }

  @Test
  @DisplayName("Test adding and fetching users from server")
  public void testUsers() throws Exception {
    for (User  user : this.userList) {
      mrsc.registerNewUser(user);   
    }
    List<User> usersFromServer = this.mrsc.getUserRegister();
    assertTrue(usersFromServer.containsAll(this.userList), "All users weren't retrieved from server");
    assertTrue(user1.equals(this.mrsc.getUser(user1.getUsername())), "user1 should be on server and thus fetchable.");
    Assertions.assertDoesNotThrow(() -> {
      this.mrsc.existingUser(user2.getUsername(), user2.getPassword());
    }, "user2 should be on server");
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      this.mrsc.existingUser(user1.getUsername(), user2.getPassword());
    }, "No such user should be on server with that username and password combination.");
  }

  @Test
  public void testUpdate() {
    for (User  user : this.userList) {
      mrsc.registerNewUser(user);   
    }
    for (Movie movie : this.movieList) {
      mrsc.addMovie(movie);   
    }
    user1.rateMovie(m1, 4);
    user2.rateMovie(m1, 2);
    this.mrsc.updateMovieAndUser(user1, m1);
    this.mrsc.updateMovieAndUser(user2, m1);
    assertEquals(user1.getRatedMovies(), this.mrsc.getUser(user1.getUsername()).getRatedMovies(), "user1 locally and on server aren't equal");
    assertEquals(user2.getRatedMovies(), this.mrsc.getUser(user2.getUsername()).getRatedMovies(), "user2 locally and on server aren't equal");
    assertEquals(m1.getAllRatings(), this.mrsc.getMovie(m1.getTitle(), m1.getGenre()).getAllRatings(), "m1 locally and on server aren't equal");
    user2.deleteMovie(m1);
    this.mrsc.updateMovieAndUser(user2, m1);
    assertEquals(m1.getAllRatings(), this.mrsc.getMovie(m1.getTitle(), m1.getGenre()).getAllRatings(), "m1 locally and on server aren't equal");
    assertEquals(user2.getRatedMovies(), this.mrsc.getUser(user2.getUsername()).getRatedMovies(), "user2 locally and on server aren't equal");
  }

  
  @AfterEach
  public void tearDown() {
    UserHandler userHandler = new UserHandler(userFileName);
    MovieHandler movieHandler = new MovieHandler(movieFileName);
    try {
      if(userHandler.fileExists()){
        Files.delete(userHandler.getFile().toPath());
      }
      if (movieHandler.fileExists()) {
        Files.delete(movieHandler.getFile().toPath());
      }
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }
  
}
