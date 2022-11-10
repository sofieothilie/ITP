package gr2240.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.Movie;
import core.User;
import data.MovieRegister;
import data.UserRegister;

/**
 * Controller class for MovieRatingApplication.
 */

@RestController
@RequestMapping("/movieRating")
public class MovieRatingController {

  private MovieRegisterService movSer;
  private UserRegisterService userSer; 
  private MovieRegister movReg;
  private UserRegister userReg;
  private final static String movFile = "movieRegistry"; //TO BE SET IN UI CONTROLLER LATER
  private final static String userFile = "userRegistry"; //TO BE SET IN UI CONTROLLER LATER

  public MovieRatingController(){
      this.movSer = new MovieRegisterService();
      this.userSer = new UserRegisterService();
      this.movReg = new MovieRegister(movFile);
      this.userReg = new UserRegister(userFile, movFile);
  }

  /**
   * Writes movies to localhost:8080/movieRating/movies.
   * @return List<Movie>
   */
  @RequestMapping(path = "movies")
  public List<Movie> getMovieRegister() {
      return new ArrayList<Movie>(movReg.getAllMovies());
  }

  /**
   * Writes movies to localhost:8080/movieRating/users.
   * @return List<User>
   */
  @RequestMapping(path = "users")
  public List<User> getUserRegister(){
      return new ArrayList<User>(userReg.getAllUsers());
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

