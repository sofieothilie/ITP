package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Movie {
    private String title; //TODO: bør settes til final
    private String genre; //TODO: endre til final
    private List<Integer> allRatings = new ArrayList<>(); 
    private static List<String> GENRES = Arrays.asList("action", "comedy", "drama", "fantasy", "horror", "mystery", "romance", "thriller");

    public Movie() { // constructor for file management
        super();
    }
    public Movie(String title, String genre, List<Integer> allRatings) { // constructor for file management
        this(title, genre);
        this.allRatings = new ArrayList<>(allRatings);
    }

    public Movie(String title, String genre, Integer rating) { // TODO: Check if we need this for file management
        this(title, genre);
        this.allRatings.add(rating);
    }
    
    public Movie(String title, String genre) { // create Movie-object
        if(title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title;
        if(! GENRES.contains(genre)){
            throw new IllegalArgumentException("Not a valid genre");
        }
        this.genre = genre;
    }

    public String getTitle() { //return title
        return title;
    }

    public String getGenre() { //return genre
        return genre;
    }

    public List<Integer> getAllRatings(){ //returns a copy of the list with all ratings
        List <Integer> copyAllRatings = new ArrayList<Integer>(allRatings);
        return copyAllRatings;
    }

    public void addRating(int rating){ //adds a rating to the list of all ratings and adds
        if(rating < 1 || rating > 5){ //checks whether the rating is between 1 and 5
            throw new IllegalArgumentException("Not a valid rating");
        }
        allRatings.add(rating); 
    }

    public double averageRating() { // TODO: rename, + @JsonIgnore above method (calculates average of all ratings for this movie)
        Integer sum = 0;
        for (int rating=0; rating < allRatings.size(); rating++) {
              sum += allRatings.get(rating);
        }
        double average = (sum.doubleValue() / allRatings.size());
        return average;
        
    }

    public String toString(){ //returns a string with title and genre and average rating
        return ""+ this.getTitle() + "; " + this.getGenre() + "; " + String.format("%.2f",this.averageRating());
    }

    @Override
    public boolean equals(Object object){ //Checks if an object is equal, TODO: write what the method does
        if (object instanceof Movie){
            if (this.getTitle().equals(((Movie) object).getTitle())){
                if (this.getGenre().equals(((Movie) object).getGenre())){
                    return true;
                }}
        }
        return false;
    }

    @Override
    public int hashCode() { //TODO: ask studass about spotbugs, hashcode
        //SpotBugs demand override of hashCode with override of equals. 
        //This was their own fix when it isn't to be used.
        return 43;
    }

}

