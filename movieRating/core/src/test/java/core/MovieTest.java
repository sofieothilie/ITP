package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovieTest {
  private Movie m1;
  private Movie m2;
  
  @BeforeEach
  public void setUp(){
    this.m1 = new Movie("Cinderella", "fantasy");
    this.m2 = new Movie("Star Wars", "action");
  }



  @DisplayName("Testing the constructor")
  @Test
  public void testConstructor() { // tests the constructors
    //public Movie(String title, String genre)
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new Movie("", "fantasy");
    }, "Title cannot be empty");

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new Movie("Snowwhite", "fairytale");
    }, "Not a valid genre");

    assertEquals("Star Wars", m2.getTitle(), "Title was not equal to expected title.");
    assertEquals("action", m2.getGenre(), "Genre was not equal to expected genre");
    
    //public Movie(@JsonProperty("title") String title, @JsonProperty("genre") String genre,
    //@JsonProperty("ratings") List<Integer> allRatings)
    List<Integer> ratings = List.of(3, 4);
    Assertions.assertDoesNotThrow(() -> {
      new Movie("Danny", "fantasy", ratings);
    }, "Should be able to create a movie object with json constructor");
    Movie m4 = new Movie("Danny", "fantasy", ratings);
    Assertions.assertDoesNotThrow(() -> {
      new Movie("Danny", "fantasy", ratings);
    }, "Should be able to create a movie object with json constructor");
    Movie m = new Movie("Danny", "fantasy", List.of());
    assertEquals("Danny", m4.getTitle(), "Genre was not equal to expected genre");
    assertEquals("fantasy", m4.getGenre(), "Genre was not equal to expected genre");
    assertEquals(m4.getAllRatings(), ratings, "not all or too many ratings were added to object.");
  }

  @DisplayName("Testing  getTitle and getGenre")
  @Test
  public void testGetters() { // tester get-metodene
    assertEquals("Cinderella", m1.getTitle(), "Title was not equal to expected title");
    assertEquals("fantasy", m1.getGenre(), "Genre was not equal to expected genre");
  }

  @DisplayName("Testing getAllRatings")
  @Test
  public void testGetAllRatings() { // tester getAllRatings
    m1.addRating(3);
    m1.addRating(4);
    m1.addRating(5);
    assertEquals(3, m1.getAllRatings().size(), "All ratings was not as expected.");
  }

  @DisplayName("Testing addRating")
  @Test
  public void testAddRating() { // tester addRating-metoden
    m1.addRating(3);
    m1.addRating(4);
    m1.addRating(5);
    assertEquals(3, m1.getAllRatings().size(), "Something went wrong when adding a rating.");
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      m1.addRating(6);
    }, "Not a valid rating");
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      m1.addRating(0);
    }, "Not a valid rating");
  }

  @DisplayName("Testing getAverageRating")
  @Test
  public void testGetAverageRating() { // tester getAverageRating-metoden
    m1.addRating(3);
    m1.addRating(4);
    m1.addRating(5);
    assertEquals(4.0, m1.getAverageRating(), "Average rating was not equal to expected average rating");
  }

  @DisplayName("Testing toString")
  @Test
  public void testToString() { // tester toString-metoden
    m1.addRating(3);
    m1.addRating(4);
    m1.addRating(5);
    Double rating = (3.0 + 4 + 5) / 3;
    assertEquals("Cinderella; fantasy; " + String.format("%.2f", rating), m1.toString(),
        "ToString didn't match expected toString");
  }

  @DisplayName("Testing overridden equals method")
  @Test
  public void testEquals() {
    assertFalse(m1.equals(m2), "Movie 1 is not suppose to be equal to movie 2");
    assertTrue(m1.equals(m1), "Movie 1 is suppose to be equal to itself.");
    Movie m3 = new Movie(m1.getTitle(), m1.getGenre());
    assertTrue(m1.equals(m3), "Movie 1 and movie 3 has same name and genre, should thus be equal.");
    Movie m4 = new Movie(m1.getTitle(), m2.getGenre());
    assertFalse(m1.equals(m4), "Movie 1 is not suppose to be equal to movie 4 due to different genres.");
    Object obj = new Object();
    assertFalse(m1.equals(obj), "Obj is not a movie object and should thus not be equal to m1.");
  }
}
