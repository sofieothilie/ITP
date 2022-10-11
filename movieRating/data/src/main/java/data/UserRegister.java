package data;



import java.util.ArrayList;
import java.util.List;

import core.User;


public class UserRegister {
    private List<User> users = new ArrayList<>();
    private UserHandler userHandler = new UserHandler();

    //Må sikre at når en film rates, så oppdateres denne både i user- og movie-fil.

    public void registerNewUser(User user) throws Exception{
        if (userHandler.fileExists()){
            if(existingUser(user.getUsername())){
                throw new IllegalArgumentException("User already exists");
            }
        }
        userHandler.writeUserToRegister(user);
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

    private boolean existingUser(String username){
        if(users.isEmpty()){
            return false;
        }
        for (User user: users){
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    private void updateUserList(){
        this.users = userHandler.readUsersFromRegister();
    }
    
}
