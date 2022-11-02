package data;

import core.Movie;
import java.util.ArrayList;
import java.util.List;

/**
 * class that handles a register of Movie objects.
 */
public class MovieRegister {

  private List<Movie> movies = new ArrayList<>();
  private MovieHandler movieHandler;

  public MovieRegister(String fileName) {
    //Constructor for movie register. Generates a movie handler object.
    this.movieHandler = new MovieHandler(fileName);
  }

  /**
   * Method that adds a movie object to the register.
   *
   * @param movie the movie object to add
   */
  public void addMovie(Movie movie) { //Adds a movie to file if it doesn't already exist in file.
    this.movies = updateMovieList();
    if (movieExists(movie)) {
      throw new IllegalArgumentException("The movie already exists");
    }
    movieHandler.writeMovieToRegister(movie);
  }

  /**
   * Method that updates a Movie object in the file if it exists already.
   *
   * @param movie the Movie object to update
   */
  public void updateMovie(Movie movie) { 
    this.movies = updateMovieList();
    if (movies.isEmpty()) {
      throw new IllegalArgumentException("No registered movie yet.");
    }
    boolean foundMovie = false;
    for (Movie mov : movies) {
      if (mov.equals(movie)) {
        movieHandler.updateMovieInRegister(movie);
        foundMovie = true;
      }
    }
    if (!foundMovie) {
      throw new IllegalArgumentException("No movie with title " 
        + movie.getTitle() 
        + " and genre " 
        + movie.getGenre());
    }
  }

  /**
   * Method that searches in the register for movies with a given genre.
   *
   * @param genre a string, the genre to search for
   * @return a list of Movie objects with the given genre
   */
  public List<Movie> searchGenre(String genre) { 
    this.movies = updateMovieList();
    List<Movie> moviesByGenre = new ArrayList<>();
    for (Movie movie : movies) {
      if (movie.getGenre().equals(genre)) {
        moviesByGenre.add(movie);
      }
    }
    return new ArrayList<>(moviesByGenre);
  }

  /**
   * Method that searches in the register for Movie objects with given title.
   *
   * @param title a string, the title to search for
   * @return a list of Movie objects with given title
   */
  public List<Movie> searchMovieTitle(String title) { 
    this.movies = updateMovieList();
    List<Movie> moviesByTitle = new ArrayList<>();
    for (Movie movie : movies) {
      if (movie.getTitle().equals(title)) {
        moviesByTitle.add(movie);
      }
    }
    return new ArrayList<>(moviesByTitle);
  }

  /**
   * Method that returns a movie with given title and genre.
   *
   * @param title a string, the title of the movie
   * @param genre a string, the genre of the movie
   * @return a Movie object with given title and genre, if it exists
   */
  public Movie getMovie(String title, String genre) { 
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

  /**
   * Method that checks if a movie exists in register.
   *
   * @param movie the Movie object to check
   * @return true if the movie exists
   */
  private boolean movieExists(Movie movie) { 
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

  /**
   * Method that updates the list of movies in the register by reading from a file.
   *
   * @return a list of all movie objects read from the file
   */
  private List<Movie> updateMovieList() { //Reads from file, return a list
    if (movieHandler.fileExists()) {
      return new ArrayList<>(movieHandler.readMovieAndRatingFromRegister());
    } else {
      return List.of();
    }
  }
}
