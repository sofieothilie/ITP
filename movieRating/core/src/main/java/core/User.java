package core;

import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private HashMap<Movie, Integer> ratedMovies;

    public User(String username, String password){
        if (username.matches("[a-zA-Z0-9]+") && password.matches("[a-zA-Z0-9]+")){
            this.username = username;
            this.password = password;
            ratedMovies = new HashMap<>();
        } 
        else {
            throw new IllegalArgumentException("Brukernavn og passord kan bare inneholde tall og bokstaver");
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<Movie, Integer> getRatedMovies() {
        return new HashMap<>(ratedMovies);
    }

    public void rateMovie(Movie movie, Integer myRating){
        if(ratedMovies.containsKey(movie)){
            throw new IllegalArgumentException("The movie is already rated");
        }
        if(myRating <=1 || myRating >= 5){ //int
            throw new IllegalArgumentException("Rating must be an integer from 1 to 5");
        }
        ratedMovies.put(movie, myRating);
        movie.addRating(myRating);        
    }


    @Override
    public String toString() {
        return username + "\t" + password;
    }
}
