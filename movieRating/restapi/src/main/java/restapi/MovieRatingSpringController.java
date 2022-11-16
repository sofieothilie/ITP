package restapi;

import core.Movie;
import core.User;
import data.MovieRegister;
import data.UserRegister;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for MovieRatingApplication.
 */

@RestController
@RequestMapping("/api/v1/movieRating")
public class MovieRatingSpringController {
  /**
   * Fields for controller class.
   *
   * @param movReg MovieRegister
   * @param userReg UserRegister
   */
  private final MovieRegister movReg;
  private final UserRegister userReg;

  public MovieRatingSpringController() {
    this.movReg = new MovieRegister("movieRegistry");
    this.userReg = new UserRegister("userRegistry", "movieRegistry");
  }

  /**
   * Constructor for controller.
   *
   * @param movFile a string for filename
   * @param userFile a string for filename
   */
  public MovieRatingSpringController(String movFile, String userFile) {
    this.movReg = new MovieRegister(movFile);
    this.userReg = new UserRegister(userFile, movFile);
  }

  /**
   * WORKING
   * Writes movies to localhost:8080/movieRating/movies.
   *
   * @return List of Movies
   */
  @GetMapping(path = "movies")
  public List<Movie> getMovieRegister() {
    return new ArrayList<Movie>(movReg.getAllMovies());
  }

  /**
   * WORKING
   * Get movie by title and genre if it exists.
   * http://localhost:8080/movieRating/movies
   * 
   *
   * @param title a string
   * @param genre a string
   * @return Movie
   */
  @GetMapping(path = "movies/{title}&{genre}")
  public Movie getMovie(@PathVariable("title") final String title, 
      @PathVariable("genre") final String genre) {
    return movReg.getMovie(title, genre);
  }

  /**
   * Add movie to rest.
   * localhost:8080/movieRating/movies/{movie}
   *
   * @param movie adds a movie to server
   */
  @PostMapping(path = "movies/{movie}")
  public void addMovie(@PathVariable("movie") Movie movie) {
    movReg.addMovie(movie);
  }
  
  /**
   * Writes movies to server.
   * localhost:8080/movieRating/users
   *
   * @return List of Users
   */
  @GetMapping(path = "users")
  public List<User> getUserRegister() {
    return new ArrayList<User>(userReg.getAllUsers());
  }

  
  /**
   * Get user by username if it exists.
   * localhost:8080/movieRating/users/{username}
   *
   * @param username a string
   * @return User
   */
  @GetMapping(path = "users/{username}")
  public User getUser(@PathVariable("username") final String username) {
    return userReg.getUser(username);
  }

  /**
   * Throws if user already exists. 
   *
   * @param username a string
   * @param password a string
   */
  public void existingUser(String username, String password) {
    userReg.existingUser(username, password);
  }

  /**
   * Creates a new user.
   * localhost:8080/movieRating/users/{user}
   *
   * @param user a user
   */
  @PostMapping(path = "users/{user}")
  public void registerNewUser(@PathVariable("user") User user) {
    this.userReg.registerNewUser(user);
  }

  /**
   * Adds rating for movie.
   * localhost:8080/movieRating/rate/{user}&{movie}&{rating}&{action}
   *
   * @param user a user
   * @param movie a movie
   */
  @PutMapping(path = "rate/{user}&{movie}&{rating}&{action}")
  public void updateMovieAndUser(@PathVariable("user") User user,
      @PathVariable("movie") Movie movie) {
    this.userReg.updateMovieAndUser(user, movie);
  } 
}
