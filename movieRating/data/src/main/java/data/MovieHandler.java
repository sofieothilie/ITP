package data;

import core.Movie;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;


public class MovieHandler {

    public static final String SAVE_FOLDER = "/movieRating/data/src/main/java/data/";
    public static final String fileName = "MovieRegister.json";

    public File getFile(){
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        //return new File(path + SAVE_FOLDER + fileName);
        return new File(fileName);

    }


    public void writeMovieToRegister(Movie movie){
        //Writes movie to file with JSON.
        //MovieRegister secures that duplicate movies aren't written to file.
        try {
            List<Movie> movies = new ArrayList<Movie>();
            if (this.fileExists()){
                movies = readMovieAndRatingFromRegister();
            }
            movies.add(movie);
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter()); 
            objectWriter.writeValue(getFile(), movies);
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

        // //Writes all previous movies and the new updates movie to file:
        // if (!movieList.contains(movie)){
        //     throw new IllegalArgumentException("no such movie");
        // }
        //else{
        for (Movie oldMovie : movieList) {
            if (oldMovie.getTitle().equals(movie.getTitle()) && oldMovie.getGenre().equals(movie.getGenre())){
                movieList.remove(oldMovie);
                movieList.add(movie); 
            }
        }
    }


    public List<Movie> readMovieAndRatingFromRegister(){
        //Reads from file and generates a list of movies based on it.
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();

            // convert JSON array to list of movies
            List<Movie> movies = Arrays.asList(mapper.readValue(getFile(), Movie[].class));
            return new ArrayList<>(movies);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error: " + e);
        }
    }

    public Boolean fileExists(){
        File f = new File(getFile().getAbsolutePath());
        if (f.isFile()){
            return true;
        }
        else{
            return false;
        }
    }      
}
