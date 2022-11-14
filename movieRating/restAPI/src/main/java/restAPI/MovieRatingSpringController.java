package restapi;

import core.Movie;
import core.User;
import java.util.ArrayList;
import java.util.List;
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
   * @param movSer a MovieRegisterService
   * @param userSer a UserRegisterService
   * @param movFile a string
   * @param userFile a string
   */
  private final MovieRegisterService movSer;
  private final UserRegisterService userSer;
  private static final String movFile = "movieRegistry";
  private static final String userFile = "userRegistry";

  /**
   * Constructor for controller.
   */
  public MovieRatingSpringController() {
    this.movSer = new MovieRegisterService(movFile);
    this.userSer = new UserRegisterService(userFile, movFile);
  }

  /**
   * WORKING
   * Writes movies to localhost:8080/movieRating/movies.
   *
   * @return List of Movies
   */
  @RequestMapping(path = "movies")
  public List<Movie> getMovieRegister() {
    return new ArrayList<Movie>(movSer.getAllMovies());
  }

  /**
   * WORKING
   * Writes movies to localhost:8080/movieRating/users.
   *
   * @return List of Users
   */
  @RequestMapping(path = "users")
  public List<User> getUserRegister() {
    return new ArrayList<User>(userSer.getAllUsers());
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
    return movSer.getMovieByTitleAndGenre(title, genre);
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
    return new ArrayList<Movie>(movSer.getMoviesByGenre(genre));
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
  public List<Movie> searchTitle(@RequestParam("title") final String title) {
    return new ArrayList<Movie>(this.movSer.getMoviesByTitle(title));
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
    Movie m1 = new Movie(title, genre);
    movSer.addAMovie(m1);
  }

  /**
   * WORKING
   * Add rating to movie in rest.
   * localhost:8080/movieRating/
   * addRating?title={title}&genre={genre}&rating={rating}
   *
   * @param title a string 
   * @param genre a string
   * @param rating an integer
   */
  @PutMapping(path = "addRating")
  public void addRatingToMovie(@RequestParam("title") final String title,
      @RequestParam("genre") final String genre,
      @RequestParam("rating") final Integer rating) {
    Movie m = this.getMovie(title, genre);
    m.addRating(rating);
    movSer.addARating(m);
    // hvordan sjekke om filmen allerede fins
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
    if (userSer.getUserbyUsername(username) == null) {
      throw new IllegalArgumentException();
    }
    return userSer.getUserbyUsername(username);
  }

  /** WORKING
   * Get user by username if it exists.
   * http://localhost:8080/movieRating/user?username={username}
   *
   * @param username  a string
   */
  @PostMapping(path = "fullUser")
  public void existingUser(@RequestParam("username") final String username, 
      @RequestParam("password") final String password) {
    userSer.getFullUser(username, password);
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
    User user = new User(username, password);
    this.userSer.registerNewUser(user);
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
   * @param rating a string
   */
  @PutMapping(path = "rateMovie")
  public void ratedMovie(@RequestParam("username") final String username,
      @RequestParam("movieTitle") final String title,
      @RequestParam("movieGenre") final String genre,
      @RequestParam("rating") final Integer rating) {

    User user = this.getUser(username);
    Movie movie = this.getMovie(title, genre);
    this.userSer.rateMovie(user, movie);
  } 
}
