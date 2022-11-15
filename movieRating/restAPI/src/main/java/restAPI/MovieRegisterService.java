package restapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.Movie;
import data.MovieRegister;

public class MovieRegisterService {

  private MovieRegister movReg;

  private static List<String> GENRES = Arrays.asList("action",
    "comedy", "drama",
    "fantasy", "horror",
    "mystery", "romance", "thriller");

  public MovieRegisterService(String movieFile){
      this.movReg = new MovieRegister(movieFile);
  }

  public List<Movie> getAllMovies(){
    return new ArrayList<Movie>(movReg.getAllMovies());
  }

  public List<Movie> getMoviesByGenre(String genre){
    return new ArrayList<Movie>(movReg.searchGenre(genre));
  }

  public List<Movie> getMoviesByTitle(String title){
    return new ArrayList<Movie>(movReg.searchMovieTitle(title));
  }

  public Movie getMovieByTitleAndGenre(String title, String genre){
    return movReg.getMovie(title, genre);
  }

  public void addMovie(String title, String genre){
    movReg.addMovie(title, genre);
  }
}
