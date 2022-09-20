package core;

import java.util.ArrayList;
import java.util.List;

public class MovieRegister {
    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie){
        if(movies.contains(movie)){
            movies.remove(movie);
            movies.add(movie);
        }
        movies.add(movie);
    }

    public List<Movie> getMovieRegister(){
        return movies;
    }
}

