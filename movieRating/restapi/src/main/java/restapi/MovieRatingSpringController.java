package restapi;

import core.Movie;
import core.User;
import data.MovieRegister;
import data.UserRegister;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for MovieRatingApplication.
 */

@RestController
@RequestMapping("/movieRating")
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
  @RequestMapping(path = "movies")
  public List<Movie> getMovieRegister() {
    return new ArrayList<Movie>(movReg.getAllMovies());
  }

  /**
   * WORKING
   * Writes movies to localhost:8080/movieRating/users.
   *
   * @return List of Users
   */
  @RequestMapping(path = "users")
  public List<User> getUserRegister() {
    return new ArrayList<User>(userReg.getAllUsers());
  }

  /**
   * WORKING
   * Get movie by title and genre if it exists.
   * http://localhost:8080/movieRating/movie?title=title&genre=genre
   *
   * @param title a string
   * @param genre a string
   * @return Movie
   */
  @GetMapping(path = "movie")
  public Movie getMovie(@RequestParam("title") final String title,
      @RequestParam("genre") final String genre) {
    return movReg.getMovie(title, genre);
  }

  /**
   * WORKING
   * Returns a list of movies with desired genre.
   * localhost:8080/movieRating/movieGenre?genre=genre
   *
   * @param genre a string
   * @return List of movies
   */
  @GetMapping(path = "movieGenre")
  public List<Movie> searchGenre(@RequestParam("genre") final String genre) {
    return new ArrayList<Movie>(movReg.searchGenre(genre));
  }

  /**
   * WORKING
   * Returns a list of movies with desired genre.
   * localhost:8080/movieRating/movieTitle?title=title
   *
   * @param title a string
   * @return Listof movies
   */
  @GetMapping(path = "movieTitle")
  public List<Movie> searchMovieTitle(@RequestParam("title") final String title) {
    if (this.movReg.searchMovieTitle(title).isEmpty()){
      throw new IllegalArgumentException(("No movies with title: " + title
      + " found in the register"));
    }
    return new ArrayList<Movie>(this.movReg.searchMovieTitle(title));
  }

  /**
   * WORKING
   * Add movie to rest.
   * localhost:8080/movieRating/addMovie?title={title}&genre={genre}
   *
   * @param title a string
   * @param genre a string
   */
  @PostMapping(path = "addMovie")
  public void addMovie(@RequestParam("title") final String title,
      @RequestParam("genre") final String genre) {
    movReg.addMovie(title, genre);
  }

  /** WORKING
   * Get user by username if it exists.
   * http://localhost:8080/movieRating/user?username={username}
   *
   * @param username a string
   * @return User
   */
  @GetMapping(path = "user")
  public User getUser(@RequestParam("username") final String username) {
    return userReg.getUser(username);
  }

  /** WORKING
   * Get user by username if it exists.
   * http://localhost:8080/movieRating/user?username={username}
   *
   * @param username  a string
   * @param password a string
   */
  @PostMapping(path = "fullUser")
  public void existingUser(@RequestParam("username") final String username, 
      @RequestParam("password") final String password) {
    userReg.existingUser(username, password);
  }

  /**
   * WORKING
   * Creates a new user.
   * http://localhost:8080/movieRating/
   * newUser?username={username}&password={password}
   *
   * @param username a string
   * @param password a string
   */
  @PostMapping(path = "newUser")
  public void registerNewUser(@RequestParam("username") final String username,
      @RequestParam("password") final String password) {
    this.userReg.registerNewUser(username, password);
  }

    /**
   * WORKING
   * Writes movies to localhost:8080/movieRating/movies.
   *
   * @return List of Movies
   */
  @RequestMapping(path = "userMovies")
  public Map<Movie, Integer> getUserMovies(@RequestParam("username") final String username) {
    return new HashMap<Movie, Integer>(userReg.getUserMovies(username));
  }

  /**
   * WORKING
   * Adds rating for movie.
   * http://localhost:8080/movieRating/rateMovie?
   * username={username}&movieTitle={title}&genre={genre}&rating={rating}
   *
   * @param username a string
   * @param title a string
   * @param genre a string
   * @param rating an int
   * @param action a string which decideds what switch case to use
   */
  @PutMapping(path = "rateMovie")
  public void updateMovieAndUser(@RequestParam("username") final String username,
      @RequestParam("movieTitle") final String title,
      @RequestParam("movieGenre") final String genre,
      @RequestParam("rating") final Integer rating,
      @RequestParam("action") final String action) {
    this.userReg.updateMovieAndUser(username, title, genre, rating, action);
  } 
}
