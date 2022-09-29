package core;



import java.util.ArrayList;
import java.util.List;


public class UserRegister {
    private List<User> users = new ArrayList<>();
    private UserHandling userHandling = new UserHandling();

    //Må sikre at når en film rates, så oppdateres denne både i user- og movie-fil.

    public void registerNewUser(User user) throws Exception{
        //oppdatere listen, også sjekke om brukernavn fins fra før, bruk getUser
        //hvis ikke, skriv til fil

        if (userHandling.fileExists()){
            if(this.existingUser(user.getUsername(), user.getPassword())){
                throw new IllegalArgumentException("User already exists");
            }

        }
        userHandling.writeUserToRegister(user);
    }
    
    public List<User> getUsers(){
        //lese fra register, returnere kopi av liste
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
        //Se på existing user og denne, og slå de sammen.
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
        //fjerne
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
        //vurdere om denne er nødvendig, evt om den kan implementeres på en annen måte
        updateUserList();
        if(users.isEmpty()){
            return true;
        }
        return false;
    }

    private void updateUserList(){
        //fjerne fileExists delen.
        if (userHandling.fileExists()){
            this.users = userHandling.readUsersFromRegister();
        }
    }
    
}
