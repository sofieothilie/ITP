package core;

import java.util.ArrayList;
import java.util.List;

public class MovieRegister {
    private List<Movie> movies = new ArrayList<>();
    MovieHandler handler = new MovieHandler();

    public void addMovie(Movie movie){
        //Adds a movie to file if it doesn't already exist in file.
        //this.movies = getMovieRegister(); //TODO
        if(movieExists(movie)){
            throw new IllegalArgumentException("Filmen finnes allerede");
        }
        handler.writeMovieToRegister(movie);
    }

    public void updateMovie(Movie movie){
        //Updates the movie in the file if it already exists in file.
        this.movies = getMovieRegister();
        if (movies.isEmpty()){
            throw new IllegalArgumentException("No registered movie yet.");
        }
        else if (!movies.contains(movie)){
            throw new IllegalArgumentException("No movie with title " + movie.getTitle() + " and genre " + movie.getGenre());
        }
        handler.updateMovieInRegister(movie);
    }

    public List<Movie> getMovieRegister(){
        //Returns a list of movies if the file exists, returns an empty list if not.
        if (MovieHandler.fileExists()){
            return new ArrayList<>(handler.readMovieAndRatingFromRegister());
        }
        else{
            return List.of();
        }
    }


    public List<Movie> searchGenre(String genre){
        //Returns a list of movies which has the given genre.
        this.movies = getMovieRegister();
        List<Movie> moviesByGenre = new ArrayList<>();
        for(Movie movie : movies){
            if(movie.getGenre().equals(genre)){
                moviesByGenre.add(movie);
            }
        }
        return new ArrayList<>(moviesByGenre);
    }

    public List<Movie> searchMovieTitle(String title){
        //Returns a list of movies which has the given title.
        this.movies = getMovieRegister();
        List<Movie> moviesByTitle = new ArrayList<>();
        for(Movie movie : movies){
            if(movie.getTitle().equals(title)){
                moviesByTitle.add(movie);
            }
        }
        return new ArrayList<>(moviesByTitle);
    }

    public Movie getMovie(String title, String genre){
        //Returns a movies which has the given title and genre.
        this.movies = getMovieRegister();
        for(Movie movie : movies){
            if(movie.getTitle().equals(title) && movie.getGenre().equals(genre)){
                return movie;
            }
        }
        throw new IllegalArgumentException("No movie with title " + title + " and genre " + genre + ".");
    }

    private boolean movieExists(Movie movie){
        //Returns true if a movie exists in register, if not false.
        this.movies = getMovieRegister();
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
}

