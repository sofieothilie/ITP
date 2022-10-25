package core;

import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import core.deserializerAndSerializer.MovieDeserializerForUser;
import core.deserializerAndSerializer.MovieSerializerForUser;

public class User {
    private String username;
    private String password;
    private HashMap<Movie, Integer> ratedMovies;

    public User() { // constructor for file management
        super();
    }

    public User(String username, String password){ //constructor that creates User object
        if (username.matches("[a-zA-Z0-9]+") && password.matches("[a-zA-Z0-9]+")){
            this.username = username;
            this.password = password;
            ratedMovies = new HashMap<>();
        } 
        else if (username.isEmpty() || password.isEmpty()){
            throw new IllegalArgumentException("Brukernavn og passord kan ikke være tomme");
        }
        else {
            throw new IllegalArgumentException("Brukernavn og passord kan bare inneholde tall og bokstaver");
        }
    }

    public String getUsername() {//return username
        return username;
    }

    public String getPassword() { //return the users password
        return password;
    }

    @JsonSerialize(using = MovieSerializerForUser.class)
    @JsonDeserialize(using = MovieDeserializerForUser.class)
    public HashMap<Movie, Integer> getRatedMovies() { //returns a hashmap with movies and your rating on the movie
        //This method cannot be used when writing user to file. This method therefore implements jsonSerialize and jsonDeserialize.
        if(ratedMovies.isEmpty()){
            return new HashMap<>();
        }
        else{
            return new HashMap<>(ratedMovies);
        }
    }

    public void rateMovie(Movie movie, Integer myRating){ //method of rating a film
        if (this.hasRatedMovie(movie)){
            throw new IllegalArgumentException("The movie is already rated");
        }
        if(myRating <1 || myRating > 5){ 
            throw new IllegalArgumentException("Rating must be an integer from 1 to 5");
        }
        ratedMovies.put(movie, myRating);
        movie.addRating(myRating);        
    }

    public boolean hasRatedMovie(Movie movie){ //Checks if the user has already rated a movie
        boolean containsMovie = false;
        for (Movie ratedMovie : this.getRatedMovies().keySet()) {
            if ((ratedMovie.getTitle().equals(movie.getTitle()) && ratedMovie.getGenre().equals(movie.getGenre()))){
                containsMovie = true;
            }            
        }
        return containsMovie;
    }

    @Override
    public boolean equals(Object object){ // Comparing User objects
        if (object instanceof User){
            if (this.getUsername().equals(((User) object).getUsername())){
                if (this.getPassword().equals(((User) object).getPassword())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        //SpotBugs demand override of hashCode with override of equals. 
        //This was their own fix when it isn't to be used.
        return 43;
    }

}
