package data;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import core.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * class that writes and reads User objects to/from file.
 */
public class UserHandler {

  private final String fileName;

  /**
   * Constructor that takes in the name of the file.
   *
   * @param fileName a string filename
   */
  public UserHandler(String fileName) {
    //Sets a filename which is to be used for the instantiated object.
    // Can only contain letters and numbers.
    if (fileName.matches("[a-zA-Z0-9]+")) {
      this.fileName = fileName;
    } else {
      throw new IllegalArgumentException(
        "fileName can only contain letters and numbers."
      );
    }
  }

  /**
   * Method that returns the file in user home.
   *
   * @return a file with the final filename parameter
   */
  public File getFile() {
    //Gets the name of the file in user home
    return new File(System.getProperty("user.home") + "/" + this.fileName);
  }

  /**
   * Method that writes a user to the user register and JSON file.
   * secures that dublicate users aren't written to file
   *
   * @param user the User object to write to register
   */
  public void writeUserToRegister(User user) {
    try {
      List<User> users = new ArrayList<User>();
      if (this.fileExists()) {
        users = readUsersFromRegister();
      }
      users.add(user);
      ObjectMapper objectMapper = new ObjectMapper();
      ObjectWriter objectWriter = objectMapper.writer(
        new DefaultPrettyPrinter()
      );

      objectWriter.writeValue(getFile(), users);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error: " + e);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error: " + e);
    }
  }

  /**
   * Method that reads from file and generates a list of users.
   *
   * @return the list of User objects read from file
   */
  public List<User> readUsersFromRegister() {
    try {
      // create object mapper instance
      ObjectMapper mapper = new ObjectMapper();

      // convert JSON array to list of users
      List<User> users = Arrays.asList(
          mapper.readValue(getFile(), User[].class)
      );
      return new ArrayList<>(users);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error: " + e);
    }
  }

  /**
   * Method that updates a user in the register if it already exists.
   *
   * @param user the User object to update
   */
  public void updateRegister(User user) {
    try {
      //Generates a list of all user objects in file:
      User oldUserToRemove = this.userExists(user);
      List<User> userList = this.readUsersFromRegister();
      userList.remove(oldUserToRemove);
      userList.add(user);

      ObjectMapper objectMapper = new ObjectMapper();
      ObjectWriter objectWriter = objectMapper.writer(
        new DefaultPrettyPrinter()
      );
      objectWriter.writeValue(getFile(), userList);
    } catch (JsonGenerationException e) {
      throw new IllegalStateException("JsonGeneration Exception");
    } catch (JsonMappingException e) {
      throw new IllegalStateException("JsonMapping Exception");
    } catch (IOException e) {
      throw new IllegalStateException("IOException Exception");
    }
  }

  /**
   * Private method that checks if a User exists.
   *
   * @param user the User object to check
   * @return the User if it exists
   */
  private User userExists(User user) {
    List<User> userList = this.readUsersFromRegister();

    //Writes all previous users and the new updates user to file:
    User oldUserToRemove = null;
    for (User oldUser : userList) {
      if (oldUser.equals(user)) {
        oldUserToRemove = oldUser;
      }
    }
    if (oldUserToRemove == null) {
      throw new IllegalArgumentException("no such user");
    }
    return oldUserToRemove;
  }

  /**
   * Method that checks if a file exists.
   *
   * @return true if the file exists
   */
  public boolean fileExists() { 
    File f = new File(getFile().getAbsolutePath());
    if (f.isFile()) {
      return true;
    } else {
      return false;
    }
  }
}
