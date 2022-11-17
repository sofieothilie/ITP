package ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.Movie;
import core.User;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
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

  public URI resolveUri(String uri) {
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

  /**
   * WORKING
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
      final HttpResponse<String> response = HttpClient.newBuilder()
          .build()
          .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      this.checkStatus(response);
      Movie[] movies = objectMapper.readValue(response.body(), Movie[].class);
      this.movieList = new ArrayList<Movie>(Arrays.asList(movies));
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
    return new ArrayList<Movie>(this.movieList);
  }
  /**
   * Initialize test mode.
   *
   * @param movieFilename a string
   * @param userFilename a string
   */

  public void initializeTestMode(String movieFilename, String userFilename) {
    try {
      HttpRequest request = HttpRequest
          .newBuilder(endpointBaseUri.resolve("test/" + movieFilename + "&" + userFilename))
          .header("Accept", "application/json")
          .GET()
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      this.checkStatus(response);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
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
      final HttpResponse<String> response = HttpClient.newBuilder()
          .build()
          .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      this.checkStatus(response);
      User[] users = objectMapper.readValue(response.body(), User[].class);
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
  public Movie getMovie(String title, String genre) { 
    try {
      HttpRequest request = HttpRequest
          .newBuilder(endpointBaseUri.resolve("movies/" + this.nameConverter(title) + "&" + genre))
          .header("Accept", "application/json")
          .GET()
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      this.checkStatus(response);
      return objectMapper.readValue(response.body(), Movie.class);
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
    List<Movie> moviesFound = getMovieRegister()
        .stream().filter(m -> m.getGenre().equals(genre)).toList();
    if (moviesFound.size() < 1) {
      throw new IllegalArgumentException("No movies with genre " + genre);
    }
    return moviesFound;
  }

  /**
   * Gets movies with the following title from server.
   *
   * @param title a string
   * @return a list of movies with a spesific title
   */
  public List<Movie> searchMovieTitle(String title) {
    List<Movie> moviesFound =  getMovieRegister()
        .stream().filter(m -> m.getTitle().equals(title)).toList();
    if (moviesFound.size() < 1) {
      throw new IllegalArgumentException("No movies with title " + title);
    }
    return moviesFound;
  }

  /**
   * Adds a movie to Register.
   *
   * @param movie a Movie
   */
  public void addMovie(Movie movie) {
    String getMappingPath = "movies/add";
    try {
      String json = objectMapper.writeValueAsString(movie);
      HttpRequest request = HttpRequest.newBuilder(resolveUri(getMappingPath))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(json))
          .build();

      HttpResponse<String> response = HttpClient.newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      this.checkStatus(response);

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Gets a user from server.
   *
   * @param username a string
   * @return a User
   */
  public User getUser(String username) {
    HttpRequest httpRequest = HttpRequest.newBuilder(endpointBaseUri.resolve("users/" + username))
        .header("Accept", "application/json")
        .GET()
        .build();
    try {
      final HttpResponse<String> response = HttpClient.newBuilder()
          .build()
          .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      this.checkStatus(response);
      return objectMapper.readValue(response.body(), User.class);
    } catch (IllegalArgumentException exception) {
      throw new IllegalArgumentException(exception.getMessage());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
  * Throws if user already exists, invalid password or invalid file.
  *
  * @param username a string
  * @param password a string
  */
  public void existingUser(String username, String password) {
    List<User> usersFound = this.getAllUsers().stream()
        .filter(user -> user.getUsername().equals(username))
        .toList();
    if (usersFound.size() == 0) {
      throw new IllegalArgumentException("User not found");
    }
    usersFound.stream()
        .filter(user -> user.getPassword().equals(password))
        .toList();
    if (usersFound.size() == 0) {
      throw new IllegalArgumentException("Incorrect password");
    } else if (usersFound.size() > 1) {
      throw new IllegalStateException(
        "More than one user with same username and  password in file. Unable to fetch user.");
    }
  }

  /**
   * Registers a new user on the server.
   * Throws IllegalArgumentException if user exists.
   *
   * @param user a User
   */
  public void registerNewUser(User user) {
    try {
      String postString = objectMapper.writeValueAsString(user);
      HttpRequest httpRequest = HttpRequest.newBuilder(resolveUri("users/add"))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .POST(BodyPublishers.ofString(postString))
          .build();

      final HttpResponse<String> response = HttpClient.newBuilder()
          .build()
          .send(httpRequest, HttpResponse.BodyHandlers.ofString());

      this.checkStatus(response);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * Updates both movie and user on server.
   *
   * @param user a user
   * @param movie a movie
   */
  public void updateMovieAndUser(User user, Movie movie) {
    updateUser(user);
    updateMovie(movie);

  }

  /**
   * Updates a user on server.
   *
   * @param user a User
   */
  public void updateUser(User user) {
    try {
      String postString = objectMapper.writeValueAsString(user);
      HttpRequest httpRequest = HttpRequest.newBuilder(resolveUri("users/update"))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .PUT(BodyPublishers.ofString(postString))
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder()
          .build()
          .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      this.checkStatus(response);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Updates a movie on server.
   *
   * @param movie a Movie
   */
  public void updateMovie(Movie movie) {
    try {
      String postString = objectMapper.writeValueAsString(movie);
      HttpRequest httpRequest = HttpRequest.newBuilder(resolveUri("movies/update"))
          .header("Accept", "application/json")
          .header("Content-Type", "application/json")
          .PUT(BodyPublishers.ofString(postString))
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder()
          .build()
          .send(httpRequest, HttpResponse.BodyHandlers.ofString());
      this.checkStatus(response);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Throws IllegalArgumentException if statuscode is not 200.
   *
   * @param response a httpresponse
   */
  private void checkStatus(HttpResponse<String> response) {
    if (response.statusCode() != 200) {
      throw new IllegalArgumentException(response.body());
    }
  }

  /**
   * Converts a string with spaces to a string with %20.
   *
   * @param name a string
   */
  private String nameConverter(String name) {
    if (name.contains(" ")) {
      return name.replaceAll(" ", "%20");
    }
    return name;
  }
}
