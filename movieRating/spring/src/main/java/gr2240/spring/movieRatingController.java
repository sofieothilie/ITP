package gr2240.spring;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.Movie;
import data.MovieRegister;

/**
 * Controller class for MovieRatingApplication.
 */

@RestController
@RequestMapping("/movieRating")
public class MovieRatingController {
    /**
     * Fields used in class.
     */
    private MovieRegister movieRegister = new MovieRegister("movieRegistry");

    /**
     * Writes movies to localhost:8080/movieRating/movies.
     * @return List<Movie>
     */
    @RequestMapping(path = "movies")
    public List<Movie> getMovieRegister() {
        return movieRegister.updateMovieList();
    }

    // @PostMapping(path = "create-movie",
    // consumes = MediaType.APPLICATION_JSON_VALUE)
    // public void createAdminUser(@RequestBody Movie newMovie) {
    //   movieRegister.addMovie(newMovie);
    // }

    // @PutMapping(path = "users")
    // public List<Movie> getUserRegister() {
    //     return movieRegister.updateMovieList();
    // }

    // // get all the users from file
    // @GetMapping(path = "getMovies")
    // public List<Movie> getUsers(){
    //     List<Movie> users = null;
    //     return users;
    // }
}

