package ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.Movie;
import core.User;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Methods for communication with the server.
 */
public class RemoteMovieRatingAccess {

  private final URI endpointBaseUri;

  private ObjectMapper objectMapper = new ObjectMapper();

  private List<User> userList;
  private List<Movie> movieList;

  public URI resolveUriAccounts(String uri) {
    return endpointBaseUri.resolve(uri);
  }

  /**
   * Constructor for RemoteMovieRatingAcces.
   *
   * @param endpointBaseUri a URI
   */
  public RemoteMovieRatingAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
  }

  /** WORKING
   * Gets all movies from the server and returns them as a list.
   *
   * @return List of movies
   */  
  public List<Movie> getMovieRegister() {
    HttpRequest httpRequest = HttpRequest.newBuilder(endpointBaseUri.resolve("movies/"))
                                            .header("Accept", "application/json")
                                            .GET()
                                            .build();
    try {
      final HttpResponse<String> httpResponse = HttpClient.newBuilder()
                                      .build()
                                      .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      Movie[] movies = objectMapper.readValue(httpResponse.body(), Movie[].class);
      this.movieList = new ArrayList<Movie>(Arrays.asList(movies));  
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new ArrayList<Movie>(this.movieList);
  }

  /**
   * Gets all users from server and returns them as a list.
   *
   * @return List of users
   */
  public List<User> getAllUsers() {
    HttpRequest httpRequest = HttpRequest.newBuilder(endpointBaseUri.resolve("users/"))
                                            .header("Accept", "application/json")
                                            .GET()
                                            .build();
    try {
      final HttpResponse<String> httpResponse = HttpClient.newBuilder()
                                      .build()
                                      .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      User[] users = objectMapper.readValue(httpResponse.body(), User[].class);
      this.userList = new ArrayList<User>(Arrays.asList(users));  
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new ArrayList<User>(this.userList);
  }

  /**
   * Returns a movie from the server.
   *
   * @param title a string
   * @param genre a string
   * @return movie
   */
  public Movie getMovie(String title, String genre) { //movie?title=title&genre=genre
    String getMappingPath = "movie?";
    String t = "title=";
    String g = "genre=";
    String tit = title;
    String gen = genre;
    try {
      HttpRequest request = 
          HttpRequest.newBuilder(resolveUriAccounts(getMappingPath + t + tit + "&" + g + gen))
                     .header("Accept", "application/json")
                     .GET()
                     .build();
      final HttpResponse<String> httpResponse =
          HttpClient.newBuilder()
                     .build()
                     .send(request, HttpResponse.BodyHandlers.ofString());

      return objectMapper.readValue(httpResponse.body(), Movie.class);
      
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Gets movies with the following genre from server.
   *
   * @param genre a string
   * @return a list of movies with genre
   */
  public List<Movie> searchGenre(String genre) { 
    return getMovieRegister().stream().filter(m -> m.getGenre().equals(genre)).toList();
  }

 /**
   * Gets movies with the following title from server.
   *
   * @param title a string
   * @return a list of movies with a spesific title
   */
  public  List<Movie> searchMovieTitle(String title) { 
    return getMovieRegister().stream().filter(m -> m.getTitle().equals(title)).toList();
  }

  /**
   * Adds a movie to Register
   *
   * @param movie a Movie
   */
  public void addMovie(Movie movie){ 
    String getMappingPath = "movies/";
    try {
      String json = objectMapper.writeValueAsString(movie);
      HttpRequest request = HttpRequest.newBuilder(resolveUriAccounts(getMappingPath + movie))
                                       .header("Accept", "application/json")
                                       .header("Content-Type", "application/json")
                                       .POST(BodyPublishers.ofString(json))
                                       .build();
            
      HttpClient.newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }
    catch (IOException | InterruptedException e){
      throw new RuntimeException(e);
    }
  }

  /**
   * Gets a user from server.
   *
   * @param username  a string
   * @return boolean, true if user exists, false if not
   */
  public User getUser(String username) {
    HttpRequest httpRequest = HttpRequest.newBuilder(endpointBaseUri.resolve("users/" + username))
                                            .header("Accept", "application/json")
                                            .GET()
                                            .build();
    try {
      final HttpResponse<String> httpResponse = HttpClient.newBuilder()
                                      .build()
                                      .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      return objectMapper.readValue(httpResponse.body(), User.class);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Throws illegal argument if no such user exists.
   *
   * @param user a User
   */
  public void registerNewUser(User user) throws IllegalArgumentException {
    try {
      String postString = objectMapper.writeValueAsString(user);
      HttpRequest httpRequest = HttpRequest.newBuilder(resolveUriAccounts("users/"+user))
                                            .header("Accept", "application/json")
                                            .header("Content-Type","application/json")
                                            .POST(BodyPublishers.ofString(postString))
                                            .build();

      final HttpResponse<String> httpResponse = HttpClient.newBuilder()
        .build()
        .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      objectMapper.readValue(httpResponse.body(), User.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // public void 

}
