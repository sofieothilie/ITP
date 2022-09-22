package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MovieHandler {
    
    public void writeMovieToRegister(Movie movie){
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("movieRegister.txt", true)));
            writer.println(this.convertMovieToString(movie));
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

    private String convertMovieToString(Movie movie){
        StringBuilder sb = new StringBuilder();
        sb.append(movie.getTitle() + "; " + movie.getGenre());
        if (movie.getAllRatings().size() > 0){
            sb.append("; ");
            for(Integer rating: movie.getAllRatings()){
                sb.append(rating);
                sb.append("\t");
            }
        }
        return String.valueOf(sb);
        
    }

    public List<Movie> readMovieAndRatingFromRegister(){
        List<Movie> copyList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("movieRegister.txt"));

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split("; ");
                String title = parts[0];
                String genre = parts[1];
                Movie movie = new Movie(title, genre);
                if (parts.length > 2){
                    String[] ratings = parts[2].split("\t");
                    for (String str : ratings) {
                        movie.addRating(Integer.valueOf(str));                        
                    }
                    Double sum = Arrays.stream(ratings).mapToDouble(d -> Double.parseDouble(d)).sum();
                    Double mean = sum/ratings.length;  
                    movie.setMeanrating(mean);
                }
                copyList.add(movie);
            }
            scanner.close();
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error: " + e);
        }
        return copyList;

    }


}
