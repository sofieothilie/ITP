package gr2240.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.Movie;
import core.User;



/**
 * Controller class for MovieRatingApplication.
 */

@RestController
@RequestMapping("/movieRating")
public class MovieRatingController {

  private final MovieRegisterService movSer;
  private final UserRegisterService userSer; 
  private final static String movFile = "movieRegistry"; //TO BE SET IN UI CONTROLLER LATER
  private final static String userFile = "userRegistry"; //TO BE SET IN UI CONTROLLER LATER


  public MovieRatingController(){
      this.movSer = new MovieRegisterService(movFile);
      this.userSer = new UserRegisterService(userFile, movFile);
  }

  /** WORKING
   * Writes movies to localhost:8080/movieRating/movies.
   * @return List<Movie>
   */
  @RequestMapping(path = "movies")
  public List<Movie> getMovieRegister() {
      return new ArrayList<Movie>(movSer.getAllMovies());
  }

  /** WORKING
   * Writes movies to localhost:8080/movieRating/users.
   * @return List<User>
   */
  @RequestMapping(path = "users")
  public List<User> getUserRegister(){
      return new ArrayList<User>(userSer.getAllUsers());
  }

  /** WORKING
   * Get movie by title and genre if it exists
   * 
   */
  //http://localhost:8080/movieRating/movie?title=title&genre=genre
  @GetMapping(path = "movie")
  public Movie getMovie(@RequestParam("title") String title, @RequestParam("genre") String genre){
    return movSer.getMovieByTitleAndGenre(title, genre);
  }


  /** WORKING
  * Returns a list of movies with desired genre
  * localhost:8080/movieRating/movieGenre?genre=genre
  * @return List<Movie>
  */
  @GetMapping(path = "movieGenre")
  public List<Movie> searchGenre(@RequestParam("genre") String genre){
    return new ArrayList<Movie>(movSer.getMoviesByGenre(genre));
  }

  /** WORKING
  * Returns a list of movies with desired genre
  * localhost:8080/movieRating/movieTitle?title=title 
  * @return movie
  */ 
  @GetMapping(path = "movieTitle")
  public List<Movie> searchTitle(@RequestParam("title") String title) {
    return new ArrayList<Movie>(this.movSer.getMoviesByTitle(title));
  }

  /** WORKING
  * Add movie to rest, void
  * @return movie
  */
  //localhost:8080/movieRating/addMovie?title={title}&genre={genre}
  @PostMapping(path = "addMovie")
  public void addMovie(@RequestParam("title") String title, @RequestParam("genre") String genre) {
    //if (movSer.ableToCreateMovie(title, genre)) {
    Movie m1 = new Movie(title, genre);
    movSer.addAMovie(m1);
    //} //hvordan sjekke om film allerede fins?
    //throw new IllegalArgumentException("Invalid movie");
  }
  
  /** WORKING
  * Add rating to movie in rest
  * @return movie
  */
  //localhost:8080/movieRating/addRating?title={title}&genre={genre}&rating={rating}  
  @PutMapping(path = "addRating")
  public void addRatingToMovie(@RequestParam("title") String title, @RequestParam("genre") String genre, @RequestParam("rating") Integer rating) {
    Movie m = this.getMovie(title, genre);
    m.addRating(rating);
    movSer.addARating(m);
     //hvordan sjekke om filmen allerede fins
  }

  /**
   * Get user by username if it exists
   * @param username
   * @return
   * http://localhost:8080/movieRating/user?username={username}
   */
  @GetMapping(path = "user")
  public User getUser(@RequestParam("username") String username){
    if(userSer.getUserbyUsername(username) == null){
      throw new IllegalArgumentException();
    }
    return userSer.getUserbyUsername(username);
  }

  /** WORKING
   * Creates a new user
   * @param username
   * @param password
   * http://localhost:8080/movieRating/newUser?username={username}&password={password}  
   */
  @PostMapping(path = "newUser")
  public void registerNewUser(@RequestParam("username") String username, @RequestParam("password") String password) {
    User user = new User(username, password);
      this.userSer.registerNewUser(user);
  }

  /** NOT
   * Adds rating for movie
   * @param username
   * @param title
   * @param genre
   * @param rating
   * http://localhost:8080/movieRating/rateMovie?username={username}&movieTitle={title}&genre={genre}&rating={rating}  
   */
  @PutMapping(path = "rateMovie")
  public void rateMovie(@RequestParam("username") String username, @RequestParam("movieTitle") String title, @RequestParam("movieGenre") String genre, @RequestParam("rating") Integer rating){
  
    User user = this.getUser(username);
    Movie movie = this.getMovie(title, genre);
    this.userSer.rateMovie(user, movie);      
  } 
}
