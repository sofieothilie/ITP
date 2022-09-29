package core;

import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private HashMap<Movie, Integer> ratedMovies;

    public User(String username, String password){
        //validering på brukernavn og passord, bare tall og bokstaver
        this.username = username;
        this.password = password;
        ratedMovies = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public HashMap<Movie, Integer> getRatedMovies() {
        return ratedMovies; //returnere kopi av hashmap
    }

    public void rateMovie(Movie movie, Integer myRating){
        if(ratedMovies.containsKey(movie)){
            throw new IllegalArgumentException("The movie is already rated");
        }
        if(myRating <1.0 || myRating > 5.0){ //int
            throw new IllegalArgumentException("Rating must be an integer from 1 to 5");
        }
        ratedMovies.put(movie, myRating);
        movie.addRating(myRating);        
    }

    public String ratedMoviesToString(){
        //fjerne, hører til i userhandling.
        String ratedMoviesString = "";
        if (this.ratedMovies.keySet().size() > 0){
            for(Movie movie : ratedMovies.keySet()){
                ratedMoviesString +=  movie.getTitle() + ":" + movie.getGenre() + ":" + ratedMovies.get(movie) + ",";
            }
        }
        return ratedMoviesString;
    }

    @Override
    public String toString() {
        //bytte til \t? og endre på this.ratedMoviesToString.
        return username + ";" + password + ";" + this.ratedMoviesToString();
    }
}
