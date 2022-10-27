package data;

import core.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieRegister {

  //TODO: endre til engelsk i Exceptions
  private List<Movie> movies = new ArrayList<>();
  private MovieHandler handler = new MovieHandler();

  public void addMovie(Movie movie) { //Adds a movie to file if it doesn't already exist in file.
    this.movies = updateMovieList();
    if (movieExists(movie)) {
      throw new IllegalArgumentException(">The movie already exists");
    }
    handler.writeMovieToRegister(movie);
  }

  public void updateMovie(Movie movie) { //Updates the movie in the file if it already exists in file.
    this.movies = updateMovieList();
    if (movies.isEmpty()) {
      throw new IllegalArgumentException("No registered movie yet.");
    }
    boolean foundMovie = false;
    for (Movie mov : movies) {
      if (mov.equals(movie)) {
        handler.updateMovieInRegister(movie);
        foundMovie = true;
      }
    }
    if (!foundMovie) {
      throw new IllegalArgumentException(
        "No movie with title " +
        movie.getTitle() +
        " and genre " +
        movie.getGenre()
      );
    }
  }

  public List<Movie> searchGenre(String genre) { //Returns a list of movies which has the given genre.
    this.movies = updateMovieList();
    List<Movie> moviesByGenre = new ArrayList<>();
    for (Movie movie : movies) {
      if (movie.getGenre().equals(genre)) {
        moviesByGenre.add(movie);
      }
    }
    return new ArrayList<>(moviesByGenre);
  }

  public List<Movie> searchMovieTitle(String title) { //Returns a list of movies which has the given title.
    this.movies = updateMovieList();
    List<Movie> moviesByTitle = new ArrayList<>();
    for (Movie movie : movies) {
      if (movie.getTitle().equals(title)) {
        moviesByTitle.add(movie);
      }
    }
    return new ArrayList<>(moviesByTitle);
  }

  public Movie getMovie(String title, String genre) { //Returns a movies which has the given title and genre.
    this.movies = updateMovieList();
    for (Movie movie : movies) {
      if (movie.getTitle().equals(title) && movie.getGenre().equals(genre)) {
        return movie;
      }
    }
    throw new IllegalArgumentException(
      "No movie with title " + title + " and genre " + genre + "."
    );
  }

  private boolean movieExists(Movie movie) { //Returns true if a movie exists in register, if not false.
    this.movies = updateMovieList();
    if (this.movies.isEmpty()) {
      return false;
    }
    for (Movie mov : this.movies) {
      if (mov.equals(movie)) {
        return true;
      }
    }
    return false;
  }

  private List<Movie> updateMovieList() { //Reads from file, return a list
    if (handler.fileExists()) {
      return new ArrayList<>(handler.readMovieAndRatingFromRegister());
    } else {
      return List.of();
    }
  }
}
