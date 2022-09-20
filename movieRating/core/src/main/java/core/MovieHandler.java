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

    private List<Movie> movies = new ArrayList<Movie>();

    public void addMovie(Movie movie) {
        if (movies.contains(movie)) {
            throw new IllegalArgumentException("Movie already in register");
        }
        movies.add(movie);
    }

    public void writeMovieToRegister(String filename){
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename, false)));

            for (Movie movie : movies) {
                StringBuilder sb = new StringBuilder();
                for(Double rating: movie.getAllRatings()){
                    sb.append(rating);
                    sb.append(", ");
                } 
                String str = sb.substring(0, sb.length() - 1);

                writer.println(movie.getTitle() + ";" + movie.getGenre() + ";" + sb); // TODO ny metode i Movie
            }
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

    public void readMovieAndRatingFromRegister(String filename){
        try {
            Scanner scanner = new Scanner(filename);
            this.movies = new ArrayList<>();

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
                movies.add(movie);
            }
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }
    }
    
}
