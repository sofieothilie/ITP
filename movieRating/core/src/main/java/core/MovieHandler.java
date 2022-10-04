package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class MovieHandler {

    public static final String SAVE_FOLDER = "/movieRating/core/src/main/java/core/";
    JSONObject obj = new JSONObject(); 

    public void writeMovieToRegister(Movie movie){
        //Writes movie to file with JSON.
        //MovieRegister secures that duplicate movies aren't written to file.
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("movieRegister.txt", true)); 
            writer.write(this.convertMovieToJSON(movie));
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


    public void updateMovieInRegister(Movie movie){
        //Takes in a movie and updates it in the register

        //Generates a list of all movie objects in file:
        List<Movie> movieList = this.readMovieAndRatingFromRegister();

        //Writes all previous movies and the new updates movie to file:
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("movieRegister.txt", false))) {
            bufferedWriter.write(this.convertMovieToJSON(movie));
            for (Movie oldMovie : movieList) {
                if (!oldMovie.getTitle().equals(movie.getGenre()) || !oldMovie.getGenre().equals(movie.getGenre())){
                    bufferedWriter.write(this.convertMovieToJSON(movie));
                }  
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (Exception ex) {
            System.out.println("An unknown error has accoured.");
        }
    }


    public List<Movie> readMovieAndRatingFromRegister(){
        //Reads from file and generates a list of movies based on it.
        List<Movie> movieList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("movieRegister.txt"));
            JSONArray arr = obj.getJSONArray("Movies");

            for(Object o : jsonArray){
                JSONObject jsonMovie = (JSONObject) o;
                movieList.add(this.convertJSONToMovie(o));                
            }
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error: " + e);
        }
        return new ArrayList<>(movieList);
    }

    public static Boolean fileExists(){
        File f = new File("movieRegister.txt");
        if (f.isFile()){
            return true;
        }
        else{
            return false;
        }
        
    }

    private Movie convertJSONFromMovie(JSONObject jsonObject){
        //maybe delete
        //Takes in a jsonObject, generates and returns a movie object.
        String title = String.valueOf(jsonMovie.getString("Title"));
        String genre = String.valueOf(jsonMovie.getString("Genre"));
        Movie movie = new Movie(title, genre);
        JSONArray ratingArray = (JSONArray) jsonMovie.get("Ratings");
        for (Object ratingObject : ratingArray){
            Integer rating = Integer.valueOf(String.valueOf(ratingObject));
            movie.addRating(rating);                    
        }  
    }

    private String convertMovieToJSON(Movie movie){
        //Generates a JSON object from a movie and returns it as a string.
        JSONObject obj = new JSONObject(); 

        obj.put("Title", movie.getTitle());
        obj.put("Genre", movie.getGenre());
        if (movie.getAllRatings().size() > 0){
            obj.put("Ratings", movie.getAllRatings());
        }
        return obj.toJSONString();;
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
