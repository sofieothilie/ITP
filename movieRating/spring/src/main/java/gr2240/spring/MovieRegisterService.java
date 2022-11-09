package gr2240.spring;

import java.util.Arrays;
import java.util.List;

import core.Movie;

public class MovieRegisterService {

    private static List<String> GENRES = Arrays.asList("action",
      "comedy", "drama",
      "fantasy", "horror",
      "mystery", "romance", "thriller");

    public Movie getMovie(String title, String genre) {
        return null;
    }

	public boolean ableToCreateMovie(String title, String genre) {
		return this.validMovie(title, genre);
	}

    public boolean ableToRate(Movie movie, int rating){
        return this.validRating(movie, rating);
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