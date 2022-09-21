package core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class UserHandling {

    private Users users = new Users();

    public void writeUserToRegister(User user){
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("userRegister.txt", true)));
            writer.println(user.toString());
           
            writer.flush();
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error: " + e);
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }
    }

    public void readUsersFromRegister(){
        try {
            Scanner scanner = new Scanner("userRegister.txt");
            this.users = new Users();

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                String username = parts[0];
                String password = parts[1];
                String MovieAndRatings = parts[2];
                String[] movieAndRatings = MovieAndRatings.split(",");
                
                User user = new User(username, password);

                for(String movieAndRating : movieAndRatings){
                    String[] movieAndRatingParts = movieAndRating.split(":");
                    String movieTitle = movieAndRatingParts[0];
                    String genre = movieAndRatingParts[1];
                    String rating = movieAndRatingParts[2];
                    user.rateMovie(new Movie(movieTitle, genre), Integer.parseInt(rating));
                }
                users.registerNewUser(user);

            }
            scanner.close();
        }
        catch (IOException e){
            System.out.println("Error: " + e);
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }
    }

}