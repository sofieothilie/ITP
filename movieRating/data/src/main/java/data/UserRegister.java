package data;



import java.util.ArrayList;
import java.util.List;

import core.Movie;
import core.User;


public class UserRegister {
    private List<User> users = new ArrayList<>();
    private UserHandler userHandler = new UserHandler();
    MovieRegister movieRegister = new MovieRegister();

    //Må sikre at når en film rates, så oppdateres denne både i user- og movie-fil.

    public void registerNewUser(User newuser){
        this.users = updateUserList();
        for(User user: users){
            if(user.equals(newuser)){
                throw new IllegalArgumentException("User already exists");
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
        this.users = this.updateUserList();
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
        User foundUser = null;
        this.users = this.updateUserList();
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

    public void updateRatedMovie(User user, Movie movie){
        //Updates the user in the file if it already exists
        this.users = updateUserList();
        if(users.isEmpty()){
            throw new IllegalArgumentException("No registered users yet");
        }
        boolean foundUser = false;
        for(User u1: users){
            if(u1.getUsername().equals(user.getUsername()) && u1.getPassword().equals(user.getPassword())){
                userHandler.updateRegister(user);
                movieRegister.updateMovie(movie);
                foundUser = true;
            }
        }
        if(!foundUser){
            throw new IllegalArgumentException("No user with username: " + user.getUsername());
        }
    }

    private List<User> updateUserList(){
        if(userHandler.fileExists()){
            return new ArrayList<>(userHandler.readUsersFromRegister());
        }
        else{
            return List.of();
        }
    }
}
