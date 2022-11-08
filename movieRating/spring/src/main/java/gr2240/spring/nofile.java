// package gr2240.spring;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import core.Movie;
// import core.User;
// import data.MovieRegister;

// public class nofile{

// public static final String MOVIERATING_SERVICE_PATH = "spring/movieRating";

// @Autowired
// private movieRatingService movieRatingService;

// @GetMapping
// public MovieRegister getMovieRegister() {
// return movieRatingService.getMovieRegister();
// }

// // get all the movies from file
// @GetMapping
// public List<Movie> getMovies() {
// List<Movie> movies = null;
// return movies;

// }

// // get a movie by title, if there are more movie titles
// @GetMapping(path = "/{title}")
// public Movie getMovieByGenre(@PathVariable("title") String title) {
// Movie m1 = null;
// return m1;
// }

// // get all the users from file
// @GetMapping
// public List<User> getUsers(){
// List<User> users = null;
// return users;
// }

// }
