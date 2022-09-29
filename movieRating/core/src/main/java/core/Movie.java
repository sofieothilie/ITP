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
        //Validering på tittel
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

    public double getRating() {
        //fjerne, duplikat
        return rating;
    }

    public List<Integer> getAllRatings(){
        return ratings; //returnere kopi av listen
    }

    public void addRating(int rating){
        //validering, sjekke at int
        ratings.add(rating);
    }

    public void setMeanrating(Double rating){
        //fjerne, dårlig i henhold til objektorientert. 
        this.rating = rating;
    }

    public Double getMeanRating(){
        //regne ut fra listen, ikke hente verdi.
        return this.rating;
    }

    public String toString(){
        return ""+ this.getTitle() + "\t" + this.getGenre() + "\t" + this.getRating(); //oppdatere til getMeanRating.
    }
}
