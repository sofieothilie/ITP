package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for movie objects.
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
   * Constructor that that takes inn a title, genre and a list of ratings.
   *
   * @param title a string, the name of the movie
   * @param genre a string, that must match one of the elements in the static list GENRES
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
   * Constructor that takes in a title and a genre.
   *
   * @param title a string the name of the movie
   * @param genre a string that matches one of the elements in the static list GENRES
   * @throws IllegalArgumentException if title is empty
   * @throws IllegalArgumentException if not a valid genre
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

  /**
   * Getter for title.
   *
   * @return a string, the title of the movie
   */
  public String getTitle() { 
    return title;
  }

  /**
   * Getter for genre.
   *
   * @return a string, the genre of the movie
   */
  public String getGenre() { 
    return genre;
  }

  /**
   * Getter for allRatings. 
   * returns a copy of the list
   *
   * @return a list of all ratings for this movie
   */
  public List<Integer> getAllRatings() { 
    List<Integer> copyAllRatings = new ArrayList<Integer>(allRatings);
    return copyAllRatings;
  }

  /**
   * Method that adds a rating to the movie object.
   * 
   *
   * @param rating an int rating
   * @throws IllegalArgumentexception if rating isn't between 1 and 5
   */
  public void addRating(int rating) { 
    if (rating < 1 || rating > 5) { 
      throw new IllegalArgumentException("Not a valid rating");
    }
    allRatings.add(rating);
  }

  /**
   * Method that returns the average rating for the movie.
   *
   * @return a double average rating
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
   * @return a string with title, genre and average rating for the movie
   */
  public String toString() { 
    return "" + this.getTitle() + "; " 
      + this.getGenre() + "; " 
      + String.format("%.2f", this.getAverageRating());
  }

  public void deleteMovie(int rating){
    if (allRatings.isEmpty()){
      throw new IllegalArgumentException("No ratings added yet");
    }
    if(this.allRatings.contains(rating)){
      this.allRatings.remove(Integer.valueOf(rating));
    }

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

  public static void main(String[] args) {
    Movie m = new Movie("111", "fantasy");
    m.addRating(1);
    m.addRating(2);
    m.addRating(3);
    System.out.println(m.getAllRatings());
    m.deleteMovie(3);
    System.out.println(m.getAllRatings());
  }
}
