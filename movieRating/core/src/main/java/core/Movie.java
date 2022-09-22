package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Movie {
    private String title;
    private String genre;
    private double rating;
    private List<Integer> ratings = new ArrayList<>();
    private static List<String> GENRES = Arrays.asList("action", "comedy", "drama", "fantasy", "horror", "mystery", "romance", "thriller");

    public Movie(String title, String genre) {
        if(! GENRES.contains(genre)){
            throw new IllegalArgumentException("Not a valid genre");
        }
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public List<Integer> getAllRatings(){
        return ratings;
    }

    public void addRating(int rating){
        ratings.add(rating);
    }

    public void setMeanrating(Double rating){
        this.rating = rating;
    }

    public Double getMeanRating(){
        return this.rating;
    }

    public String toString(){
        return ""+ this.getTitle() + "\t" + this.getGenre() + "\t" + this.getMeanRating();
    }
}
