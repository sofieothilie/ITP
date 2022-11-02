package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for move objects.
 */
public class Movie {
  private final String title;
  private final String genre;
  private List<Integer> allRatings = new ArrayList<>();
  private static List<String> GENRES = Arrays.asList("action",
      "comedy", "drama",
      "fantasy", "horror",
      "mystery", "romance", "thriller");

  /**
   * Constructor that takes three arguments.
   *
   * @param title a String 
   * @param genre a String, but must match one of the elements in GENRES
   * @param allRatings a list of integers
   */
  @JsonCreator
  public Movie(@JsonProperty("title") String title, @JsonProperty("genre") String genre,
      @JsonProperty("ratings") List<Integer> allRatings) {
    // constructor for a movie object from file
    this(title, genre);
    if (allRatings != null) {
      this.allRatings = new ArrayList<>(allRatings);
    } else {
      this.allRatings = List.of();
    }
  }

  /**
   * Constructor that takes two arguments.
   *
   * @param title a String
   * @param genre a String
   */
  public Movie(String title, String genre) { // create Movie-object with title and genre
    if (title.isEmpty()) {
      throw new IllegalArgumentException("Title cannot be empty");
    }
    this.title = title;
    if (!GENRES.contains(genre)) {
      throw new IllegalArgumentException("Not a valid genre");
    }
    this.genre = genre;
    this.allRatings = new ArrayList<>();
  }

  public String getTitle() { // return title
    return title;
  }

  public String getGenre() { // return genre
    return genre;
  }

  /**
   * getAllRatings method. 
   *
   * @return a list of integers
   */
  public List<Integer> getAllRatings() { // returns a copy of the list with all ratings
    List<Integer> copyAllRatings = new ArrayList<Integer>(allRatings);
    return copyAllRatings;
  }

  /**
   * addRating method.
   *
   * @param rating an int
   */
  public void addRating(int rating) { // adds a rating to the list of all ratings and adds
    if (rating < 1 || rating > 5) { // checks whether the rating is between 1 and 5
      throw new IllegalArgumentException("Not a valid rating");
    }
    allRatings.add(rating);
  }

  /**
   * getAverageRating method.
   *
   * @return a double
   */
  @JsonIgnore
  public double getAverageRating() { // calculates average of all ratings for this movie
    Integer sum = 0;
    for (int rating = 0; rating < allRatings.size(); rating++) {
      sum += allRatings.get(rating);
    }
    double average = (sum.doubleValue() / allRatings.size());
    return average;

  }

  /** 
   * toString method.
   *
   * @return a string
   */
  public String toString() { // returns a string with title and genre and average rating
    return "" + this.getTitle() + "; " 
      + this.getGenre() + "; " 
      + String.format("%.2f", this.getAverageRating());
  }

  @Override
  public boolean equals(Object object) { // Checks if an object is equal
    if (object instanceof Movie) {
      if (this.getTitle().equals(((Movie) object).getTitle())) {
        if (this.getGenre().equals(((Movie) object).getGenre())) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    // Enables comparing over hashcode.
    return this.getTitle().hashCode() + this.getGenre().hashCode();
  }

}
