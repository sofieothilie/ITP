package data;



import java.util.ArrayList;
import java.util.List;

import core.Movie;
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

    public static void main(String[] args) {
        User user3 = new User("sofie", "sofie123"); 
        Movie movie = new Movie("Snow", "fantasy");
        user3.rateMovie(movie, 3);

        UserRegister userRegister = new UserRegister();
        userRegister.registerNewUser(user3);
        System.out.println(user3);
        System.out.println(userRegister.getUser(user3.getUsername()));
        System.out.println(user3.equals(userRegister.getUser(user3.getUsername())));
    }
    
}
