package data;

import core.Movie;
import core.User;
import java.util.ArrayList;
import java.util.List;

/**
 * class that handles a register of User objects.
 */
public class UserRegister {
  private List<User> users = new ArrayList<>();
  private UserHandler userHandler;
  private MovieRegister movieRegister;

  /**
   * Constructor that takes a user filename and a movie filename.
   * Generates a movie and user handler object
   *
   * @param userFileName a string
   * @param movieFileName a string
   */
  public UserRegister(String userFileName, String movieFileName) {
    this.userHandler = new UserHandler(userFileName);
    this.movieRegister = new MovieRegister(movieFileName);
  }

  /**
   * Method that registers a new user to the register.
   *
   * @param newuser the User object to register
   */
  public void registerNewUser(User newuser) { 
    this.users = updateUserList();
    for (User user : users) {
      if (user.equals(newuser)) {
        throw new IllegalArgumentException("User already exists");
      }
    }
    userHandler.writeUserToRegister(newuser);
  }

  /**
   * Method that retrieves a ist of users.
   *
   * @return a list of User objects
   */
  public List<User> getUsers() { 
    if (userHandler.fileExists()) {
      return new ArrayList<>(userHandler.readUsersFromRegister());
    }
    return List.of();
  }

  /**
   * A method that gets a user with given username.
   *
   * @param username a string, the username of the User object to get
   * @return the User object with given username if it exists
   */
  public User getUser(String username) { 
    this.users = this.updateUserList();
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null;
  }

  /**
   * Method that checks whether a user exists on username and password.
   *
   * @param username a string, the username
   * @param password a string, the password
   */
  public void existingUser(String username, String password) { 
    User foundUser = getUser(username);

    if (foundUser == null) {
      throw new IllegalArgumentException("User not found");
    }
    if (!foundUser.getPassword().equals(password)) {
      throw new IllegalArgumentException("Invalid password.");
    }
  }

  /**
   * Method that updates a user in the file if it already exists.
   *
   * @param user the User object
   * @param movie the movie object to update with
   */
  public void updateRatedMovie(User user, Movie movie) { 
    this.users = updateUserList();
    if (users.isEmpty()) {
      throw new IllegalArgumentException("No registered users yet");
    }
    boolean foundUser = false;
    for (User u1 : this.users) {
      if (u1.equals(user)) {
        userHandler.updateRegister(user);
        movieRegister.updateMovie(movie);
        foundUser = true;
      }
    }
    if (!foundUser) {
      throw new IllegalArgumentException(
          "No user with username: " + user.getUsername());
    }
  }

  /**
   * Private method that reads from the file and returns a list of users, or an empty list.
   *
   * @return a list of Users read from the file
   */
  private List<User> updateUserList() { 
    if (userHandler.fileExists()) {
      return new ArrayList<>(userHandler.readUsersFromRegister());
    } else {
      return List.of();
    }
  }
}
