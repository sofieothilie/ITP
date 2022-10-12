package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Movie {
    private String title; //bør settes til final
    private String genre;
    //private int averageRating;
    private List<Integer> allRatings = new ArrayList<>();
    private static List<String> GENRES = Arrays.asList("action", "comedy", "drama", "fantasy", "horror", "mystery", "romance", "thriller");

    public Movie() {
        super();
    }
    public Movie(String title, String genre, List<Integer> allRatings) {
        super();
        this.title = title;
        this.genre = genre;
        this.allRatings = allRatings;
    }

    public Movie(String title, String genre) { // oppretter konstruktør for Movie
        if(title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
        if(! GENRES.contains(genre)){
            throw new IllegalArgumentException("Not a valid genre");
        }
        this.genre = genre;
    }

    public String getTitle() { //returnerer tittelen
        return title;
    }

    public String getGenre() { //returnerer sjangeren
        return genre;
    }

    public List<Integer> getAllRatings(){ //returnerer en kopi av listen med alle ratings
        List <Integer> copyAllRatings = new ArrayList<Integer>(allRatings);
        return copyAllRatings;
    }

    public void addRating(int rating){ //legger til en rating i liste over alle ratings
        if(rating < 1 || rating > 5){ //sjekker om rating er mellom 1 og 5
            throw new IllegalArgumentException("Not a valid rating");
        }
        allRatings.add(rating);
    }

    public double averageRating() { // beregner gjennomsnittet av alle ratinger for denne filmen
        Integer sum = 0;
        for (int rating=0; rating < allRatings.size(); rating++) {
              sum += allRatings.get(rating);
        }
        double average = (sum.doubleValue() / allRatings.size());
        return average;
    }

    public String toString(){ //returnerer en string med tittel og sjanger og gjennomsnittsrating
        return ""+ this.getTitle() + "; " + this.getGenre() + "; " + String.format("%.2f",averageRating());
    }

    public String movieInfoString(){
        return ""+ this.getTitle() + "; " + this.getGenre() + "; " + getAllRatings();
    }
}

