package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MovieHandler {

    //private List<Movie> movies = new ArrayList<Movie>();
    public static final String SAVE_FOLDER = "/movieRating/core/src/main/java/core/";

    public void writeMovieToRegister(Movie movie){
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("movieRegister.txt", true)));
            StringBuilder sb = new StringBuilder();
            for(Integer rating: movie.getAllRatings()){
                sb.append(rating);
                sb.append(";");
            }
            writer.println(movie.getTitle() + "; " + movie.getGenre() + "; " + sb.substring(0,-1)); 
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

    public List<Movie> readMovieAndRatingFromRegister(){
        List<Movie> copyList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner("movieRegister.txt");

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                String title = parts[0];
                String genre = parts[1];
                String[] ratings = parts[2].split(", ");
                Double sum = Arrays.stream(ratings).mapToDouble(d -> Double.parseDouble(d)).sum();
                Double mean = sum/ratings.length;  
                Movie movie = new Movie(title, genre);
                movie.setRating(mean);
                copyList.add(movie);
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }
        return copyList;

    }
    
}
