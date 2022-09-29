package core;

import java.util.ArrayList;
import java.util.List;

public class MovieRegister {
    private List<Movie> movies = new ArrayList<>();
    MovieHandler handler = new MovieHandler();

    public void addMovie(Movie movie){
        setMovieRegister(); //sikre at det alltid finnes en tom fil, og dermed ikke får error pga manglende fil.
        //Validere om filen er tom, i så fall legge til filmen uten videre sjekk fra fil.
        if(checkMovie(movie)){
            throw new IllegalArgumentException("Filmen finnes allerede");
        }
        movies.add(movie); //trenger vi å legge den til i listen, denne blir jo alltid oppdatert.
        handler.writeMovieToRegister(movie);
    }

    public void updateMovie(Movie movie){
        //validere at filmen allerede fins.
        handler.updateMovieToRegister(movie);
    }

    public List<Movie> getMovieRegister(){
        setMovieRegister();
        return movies; //returnere kopi av listen
    }



    public List<Movie> searchGenre(String genre){
        setMovieRegister();
        List<Movie> moviesByGenre = new ArrayList<>();
        for(Movie movie : movies){
            if(movie.getGenre().equals(genre)){
                moviesByGenre.add(movie);
            }
        }
        return moviesByGenre; //returnere kopi av listen
    }

    public List<Movie> searchMovieTitle(String title){
        setMovieRegister();
        List<Movie> moviesByTitle = new ArrayList<>();
        for(Movie movie : movies){
            if(movie.getTitle().equals(title)){
                moviesByTitle.add(movie);
            }
        }
        return moviesByTitle; //returnere kopi av listen
    }

    public Movie getMovie(String title, String genre){
        setMovieRegister();
        for(Movie movie : movies){
            if(movie.getTitle().equals(title) && movie.getGenre().equals(genre)){
                return movie;
            }
        }
        return null; //Vurdere om denne skal ha null eller kaste unntak.
    }

    public boolean checkMovie(Movie movie){ //private
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

