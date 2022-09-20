package core;

import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private HashMap<Movie, Integer> ratedMovies;

    public User(String username, String password){
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
        return ratedMovies;
    }

    public void rateMovie(Movie movie, Integer myRating){
        if(ratedMovies.containsKey(movie)){
            throw new IllegalArgumentException("The movie is already rated");
        }
        if(myRating <1.0 || myRating > 5.0){
            throw new IllegalArgumentException("Rating must be an integer from 1 to 5");
        }
        ratedMovies.put(movie, myRating);
        movie.addRating(myRating);        
        movie.updateRating( myRating);
    }

    public String ratedMoviesToStrong(){
        String ratedMoviesString = "";
        for(Movie movie : ratedMovies.keySet()){
            ratedMoviesString +=  movie.getTitle() + ":" + movie.getGenre() + ":" + ratedMovies.get(movie) + ",";
        }
        return ratedMoviesString;
    }

    @Override
    public String toString() {
        return username + ";" + password + ";" + this.ratedMoviesToStrong();
    }
}
