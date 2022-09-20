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
        //add comment to show git command
    }

    public List<Movie> getMovieRegister(){
        return movies;
    }

    public List<Movie> searchGenre(String genre){
        List<Movie> moviesByGenre = new ArrayList<>();
        for(Movie movie : movies){
            if(movie.getGenre().equals(genre)){
                moviesByGenre.add(movie);
            }
        }
        return moviesByGenre;
    }

    public List<Movie> searchMovieTitle(String title){
        List<Movie> moviesByTitle = new ArrayList<>();
        for(Movie movie : movies){
            if(movie.getTitle().equals(title)){
                moviesByTitle.add(movie);
            }
        }
        return moviesByTitle;
    }

    public Movie getMovie(String title, String genre){
        for(Movie movie : movies){
            if(movie.getTitle().equals(title) && movie.getGenre().equals(genre)){
                return movie;
            }
        }
        return null;
    }
}

