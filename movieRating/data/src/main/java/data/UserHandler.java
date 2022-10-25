package data;

import core.User;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;

public class UserHandler {

    public static final String SAVE_FOLDER = "/movieRating/data/src/main/java/data/";
    public static final String fileName = "UserRegister.json";

    //TODO: add System.getProperty("user.home")
    public File getFile(){ //TODO: try to fix Path
        //Returns file
        //String path = Paths.get(".").toAbsolutePath().normalize().toString();
        //return new File(path + SAVE_FOLDER + fileName);
        return new File(fileName);
    }

    public void writeUserToRegister(User user){
        //Writes user to file with JSON.
        //UserRegister secures that duplicate user aren't written to file.
        try {
            List<User> users = new ArrayList<User>();
            if (this.fileExists()){
                users = readUsersFromRegister();
            }

            if (user.getRatedMovies().isEmpty()){
                throw new IllegalArgumentException("Cannot add user to register before a movie has been rated.");
                
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
        try {
            //Takes in a user and a rating and updates it in the register
            //Generates a list of all user objects in file:
            User oldUserToRemove = this.userExists(user);
            List<User> userList = this.readUsersFromRegister();
            userList.remove(oldUserToRemove);
            userList.add(user);

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter()); 
            objectWriter.writeValue(getFile(), userList);

        } catch (JsonGenerationException e) {
            throw new IllegalStateException("JsonGeneration Exception");
        } catch (JsonMappingException e) {
            throw new IllegalStateException("JsonMapping Exception");
        } catch (IOException e) {
            throw new IllegalStateException("IOException Exception");
        }

    }

    private User userExists(User user){
        //Takes in a user and a rating and updates it in the register

        //Generates a list of all user objects in file:
        List<User> userList = this.readUsersFromRegister();

        //Writes all previous users and the new updates user to file:
        User oldUserToRemove = null;
        for (User oldUser : userList) {
            if (oldUser.equals(user)){
                oldUserToRemove = oldUser;
            }    
        }
        if (oldUserToRemove == null){
            throw new IllegalArgumentException("no such user");
        }
        return oldUserToRemove;

    }

    public boolean fileExists(){ // check if the file exists
        File f = new File(getFile().getAbsolutePath());
        if (f.isFile()){
            return true;
        }
        else{
            return false;
        }
    }
}