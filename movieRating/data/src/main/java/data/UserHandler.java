package data;

import core.Movie;
import core.User;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;

public class UserHandler {

    public static final String SAVE_FOLDER = "/movieRating/data/src/main/java/data/";
    public static final String fileName = "UserRegister.json";

    private static File getFile(){
        //Returns the file
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        return new File(path + SAVE_FOLDER + fileName);
    }

    public void writeUserToRegister(User user){
        //Writes user to file with JSON.
        //UserRegister secures that duplicate user aren't written to file.
        try {
            List<User> users = new ArrayList<User>();
            if (this.fileExists()){
                users = readUsersFromRegister();
            }
            users.add(user);
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter()); 
            objectWriter.writeValue(getFile(), users);
        }
        catch (IOException e){
            throw new IllegalArgumentException("Error: " + e);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error: " + e);
        }   
    }

    public List<User> readUsersFromRegister(){
        //Reads from file and generates a list of users based on it.
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON array to list of users
            List<User> users = Arrays.asList(mapper.readValue(getFile(), User[].class));
            return new ArrayList<>(users);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error: " + e);
        }
    }

    public void updateRegister(User user){
        //Takes in a user and a rating and updates it in the register

        //Generates a list of all user objects in file:
        List<User> userList = this.readUsersFromRegister();

        //Writes all previous users and the new updates user to file:
        if (!userList.contains(user)){
            throw new IllegalArgumentException("no such user");
        }
        else{
            for (User oldUser : userList) {
                if (oldUser.getUsername().equals(user.getUsername()) && oldUser.getPassword().equals(user.getPassword())){
                    userList.remove(oldUser);
                    userList.add(user);
                }    
            }
        }
    }

    public boolean fileExists(){
        File f = new File(getFile().getAbsolutePath());
        if (f.isFile()){
            return true;
        }
        else{
            return false;
        }
    }

}
