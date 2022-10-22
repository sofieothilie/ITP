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

    public User() { // konstruktør til filhåndtering
        super();
    }

//TODO: fjerne
//public User(String username, String password, HashMap<Movie, Integer> ratedMovies){ // konstruktør som brukes i filhåndteringen, når man henter fra UserRegister
//    this(username, password);
//    this.ratedMovies = new HashMap<>(ratedMovies);
//
//}

    public User(String username, String password){ //konstruktør som oppretter User-objekt, TODO, går inn i denne eller den tomme
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

    public String getUsername() {//returnerer brukernavn
        return username;
    }

    public String getPassword() { //returnerer passordet til brukeren
        return password;
    }

    @JsonSerialize(using = MovieSerializerForUser.class) // TODO: må kommenteres
    @JsonDeserialize(using = MovieDeserializerForUser.class) // TODO: må kommenteres
    public HashMap<Movie, Integer> getRatedMovies() { //returnerer en hashmap med filmer og din rating på filmen
        if(ratedMovies.isEmpty()){
            return new HashMap<>();
        }
        else{
            return new HashMap<>(ratedMovies);
        }
    }

    public void rateMovie(Movie movie, Integer myRating){ //metode for å gi rating til en film

        for (Entry<Movie, Integer> ratedMovie : this.getRatedMovies().entrySet()) { //TODO: bruke hasRatedMovie istedenfor for-løkken
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

    public boolean hasRatedMovie(Movie movie){ //TODO: sjekke opp hvor den skal brukes, sjekker om brukeren allerede ratet den
        boolean containsMovie = false;
        for (Movie ratedMovie : this.getRatedMovies().keySet()) {
            if ((ratedMovie.getTitle().equals(movie.getTitle()) && ratedMovie.getGenre().equals(movie.getGenre()))){
                containsMovie = true;
            }            
        }
        return containsMovie;
    }

    @Override
    public String toString() { //returnerer en string med brukernavn og passord, TODO: eventuelt slette
        return username + "\t" + password;
    }

    @Override
    public boolean equals(Object object){ // Sammenligner User-objekter, TODO: høre med studass
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
    public int hashCode() { //TODO: høre med studass
        //SpotBugs demand override of hashCode with override of equals. 
        //This was their own fix when it isn't to be used.
        return 43;
    }

}
