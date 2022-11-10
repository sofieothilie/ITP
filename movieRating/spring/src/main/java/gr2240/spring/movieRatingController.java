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

  /**
   * Writes movies to localhost:8080/movieRating/movies.
   * @return List<Movie>
   */
  @RequestMapping(path = "movies")
  public List<Movie> getMovieRegister() {
      return new ArrayList<Movie>(movSer.getAllMovies());
  }

  /**
   * Writes movies to localhost:8080/movieRating/users.
   * @return List<User>
   */
  @RequestMapping(path = "users")
  public List<User> getUserRegister(){
      return new ArrayList<User>(userSer.getAllUsers());
  }
  /**
   * Get user by username if it exists
   */
  //http://localhost:8080/movieRating/user?username=Pauline
  @GetMapping(path = "user")
  public User getUser(@RequestParam("username") String username){
    if(userSer.getUserbyUsername(username) == null){
      throw new IllegalArgumentException();
    }
    return userSer.getUserbyUsername(username);
  }

  /**
   * Get movie by title if it exists, by title and genre
   */
  //http://localhost:8080/movieRating/movie?title=Titanic&genre=drama
  @GetMapping(path = "movie")
  public Movie getMovie(@RequestParam("title") String title, @RequestParam("genre") String genre){
    if(movSer.getMovie(title, genre) == null){
      throw new IllegalArgumentException();
    }
    return movSer.getMovieByTitleAndGenre(title, genre);
  }






  /**
  * Returns a list of movies with desired genre
  * localhost:8080//movieRating/movieGenre?genre={genre}
  * @return List<Movie>
  */
  @GetMapping(path = "movieGenre")
  public List<Movie> searchGenre(@RequestParam("genre") String genre){
    return new ArrayList<Movie>(this.movSer.getMoviesByGenre(genre));
  }

  /**
  * Returns a list of movies with desired genre
  * @return movie

  * localhost:8080//movieRating/movieTitle?title={title}  */
  public List<Movie> searchTitle(@RequestParam("title") String title) {
    return new ArrayList<Movie>(this.movSer.getMoviesByTitle(title)););
  }

  /**
  * Add movie to rest
  * @return movie
  */
  //localhost:8080//movieRating/addMovie?title={title}&genre={genre}
  @PostMapping(path = "movie")
  public Movie addMovie(@RequestParam("title") String title, 
      @RequestParam("genre") String genre) {
    if (movSer.ableToCreateMovie(title, genre)) {
      return movSer.getMovie(title, genre);
    } //hvordan sjekke om film allerede fins?
    throw new IllegalArgumentException("Invalid movie");
  }
  
  /**
  * Add rating to movie in rest
  * @return movie
  */
  //localhost:8080//movieRating/addRating?movie={movie}&rating={rating}  
  @PutMapping(path = "addRating")
  public void addRatingToMovie(@RequestParam("movie") Movie movie, @RequestParam("rating") Integer rating) {
    if (movSer.ableToRate(movie, rating)){
      //new Movie("heis", "fantasy");
    } //hvordan sjekke om filmen allerede fins
    else {
      throw new IllegalArgumentException("Not able to rate");
    }
  }
}
