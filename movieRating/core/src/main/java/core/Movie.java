package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Movie {
    private String title; //bør settes til final
    private final String genre;
    private int rating;
    private List<Integer> ratings = new ArrayList<>();
    private static List<String> GENRES = Arrays.asList("action", "comedy", "drama", "fantasy", "horror", "mystery", "romance", "thriller");

    public Movie(String title, String genre) {
        if(title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
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
        List <Integer> copyAllRatings = new ArrayList<Integer>(ratings);
        return copyAllRatings;
    }

    public void addRating(int rating){
        if(rating < 1 || rating > 5){
            throw new IllegalArgumentException("Not a valid rating");
        }
        ratings.add(rating);
    }

    private double averageRating() {
        // beregner gjennomsnittet av alle ratinger for denne filmen
        Integer sum = 0;
        for (int rating=0; rating < ratings.size(); rating++) {
              sum += ratings.get(rating);
        }
        double average = (sum.doubleValue() / ratings.size());
        return average;
    }

    public double getAverageRating() {
        return averageRating();
    }

    public String toString(){
        return ""+ this.getTitle() + "\t" + this.getGenre() + "\t" + String.format("%.2f",averageRating());
    }
}

