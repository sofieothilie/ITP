package data;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import core.Movie;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 
 * class that writes and reads Movie objects to/from file.
 */
public class MovieHandler {
  private final String fileName;

  /**
   * Constructor that takes in a filename.
   *
   * @param fileName a string, only of letters and numbers
   */
  public MovieHandler(String fileName) {
    //Sets a filename which is to be used for the instantiated object. 
    //Can only contain letters and numbers.
    if (fileName.matches("[a-zA-Z0-9]+")) {
      this.fileName = fileName;
    } else {
      throw new IllegalArgumentException(
        "fileName can only contain letters and numbers."
      );
    }
    //"MovieRegister.json"
  }

  /**
   * Method that returns the file in user home.
   *
   * @return a file
   */
  public File getFile() {
    //Gets the name of the file in user home
    return new File(System.getProperty("user.home") + "/" + this.fileName);
  }

  /**
   * Meyhod that writes movie to file with JSON.
   * MovieRegister secures that dublicate movies aren't written to file
   *
   * @param movie the movie object to write to file
   */
  public void writeMovieToRegister(Movie movie) {
    try {
      List<Movie> movies = new ArrayList<Movie>();
      if (this.fileExists()) {
        movies = readMovieAndRatingFromRegister();
      }
      movies.add(movie);
      ObjectMapper objectMapper = new ObjectMapper();
      ObjectWriter objectWriter = objectMapper.writer(
        new DefaultPrettyPrinter()
      );
      objectWriter.writeValue(getFile(), movies);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error: " + e);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error: " + e);
    }
  }

  /**
   * A method that updates a movie object in the file.
   *
   * @param movie the movie object to update
   * @throws IllegalStateException if movie can't be updated
   * @throws IOException if something goes wrong with the file handeling
   */
  public void updateMovieInRegister(Movie movie) {
    try {
      List<Movie> movieList = updateMovieListWithNewMovie(movie);
      if (movieList.contains(movie)) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(
          new DefaultPrettyPrinter()
        );
        objectWriter.writeValue(getFile(), movieList);
      } else {
        throw new IllegalStateException("Couldnt update movie in register.");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException(
        "An IOException occoured with the file."
      );
    }
  }

  /**
   * Method that updates a movie and generates a new list of all the movies with the new update.
   *
   * @param movie the updated movie
   * @return a list of all movies
   */
  private List<Movie> updateMovieListWithNewMovie(Movie movie) {
    //Generates a list of all movie objects in file:
    List<Movie> movieList = this.readMovieAndRatingFromRegister();
    Movie oldmovietoberemoved = null;
    // Writes all previous movies and the new updates movie the list:
    for (Movie oldMovie : movieList) {
      if (oldMovie.equals(movie)) {
        oldmovietoberemoved = oldMovie;
      }
    }
    if (oldmovietoberemoved != null) {
      movieList.remove(oldmovietoberemoved);
      movieList.add(movie);
    }
    return new ArrayList<>(movieList);
  }

  /**
   * Method that reads a movie and rating from a file.
   *
   * @return a list of the Movies read from file
   */
  public List<Movie> readMovieAndRatingFromRegister() {
    //Reads from file and generates a list of movies based on it.
    try {
      // create object mapper instance
      ObjectMapper mapper = new ObjectMapper();

      // convert JSON array to list of movies
      List<Movie> movies = Arrays.asList(
          mapper.readValue(getFile(), Movie[].class)
      );
      return new ArrayList<>(movies);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error: " + e);
    }
  }

  /**
   * A method that checks if a file exists.
   *
   * @return true if the file exists
   */
  public Boolean fileExists() { //Chekcs if the file exists
    File f = new File(getFile().getAbsolutePath());
    if (f.isFile()) {
      return true;
    } else {
      return false;
    }
  }
}
