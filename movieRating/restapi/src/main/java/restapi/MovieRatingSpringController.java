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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for MovieRatingApplication.
 */

@RestController
@RequestMapping("/api/v1/movieRating/")
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
   * Writes movies to server.
   * http://localhost:8080/api/v1/movieRating/movies
   *
   * @return List of Movies
   */
  @GetMapping(path = "movies")
  public List<Movie> getMovieRegister() {
    return new ArrayList<Movie>(movReg.getAllMovies());
  }

  /** WORKING
   * Get movie by title and genre if it exists.
   * http://localhost:8080/api/v1/movieRating/movies/{title}&{genre}
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
   * http://localhost:8080/api/v1/movieRating/movies/add
   *
   * @param movie adds a movie to server
   */
  @PostMapping(path = "movies/add")
  public void addMovie(@RequestBody Movie movie) {
    movReg.addMovie(movie);
  }
  
  /**
   * Writes movies to server.
   * http://localhost:8080/api/v1/movieRating/users/
   *
   * @return List of Users
   */
  @GetMapping(path = "users")
  public List<User> getUserRegister() {
    return new ArrayList<User>(userReg.getAllUsers());
  }

  
  /**
   * Get user by username if it exists.
   * http://localhost:8080/api/v1/movieRating/users/{username}
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
   * http://localhost:8080/api/v1/movieRating/users/{user}
   *
   * @param user a user
   */
  @PostMapping(path = "users/add")
  public void registerNewUser(@RequestBody User user) {
    this.userReg.registerNewUser(user);
  }

  public void updateMovieAndUser(User user, Movie movie) {
    this.updateUser(user);
    this.updateMovie(movie);
  }

  /**
   * Updates rating for user.
   * http://localhost:8080/api/v1/movieRating/users/update
   *
   * @param user a user
   */
  @PutMapping(path = "users/update")
  public void updateUser(@RequestBody User user) {
    this.userReg.updateUser(user);
  }

  /**
   * Uupdates rating for movie.
   * http://localhost:8080/api/v1/movieRating/movies/update
   *
   * @param movie a movie
   */
  @PutMapping(path = "movies/update")
  public void updateMovie(@RequestBody Movie movie) {
    this.movReg.updateMovie(movie);
  }
}
