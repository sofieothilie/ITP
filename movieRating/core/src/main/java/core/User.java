package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import core.json.MovieDeserializerForUser;
import core.json.MovieSerializerForUser;
import java.util.HashMap;

/**
 * Class for user objects.
 */
public class User {
  private final String username;
  private final String password;
  private HashMap<Movie, Integer> ratedMovies;

  /**
   * Constructor that takes three arguments.
   *
   * @param username a string
   * @param password a string
   * @param ratedMovies hashMap of Movie and Integer obejcts
   */
  @JsonCreator
  public User(@JsonProperty("username") String username, @JsonProperty("password") String password,
      @JsonProperty("ratings") @JsonDeserialize(using = MovieDeserializerForUser.class) 
      HashMap<Movie, Integer> ratedMovies) {
    // constructor for file management
    this(username, password);
    if (ratedMovies != null) {
      this.ratedMovies = new HashMap<>(ratedMovies);
    }
  }

  /**
   * Constructor that takes two arguments.
   *
   * @param username a string
   * @param password a string
   */
  public User(String username, String password) { // constructor that creates User object
    if (username.matches("[a-zA-Z0-9]+") && password.matches("[a-zA-Z0-9]+")) {
      this.username = username;
      this.password = password;
      ratedMovies = new HashMap<>();
    } else if (username.isEmpty() || password.isEmpty()) {
      throw new IllegalArgumentException("Username and password can not be empty");
    } else {
      throw new IllegalArgumentException(
        "Username and password can only contain numbers and letters");
    }
  }

  public String getUsername() { // return username
    return username;
  }

  public String getPassword() { // return the users password
    return password;
  }

  /**
   * getRatedMovies method.
   *
   * @return a hashmap of Movie and Integer objects
   */
  @JsonSerialize(using = MovieSerializerForUser.class)
  @JsonDeserialize(using = MovieDeserializerForUser.class)
  // returns a hashmap with movies and your rating on the movie
  public HashMap<Movie, Integer> getRatedMovies() { 
    // This method cannot be used when writing user to file. This method therefore
    // implements jsonSerialize and jsonDeserialize.
    if (ratedMovies.isEmpty()) {
      return new HashMap<>();
    } else {
      return new HashMap<>(ratedMovies);
    }
  }

  /**
   * rateMovie method.
   *
   * @param movie a Movie object
   * @param myRating an Integer 
   */
  public void rateMovie(Movie movie, Integer myRating) { // method of rating a film
    if (this.hasRatedMovie(movie)) {
      throw new IllegalArgumentException("The movie is already rated");
    }
    if (myRating < 1 || myRating > 5) {
      throw new IllegalArgumentException("Rating must be an integer from 1 to 5");
    }
    ratedMovies.put(movie, myRating);
    movie.addRating(myRating);
  }

  /**
   * hasRatedMovie method.
   *
   * @param movie a Movie object
   * @return true if the movie is already rated
   */
  public boolean hasRatedMovie(Movie movie) { // Checks if the user has already rated a movie
    boolean containsMovie = false;
    for (Movie ratedMovie : this.getRatedMovies().keySet()) {
      if ((ratedMovie.getTitle().equals(movie.getTitle()) 
          && ratedMovie.getGenre().equals(movie.getGenre()))) {
        containsMovie = true;
      }
    }
    return containsMovie;
  }

  @Override
  public boolean equals(Object object) { // Comparing User objects
    if (object instanceof User) {
      if (this.getUsername().equals(((User) object).getUsername())) {
        if (this.getPassword().equals(((User) object).getPassword())) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public int hashCode() {
    // Enables comparing over hashcode.
    return this.getUsername().hashCode() + this.getPassword().hashCode();
  }

}
