package gr2240.spring;

import java.util.List;

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

  private MovieRegisterService movSer;
  private UserRegisterService userSer; 

  public MovieRatingController(){
      this.movSer = new MovieRegisterService();
      this.userSer = new UserRegisterService();
  }

  /**
   * Writes movies to localhost:8080/movieRating/movies.
   * @return List<Movie>
   */
  @RequestMapping(path = "movies")
  public Movie getMovieRegister() {
      return new Movie ("Titanic", "fantasy");
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
  public Movie addRatingToMovie(@RequestParam("movie") Movie movie, 
  @RequestParam("rating") Integer rating) {
    if (movSer.ableToRate(movie, rating)){
      return new Movie("heis", "fantasy");
    } //hvordan sjekke om filmen allerede fins
    else {
      throw new IllegalArgumentException("Not able to rate");
    }
  }

  // get all the users from file
  @RequestMapping(path = "getUsers")
  public List<User> getUserRegister(){
      List<User> users = this.getUserRegister();
      return users;
  }
}

