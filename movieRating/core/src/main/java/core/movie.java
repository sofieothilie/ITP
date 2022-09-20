package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Movie {
    private String title;
    private String genre;
    private double rating;
    private MovieRegister register = new MovieRegister();
    private List<Double> ratings = new ArrayList<>();
    private static List<String> GENRES = Arrays.asList("action", "comedy", "drama", "fantasy", "horror", "mystery", "romance", "thriller");

    public Movie(String title, String genre) {
        if(checkMovie()){
            throw new IllegalArgumentException("Movie already in register");
        }
        if(! GENRES.contains(genre)){
            throw new IllegalArgumentException("Not a valid genre");
        }
        this.title = title;
        this.genre = genre;
        register.addMovie(this);
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public MovieRegister getMovieRegister(){
        return register;
    }

    public List<Double> getAllRatings(){
        return ratings;
    }

    public void addRating(Double rating){
        ratings.add(rating);
    }

    public void setRating(Double rating){
        this.rating = rating;
    }

    public void updateRating( Double myRating){
        Double sum = ratings.stream().mapToDouble(d -> d).sum();
        this.rating  = sum/ratings.size();  
        register.addMovie(this);
    }   

    private boolean checkMovie(){
        if (register.getMovieRegister().isEmpty()){
            return false;
        }
        for(Movie movie: register.getMovieRegister()){
            if (movie.getTitle().equals(this.title)){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return ""+ this.getTitle() + "\t" + this.getGenre() + "\t" + this.getRating();
    }
}
