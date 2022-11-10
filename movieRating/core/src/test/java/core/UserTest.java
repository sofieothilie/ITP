package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.HashMap;

public class UserTest {
  private User testUser;
  private User testUserCopy;
  private User testUserNotCopy;
  private Movie m1;
  private Movie m2;

  @BeforeEach
  public void setUp(){
    this.testUser = new User("username", "password");
    this.testUserCopy = new User(testUser.getUsername(), testUser.getPassword());
    this.testUserNotCopy = new User(testUser.getUsername(), "notUsersPassword");
    this.m1 = new Movie("Cinderella", "fantasy");
    this.m2 = new Movie("Snowwhite", "fantasy");
  }

  @DisplayName("Testing validation of username and password")
  @Test
  public void testValidUsernameAndPassword() {
    Assertions.assertDoesNotThrow(() -> {
      new User("Username1234", "Password1234");
    }, "Valid username and password. Should be created.");

    HashMap<Movie, Integer> map = new HashMap<>();
    Assertions.assertDoesNotThrow(() -> {
      new User("Username1234", "Password1234", map);
    }, "Valid username, password and empty rating. Should be created.");
    map.put(m1, 4);
    map.put(m2, 3);
    Assertions.assertDoesNotThrow(() -> {
      new User("Username1234", "Password1234", map);
    }, "Valid username, password and nonempty rating. Should be created.");
    this.testUser.rateMovie(m1, 4);
    this.testUser.rateMovie(m2, 3);
    assertEquals(this.testUser.getRatedMovies(), map);

    
  }

  @DisplayName("Testing invalid usernames and passwords")
  @Test
  public void testInvalidUsernameAndPassword() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new User("username-!", "password");
    }, "Invalid username");
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new User("username", "pass#");
    }, "Invalid password");
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new User("", "password");
    }, "Invalid Username");
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new User("username-!", "");
    }, "Invalid username and password");
  }

  @DisplayName("Testing getUsername and getPassword")
  @Test
  public void testGetters() {
    assertEquals("username", testUser.getUsername(), "Username not equal to expected username");
    assertEquals("password", testUser.getPassword(), "Password is not equal to expected password");
  }

  @DisplayName("Testing getRatedMovies")
  @Test
  public void testGetRatedMovies() {
    testUser.rateMovie(m1, 4);
    testUser.rateMovie(m2, 3);
    HashMap<Movie, Integer> compare = new HashMap<>();
    compare.put(m1, 4);
    compare.put(m2, 3);

    assertEquals(compare, testUser.getRatedMovies(), "Rated movies is not the same as expected rated movies");
  }

  @DisplayName("Testing rateMovie")
  @Test
  public void rateMovie() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      testUser.rateMovie(m2, 6);
      ;
    }, "Rating must be an integer from 1 to 5");

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      testUser.rateMovie(m2, 0);
      ;
    }, "Rating must be an integer from 1 to 5");

    testUser.rateMovie(m1, 2);
    testUser.rateMovie(m2, 4);

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      testUser.rateMovie(m1, 4);
      ;
    }, "The Movie is already rated");

    HashMap<Movie, Integer> compare = new HashMap<>();
    compare.put(m1, 2);
    compare.put(m2, 4);
    assertEquals(compare, testUser.getRatedMovies(), "Something went wrong when rating a movie");
  }

  @DisplayName("testing deleteMovie")
  @Test
  public void testDeleteMovie(){
    testUser.rateMovie(m1, 3);
    testUser.rateMovie(m2, 4);
    testUser.deleteMovie(m1);
    assertFalse(testUser.getRatedMovies().containsKey(m1));
  }  
  

  @DisplayName("Testing overridden equals method")
  @Test
  public void testEquals() {
    User user = new User("testUser", "passwords");
    assertFalse(testUser.equals(user), "testUser and user is not suppose to be equal.");
    assertTrue(testUser.equals(testUser), "TestUser is suppose to be equal to itself.");
    assertTrue(testUser.equals(testUserCopy), "testUserCopy is a copy of testUser and should thus be equal.");
    assertFalse(testUserNotCopy.equals(user), "testUserNotCopy and user is not suppose to be equal.");
    assertFalse(user.equals(new Object()), "testUser and user is not suppose to be equal.");
  }

  @DisplayName("Testing overridden hashcode method")
  @Test
  public void testHashCode() {
    User userCopy = new User(testUser.getUsername(), testUser.getPassword());
    Assertions.assertEquals(userCopy.hashCode(), testUser.hashCode());
    Assertions.assertNotEquals(testUser.hashCode(), testUserNotCopy.hashCode());

  }

}
