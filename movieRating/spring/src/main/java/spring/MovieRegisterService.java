package spring;

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

	public boolean ableToCreateMovie(String title, String genre) {
		return this.validMovie(title, genre);
	}

  public boolean ableToRate(Movie movie, int rating){
      return this.validRating(movie, rating);
  }

  public void addAMovie(Movie movie){
    movReg.addMovie(movie);
  }

  public void addARating(Movie movie){
    movReg.updateMovie(movie);
  }


  private boolean validMovie(String title, String genre) {
      if (title.isEmpty() || !GENRES.contains(genre)) {
        return false;
      }
      return true;
    }

  private boolean validRating(Movie movie, int rating){
      if (rating <= 5 || rating >=1){
          if (!(movie.equals(null))){
              return true;
          }
      }
      return false;
  }
}
