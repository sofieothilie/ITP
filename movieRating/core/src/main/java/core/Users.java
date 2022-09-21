package core;



import java.util.ArrayList;
import java.util.List;


public class Users {
    private List<User> users = new ArrayList<>();

    public void registerNewUser(User user) throws Exception{
        if(this.existingUser(user.getUsername(), user.getPassword())){
            throw new IllegalArgumentException("User already exists");
        }
        users.add(user);
    }
    
    public List<User> getUsers(){
        return users;
    }

    public User getUser(String username){
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public void validUser(String username, String password) throws Exception{
        if(userIsEmpty()){
            throw new Exception("User does not exist");
        }
        for(User user : users){
            if(!(user.getUsername().equals(username))){
                throw new Exception("User does not exist");
            }
            if(!(user.getPassword().equals(password))){
                throw new Exception("Wrong password");
            }
        }
    }

    public boolean existingUser(String username, String password){
        if(userIsEmpty()){
            return false;
        }
        for(User user : users){
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean userIsEmpty(){
        if(users.isEmpty()){
            return false;
        }
        return true;
    }
    
}
