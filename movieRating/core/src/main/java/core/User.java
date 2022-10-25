package core;

import java.util.HashMap;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import core.deserializerAndSerializer.MovieDeserializerForUser;
import core.deserializerAndSerializer.MovieSerializerForUser;

public class User {
    private String username; //TODO: final
    private String password; //TODO: final
    private HashMap<Movie, Integer> ratedMovies;

    public User() { // constructor for file management
        super();
    }

//TODO: fjerne
//public User(String username, String password, HashMap<Movie, Integer> ratedMovies){ // constructor used in the file handler, when fetching from UserRegister
//    this(username, password);
//    this.ratedMovies = new HashMap<>(ratedMovies);
//
//}

    public User(String username, String password){ //constructor that creates User object, TODO, enters this or that empty
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

    @JsonSerialize(using = MovieSerializerForUser.class) // TODO: must comment
    @JsonDeserialize(using = MovieDeserializerForUser.class) // TODO: must comment
    public HashMap<Movie, Integer> getRatedMovies() { //returns a hashmap with movies and your rating on the movie
        if(ratedMovies.isEmpty()){
            return new HashMap<>();
        }
        else{
            return new HashMap<>(ratedMovies);
        }
    }

    public void rateMovie(Movie movie, Integer myRating){ //method of rating a film

        for (Entry<Movie, Integer> ratedMovie : this.getRatedMovies().entrySet()) { //TODO: use hasRatedMovie instead of the for loop
            if (ratedMovie.getKey().equals(movie)){
                throw new IllegalArgumentException("The movie is already rated");
            }    
        }
        if(myRating <1 || myRating > 5){ 
            throw new IllegalArgumentException("Rating must be an integer from 1 to 5");
        }
        ratedMovies.put(movie, myRating);
        movie.addRating(myRating);        
    }

    public boolean hasRatedMovie(Movie movie){ //TODO: check where it will be used, checks if the user has already rated it
        boolean containsMovie = false;
        for (Movie ratedMovie : this.getRatedMovies().keySet()) {
            if ((ratedMovie.getTitle().equals(movie.getTitle()) && ratedMovie.getGenre().equals(movie.getGenre()))){
                containsMovie = true;
            }            
        }
        return containsMovie;
    }

    @Override
    public String toString() { //returns a string with username and password, TODO: possibly delete
        return username + "\t" + password;
    }

    @Override
    public boolean equals(Object object){ // Comparing User objects, TODO: hear with studass
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
    public int hashCode() { //TODO: checkout with student assistent
        //SpotBugs demand override of hashCode with override of equals. 
        //This was their own fix when it isn't to be used.
        return 43;
    }

}
