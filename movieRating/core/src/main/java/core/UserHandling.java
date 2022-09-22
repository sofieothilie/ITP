package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class UserHandling {

    List<User> users = new ArrayList<>();

    public void writeUserToRegister(User user){
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("userRegister.txt", true)));
            writer.println(user.toString());
           
            writer.flush();
            writer.close();
        }
        catch (IOException e){
            throw new IllegalArgumentException("Error: " + e);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error: " + e);
        }
    }

    public List<User> readUsersFromRegister(){
        try {
            Scanner scanner = new Scanner(new File("userRegister.txt"));
            
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                String username = parts[0];
                String password = parts[1];
                User user = new User(username, password);
                if (parts.length > 2){
                    String MovieAndRatings = parts[2];
                    String[] movieAndRatings = MovieAndRatings.split(",");

                    for(String movieAndRating : movieAndRatings){
                        String[] movieAndRatingParts = movieAndRating.split(":");
                        String movieTitle = movieAndRatingParts[0];
                        String genre = movieAndRatingParts[1];
                        String rating = movieAndRatingParts[2];
                        user.rateMovie(new Movie(movieTitle, genre), Integer.parseInt(rating));
                    }
                }
                users.add(user);

            }
            scanner.close();
            return users;   
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error: " + e);
        }
    }

    public boolean fileExists(){
        File f = new File("userRegister.txt");
        if (f.isFile()){
           return true;
        }
        return false;
    }

    public static void main(String[] args) {
        User user = new User("hei", "password");
        UserHandling users = new UserHandling();
        users.writeUserToRegister(user);
    }

}