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
   * Constructor that takes one argument.
   *
   * @param fileName a String
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

  public File getFile() {
    //Gets the name of the file in user home
    return new File(System.getProperty("user.home") + "/" + this.fileName);
  }

  /**
   * writeMovieToRegister method.
   *
   * @param movie a Movie object
   */
  public void writeMovieToRegister(Movie movie) {
    //Writes movie to file with JSON.
    //MovieRegister secures that duplicate movies aren't written to file.
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
   * @param movie a Movie object
   */
  public void updateMovieInRegister(Movie movie) {
    try {
      //Takes in a movie and updates it in the register
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
