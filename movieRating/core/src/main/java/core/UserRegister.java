package core;



import java.util.ArrayList;
import java.util.List;


public class UserRegister {
    private List<User> users = new ArrayList<>();
    private UserHandling userHandling = new UserHandling();

    public void registerNewUser(User user) throws Exception{
        if (userHandling.fileExists()){
            if(this.existingUser(user.getUsername(), user.getPassword())){
                throw new IllegalArgumentException("User already exists");
            }

        }
        userHandling.writeUserToRegister(user);
    }
    
    public List<User> getUsers(){
        if (userHandling.fileExists()){
            return userHandling.readUsersFromRegister();
        }
        return List.of();
    }

    public User getUser(String username){
        updateUserList();
        if (!userIsEmpty()){
            for(User user : users){
                if(user.getUsername().equals(username)){
                    return user;
                }
            }
        }
        return null;
    }

    public void validUser(String username, String password) throws Exception{
        updateUserList();
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
        updateUserList();
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
        updateUserList();
        if(users.isEmpty()){
            return true;
        }
        return false;
    }

    private void updateUserList(){
        //Denne metoden skal brukes til å oppdatere listen av brukere.
        //Blir implementert når filhåndering er fullt implementert.
    }
    
}
