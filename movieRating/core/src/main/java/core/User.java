package core;

import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private HashMap<Movie, Integer> ratedMovies;

    public User(String username, String password){ //konstruktør for User
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

    public HashMap<Movie, Integer> getRatedMovies() { //returnerer en hashmap med filmer og rating
        return new HashMap<>(ratedMovies);
    }

    public void rateMovie(Movie movie, Integer myRating){ //metode for å gi rating til en film
        if(ratedMovies.containsKey(movie)){
            throw new IllegalArgumentException("The movie is already rated");
        }
        if(myRating <1 || myRating > 5){ //sjekker om rating er mellom 1 og 5
            throw new IllegalArgumentException("Rating must be an integer from 1 to 5");
        }
        ratedMovies.put(movie, myRating);
        movie.addRating(myRating);        
    }

    @Override
    public String toString() { //returnerer en string med brukernavn og passord
        return username + "\t" + password;
    }
}
