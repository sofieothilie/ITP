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

    //private List<Movie> movies = new ArrayList<Movie>();
    public static final String SAVE_FOLDER = "/movieRating/core/src/main/java/core/";
    JSONObject obj = new JSONObject(); 

    public void writeMovieToRegister(Movie movie){
        //skrive film til fil, håndtering av at den ikke eksisterer finnes i movieRegister
        //bruker convertMovieToString for å skrive.
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("movieRegister.txt", true)));
            writer.println(this.convertMovieToJSON(movie));
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

    private String convertMovieToJSON(Movie movie){
        //Burde muligens endre split om ; er valid i tittel.
        StringBuilder sb = new StringBuilder();
        sb.append(movie.getTitle() + "; " + movie.getGenre());
        if (movie.getAllRatings().size() > 0){
            sb.append("; ");
            for(Integer rating: movie.getAllRatings()){
                sb.append(rating);
                sb.append("\t"); //Fjerne siste tab?
            }
        }
        return String.valueOf(sb);
        
    }

    public void updateMovieToRegister(Movie movie){
        //Reads through the file, generates a list of all movies, where the new movie is updated
        //Overwrites all these movies to file.
        List<Movie> movies = new ArrayList<>();
        movies = readMovieAndRatingFromRegister();
        // File f = new File("movieRegister.txt");
        // if (f.isFile()){
        //     movies = readMovieAndRatingFromRegister();
        //     if(!movies.isEmpty()){
        //         for (Movie mov : movies) {
        //             if (mov.getTitle().equals(movie.getTitle())){
        //                 movies.add(movie);
        //                 movies.remove(mov);
        //             }
        //         }
        //     }
        // }
        // else{
        //     writeMovieToRegister(movie);
        // }
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("movieRegister.txt", false)));
            for(Movie mov: movies){
                writer.println(mov.getTitle() + "; " + mov.getGenre() + "; " + splitRatings(mov)); 
            }
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

    private String splitRatings(Movie movie){
        //Fjerne, duplikat
        StringBuilder sb = new StringBuilder();
        if (!movie.getAllRatings().isEmpty()){
            for(Integer rating: movie.getAllRatings()){
                sb.append(rating);
                sb.append("\t");
            }
            return sb.substring(0, sb.length()-1);
        }
        return null;
    }


    public List<Movie> readMovieAndRatingFromRegister(){
        //lese og returnere en liste av alle filmer.
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

    public static void main(String[] args) {
        MovieHandler handler = new MovieHandler();
        Movie movie = new Movie("awfdg", "fantasy");
        movie.addRating(3);
        movie.addRating(4);
        
        movie.addRating(3);
        handler.writeMovieToRegister(movie);
        Movie mov = new Movie("hel", "fantasy");
        mov.addRating(4);
        handler.writeMovieToRegister(mov);
        Movie mos = new Movie("hajsd", "fantasy");
        handler.writeMovieToRegister(mos);
    } 
}
