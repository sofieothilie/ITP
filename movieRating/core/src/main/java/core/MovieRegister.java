package core;

import java.util.ArrayList;
import java.util.List;

public class MovieRegister {
    private List<Movie> movies = new ArrayList<>();
    MovieHandler handler = new MovieHandler();

    public void addMovie(Movie movie){
        setMovieRegister();
        if(movies.contains(movie)){
            movies.remove(movie);
            movies.add(movie);
        }
        movies.add(movie);
        handler.writeMovieToRegister(movie);
    }

    public List<Movie> getMovieRegister(){
        setMovieRegister();
        return movies;
    }

    public List<Movie> searchGenre(String genre){
        setMovieRegister();
        List<Movie> moviesByGenre = new ArrayList<>();
        for(Movie movie : movies){
            if(movie.getGenre().equals(genre)){
                moviesByGenre.add(movie);
            }
        }
        return moviesByGenre;
    }

    public List<Movie> searchMovieTitle(String title){
        setMovieRegister();
        List<Movie> moviesByTitle = new ArrayList<>();
        for(Movie movie : movies){
            if(movie.getTitle().equals(title)){
                moviesByTitle.add(movie);
            }
        }
        return moviesByTitle;
    }

    public Movie getMovie(String title, String genre){
        setMovieRegister();
        for(Movie movie : movies){
            if(movie.getTitle().equals(title) && movie.getGenre().equals(genre)){
                return movie;
            }
        }
        return null;
    }

    public boolean checkMovie(Movie movie){
        setMovieRegister();
        if (getMovieRegister().isEmpty()){
            return false;
        }
        for(Movie mov: getMovieRegister()){
            if (mov.getTitle().equals(movie.getTitle())){
                return true;
            }
        }
        return false;
    }

    private void setMovieRegister(){
        this.movies = handler.readMovieAndRatingFromRegister();
    }
}

