package data;



import java.util.ArrayList;
import java.util.List;

import core.User;


public class UserRegister {
    private List<User> users = new ArrayList<>();
    private UserHandler userHandler = new UserHandler();

    //Må sikre at når en film rates, så oppdateres denne både i user- og movie-fil.

    public void registerNewUser(User newuser){
        if (userHandler.fileExists()){
            for(User user: users){
                if(user.getUsername().equals(newuser.getUsername())){
                    throw new IllegalArgumentException("User already exists");
                }
            }
        }
        userHandler.writeUserToRegister(newuser);
    }
    
    public List<User> getUsers(){
        if (userHandler.fileExists()){
            return new ArrayList<>(userHandler.readUsersFromRegister());
        }
        return List.of();
    }

    public User getUser(String username){
        updateUserList();
        if(users.isEmpty()){
            throw new IllegalArgumentException("No users in register");
        }
        for (User user: users){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;      
    }

    public void existingUser(String username, String password){
        // if(users.isEmpty()){
        //     throw new IllegalArgumentException("User register is empty");
        // }
        User foundUser = null;
        for (User user: users){
            if(user.getUsername().equals(username)){
                foundUser = user;
            }
        }
        if(foundUser == null){
            throw new IllegalArgumentException("User not found");
        }
        if(! foundUser.getPassword().equals(password)){
            throw new IllegalArgumentException("Invalid password.");
        }      
    }

    private void updateUserList(){
        this.users = userHandler.readUsersFromRegister();
    }
    
}
