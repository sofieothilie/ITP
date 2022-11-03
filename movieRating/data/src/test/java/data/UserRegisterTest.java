package data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.Movie;
import core.User;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRegisterTest {

  // TODO: bruke equals der det er brukt assertEquals
  // TODO: teste MovieHandler her
  // TODO: sjekke Jacoco-test

  private User user1;
  private User user2;
  private User user3;
  private Movie movie;
  private UserRegister userRegister;
  private MovieRegister movieRegister;
  private final String userFilename = "userTest";
  private final String movieFilename = "testMovie";

  @BeforeEach
  public void setUp() {
    this.user1 = new User("ellica", "ellica123");
    this.user2 = new User("rebekka", "rebekka123");
    this.user3 = new User("sofie", "sofie123");
    this.movie = new Movie("Snow", "fantasy");

    this.userRegister = new UserRegister(userFilename, movieFilename);
    this.movieRegister = new MovieRegister(movieFilename);
  }

  @DisplayName("Testing to register a new user")
  @Test
  public void testRegisterNewUser() {
    Assertions.assertDoesNotThrow(
      () -> {
        userRegister.registerNewUser(user1);
      },
      "User should be written to file without rating a movie"
    );
    assertTrue(
      user1.equals(userRegister.getUser("ellica")),
      "Ellica should be found in register and be equal to user1."
    );

    //Tests that rated movie is written to file and can be read from file
    user1.rateMovie(this.movie, 1);
    movieRegister.addMovie(this.movie);
    userRegister.updateRatedMovie(user1, movie);
    User testUser = userRegister.getUser(user1.getUsername());
    Boolean foundMovie = false;
    Boolean foundRating = false;
    for (Entry<Movie, Integer> ratedMovieEntry : testUser
      .getRatedMovies()
      .entrySet()) {
      if (ratedMovieEntry.getKey().equals(movie)) {
        foundMovie = true;
        if (ratedMovieEntry.getValue() == 1) {
          foundRating = true;
        }
      }
    }
    assertTrue(foundMovie, "The movie was not retrieved from the file.");
    assertTrue(
      foundRating,
      "The rating was not correctly retrieved from file."
    );

    //Testing IllegalArgumentException if user already exists
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> {
        userRegister.registerNewUser(user1);
      },
      "User already in register"
    );
  }

  @DisplayName("Testing to get all users")
  @Test
  public void testGetUsers() {
    user1.rateMovie(movie, 4);
    user2.rateMovie(movie, 3);
    userRegister.registerNewUser(user1);
    userRegister.registerNewUser(user2);
    Assertions.assertDoesNotThrow(
      () -> {
        userRegister.registerNewUser(user3);
      },
      "User should be added eventhough no rated movie."
    );

    List<User> testList = new ArrayList<>();
    testList.add(user1);
    testList.add(user2);
    testList.add(user3);
    //Tests that user3 hasn't been added to register yet.
    assertTrue(
      userRegister.getUsers().containsAll(testList),
      "All users should have been added to register."
    );
  }

  @DisplayName("Testing to get a user by username")
  @Test
  public void testGetUser() {
    user1.rateMovie(movie, 4);
    user2.rateMovie(movie, 1);
    userRegister.registerNewUser(user1);
    userRegister.registerNewUser(user2);

    //Test that method returns given user from register
    assertEquals(
      "ellica",
      userRegister.getUser("ellica").getUsername(),
      "User retrieved from file not equal to user written to file."
    );

    //Test that method retursn null if the user is not registered
    assertEquals(
      null,
      userRegister.getUser("sofie"),
      "User should not exist in file."
    );
  }

  @DisplayName("Testing existing user")
  @Test
  public void testExistingUser() {
    //Test IllegalArgumentException if register is empty
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> {
        userRegister.existingUser("ellica", "ellica123");
      },
      "User register emtpy"
    );

    //Test illegalArgumentException if user is not registered
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> {
        userRegister.existingUser("ellica", "ellica123");
      },
      "User not in register"
    );
    user1.rateMovie(movie, 5);
    userRegister.registerNewUser(user1);
    //Test IllegalArgumentException if the password is invalid
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> {
        userRegister.existingUser("ellica", "ellica1234");
      },
      "Invalid password"
    );
  }

  @AfterEach
  public void tearDown() {
    UserHandler userHandler = new UserHandler(userFilename);
    MovieHandler movieHandler = new MovieHandler(movieFilename);
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
