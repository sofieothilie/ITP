package restAPI;

import java.util.ArrayList;
import java.util.List;

import core.Movie;
import core.User;
import data.UserRegister;

public class UserRegisterService {

  private UserRegister userReg;

  public UserRegisterService(String userFile, String movieFile){
    this.userReg = new UserRegister(userFile, movieFile);
  }

  public List<User> getAllUsers(){
    return new ArrayList<User>(userReg.getAllUsers());
  }

  public User getUserbyUsername(String username){
    return userReg.getUser(username);
  }

  public void registerNewUser(User user){
    this.userReg.registerNewUser(user);
  }

  public void rateMovie(User user, Movie movie){
    userReg.updateRatedMovie(user, movie);
  }

  public void getFullUser(String username, String password) {
    userReg.existingUser(username, password);
  }


  

    
    
}
