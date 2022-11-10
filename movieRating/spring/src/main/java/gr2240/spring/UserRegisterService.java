package gr2240.spring;

import java.util.ArrayList;
import java.util.List;

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
  

    
    
}
