package data;

import core.Movie;
import core.User;
import java.util.ArrayList;
import java.util.List;

public class UserRegister {

  //TODO: engslk til norsk, kommentarer
  private List<User> users = new ArrayList<>();
  private UserHandler userHandler;
  private MovieRegister movieRegister;

  public UserRegister(String userFileName, String movieFileName) {
    //Constructor for user register. Generates a movie and user handler object.
    this.userHandler = new UserHandler(userFileName);
    this.movieRegister = new MovieRegister(movieFileName);
  }

  public void registerNewUser(User newuser) { // register new user
    this.users = updateUserList();
    for (User user : users) {
      if (user.equals(newuser)) {
        throw new IllegalArgumentException("User already exists");
      }
    }
    userHandler.writeUserToRegister(newuser);
  }

  public List<User> getUsers() { // retrieves a list of users
    if (userHandler.fileExists()) {
      return new ArrayList<>(userHandler.readUsersFromRegister());
    }
    return List.of();
  }

  public User getUser(String username) { // retrieves user with username
    this.users = this.updateUserList();
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null;
  }

  public void existingUser(String username, String password) { // checks whether the user exists on username and password
    User foundUser = getUser(username);

    if (foundUser == null) {
      throw new IllegalArgumentException("User not found");
    }
    if (!foundUser.getPassword().equals(password)) {
      throw new IllegalArgumentException("Invalid password.");
    }
  }

  public void updateRatedMovie(User user, Movie movie) { //Updates the user in the file if it already exists
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
        "No user with username: " + user.getUsername()
      );
    }
  }

  private List<User> updateUserList() { // reads from the file and returns a list of users or empty list
    if (userHandler.fileExists()) {
      return new ArrayList<>(userHandler.readUsersFromRegister());
    } else {
      return List.of();
    }
  }

  public void ableToCreateNewUser(User user) { //TODO: by the time the exercise is finished, the method must be removed, but remain here for the time being
    this.users = this.updateUserList();
    for (User alreadyUser : users) {
      if (user.equals(alreadyUser)) {
        throw new IllegalArgumentException("Already a user");
      }
    }
  }
}
