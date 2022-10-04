package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Movie {
    private String title; //bør settes til final
    private final String genre;
    private double rating;
    private List<Integer> ratings = new ArrayList<>();
    private static List<String> GENRES = Arrays.asList("action", "comedy", "drama", "fantasy", "horror", "mystery", "romance", "thriller");

    public Movie(String title, String genre) {
        if(title.matches("[a-zA-Z0-9]+")){
            this.title = title;
        }
        if(title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if(! GENRES.contains(genre)){
            throw new IllegalArgumentException("Not a valid genre");
        }
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }


    public List<Integer> getAllRatings(){
        return ratings; //returnere kopi av listen
    }

    public void addRating(int rating){
        if(rating < 1 || rating > 5){
            throw new IllegalArgumentException("Not a valid rating");
        }
        ratings.add(rating);
    }

    public Double getMeanRating(){
        //regne ut fra listen, ikke hente verdi.
        return this.rating;
    }

    public String toString(){
        return ""+ this.getTitle() + "\t" + this.getGenre() + "\t" + this.getMeanRating();
    }
}
