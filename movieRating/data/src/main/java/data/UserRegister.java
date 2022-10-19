package data;



import java.util.ArrayList;
import java.util.List;

import core.Movie;
import core.User;


public class UserRegister {
    //TODO: engslk til norsk, kommentarer
    private List<User> users = new ArrayList<>();
    private UserHandler userHandler = new UserHandler();
    MovieRegister movieRegister = new MovieRegister(); //TODO: gjør private

    public void registerNewUser(User newuser){ // registrer ny bruker
        this.users = updateUserList();
        for(User user: users){
            if(user.equals(newuser)){
                throw new IllegalArgumentException("User already exists");
            }
        }
        userHandler.writeUserToRegister(newuser);
    }
    
    public List<User> getUsers(){ // henter ut en liste med brukerne
        if (userHandler.fileExists()){
            return new ArrayList<>(userHandler.readUsersFromRegister());
        }
        return List.of();
    }

    public User getUser(String username){  // henter ut bruker med brukernavn
        this.users = this.updateUserList();
        for (User user: users){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;       
    }

    public void existingUser(String username, String password){ // sjekker om brukeren eksisterer på brukernavn og password
        // TODO: bruker getUser her, found User til getUser
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

    public void updateRatedMovie(User user, Movie movie){ //Updates the user in the file if it already exists
        this.users = updateUserList();
        if(users.isEmpty()){
            throw new IllegalArgumentException("No registered users yet");
        }
        boolean foundUser = false;
        for(User u1: users){ //TODO: bruke equals
            if(u1.getUsername().equals(user.getUsername()) && u1.getPassword().equals(user.getPassword())){
                userHandler.updateRegister(user); // TODO: endre her
                movieRegister.updateMovie(movie);
                foundUser = true;
            }
        }
        if(!foundUser){
            throw new IllegalArgumentException("No user with username: " + user.getUsername());
        }
    }

    private List<User> updateUserList(){ // leser fra filen og returnerer en liste med brukere eller tom liste
        if(userHandler.fileExists()){
            return new ArrayList<>(userHandler.readUsersFromRegister());
        }
        else{
            return List.of();
        }
    }

    public void ableToCreateNewUser(User user){ //TODO: innen øvingen er ferdig skal metoden fjernes, men stå her enn så lenge
        this.users = this.updateUserList();
        for (User alreadyUser : users) {
            if (user.equals(alreadyUser)){
                throw new IllegalArgumentException("Already a user");
            }
            
        }
    }
}
