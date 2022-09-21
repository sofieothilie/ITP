package ui;

import java.util.Arrays;
import java.util.List;

import core.Movie;
import core.MovieRegister;
import core.User;
import core.UserRegister;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class MovieRatingController {
    //Fields
    private User user;
    private UserRegister usersRegister = new UserRegister();
    private Movie movie;
    private MovieRegister movieRegister = new MovieRegister();
    private static List<String> genresList = Arrays.asList("action", "comedy", "drama", "fantasy", "horror", "mystery", "romance", "thriller"); 
    private static List<Integer> ratingList = Arrays.asList(1, 2, 3, 4, 5);   

    //FXML fields
    @FXML
    private TextField username, password, movieName;
    @FXML
    private Button logIn, addMovieRegister, createUser, searchMovie, addMovieToRegister, logOut, rateButton;
    @FXML
    private ChoiceBox<String> genreBox;
    @FXML
    private ChoiceBox<Integer> rateBox;
    @FXML
    private ListView<String> movieRegisterList;
    @FXML
    private TextArea ratedMovie;
    @FXML
    private Label loggedIn, loggedOut, usernameLabel, passwordLabel, rateLabel, movieLabel, ratingscaleLabel;



    // Methods

    @FXML
    public void initialize() {
        //Starts up the app with correct visibility:
        setLoginPossibility(true);
        setSearchVisibility(true);
        setRateVisibility(false, null);
        loggedOut.visibleProperty().set(false);
        loggedIn.visibleProperty().set(false); 
        addMovieRegister.setVisible(false);
        setGenres();
        setRating();      
    }
    
    private void setGenres() {
        for (String str : genresList) {
            genreBox.getItems().add(str);
        }
    }
    private void setRating(){
        for (Integer integer : ratingList) {
            rateBox.getItems().add(integer);
        }
    }

    private void setLoginPossibility(boolean value){
        //Sets the log-in-area to desired visibility:
        usernameLabel.visibleProperty().set(value);
        passwordLabel.visibleProperty().set(value);
        logOut.setVisible(!value);
        username.visibleProperty().set(value);
        username.clear();
        password.visibleProperty().set(value);
        password.clear();
        logIn.visibleProperty().set(value);
        createUser.visibleProperty().set(value);
    }

    private void loggedIn(boolean value){
        loggedOut.visibleProperty().set(!value);
        loggedIn.visibleProperty().set(value);
        addMovieRegister.setVisible(value);
    }

    private void setSearchVisibility(boolean value){
        //Sets the search-area to desired visibility:
        searchMovie.visibleProperty().set(true);
        genreBox.visibleProperty().set(true);
        movieRegisterList.visibleProperty().set(true);
    }

    private void setRateVisibility(boolean value, Movie movie){
        //Sets the rate-area to desired visibility:
        rateBox.visibleProperty().set(value);
        rateButton.visibleProperty().set(value);
        movieLabel.visibleProperty().set(value);
        ratedMovie.visibleProperty().set(value);
        rateLabel.visibleProperty().set(value);
        ratingscaleLabel.setVisible(value);
        if (movie != (null) && value == true){
            ratedMovie.setText(movie.getTitle());
        }
    }
    
    //User methods
    @FXML
    private void handleLogIn(){
        //Tries to log in a user. If user excists: sets correct fields and visibility status.
        try {
            this.usersRegister.validUser(username.getText(), password.getText());  
            this.user = this.usersRegister.getUser(username.getText());
            setLoginPossibility(false);
            loggedIn(true);  
        } catch (Exception e) {
            errorActivation(e.getMessage());
        }          
    }

    @FXML
    private void handleCreateUser(){
        //Creates a new user and sets desired fields and visibility.
        try {
            this.usersRegister.existingUser(username.getText(), password.getText());      
        } catch (Exception e) {
            errorActivation(e.getMessage());
        }
        this.user = new User(username.getText(), password.getText());
        try {
            this.usersRegister.registerNewUser(this.user);
        } catch (Exception e) {
            errorActivation(e.getMessage());
        }
        setLoginPossibility(false); 
        loggedIn(true); 
    }


    @FXML 
    private void handleLogOut(){
        //Logs user out. Resets desired fields and sets desired visibility.
        this.user = null;
        setLoginPossibility(true); 
        setRateVisibility(false, null); 
        loggedIn(false);
    }

    //Movie methods
    @FXML
    private void handleSearchMovie(){
        //Searches for movies by title and displays them in list view.
        List <Movie> movieList = movieRegister.searchMovieTitle(movieName.getText());
        if (movieList.isEmpty()){ errorActivation("No movies with title " + movieName.getText());}
        for (Movie movie : movieList) {
            movieRegisterList.getItems().add(movie.toString());
        }
    }

    @FXML
    private void handleSearchGenre(){ //MANGLER I FXML
        //Searches for movies by genre and displays them in list view.
        List <Movie> movieList = movieRegister.searchGenre(genreBox.getValue());
        if (movieList.isEmpty()){ errorActivation("No movies with genre " + genreBox.getValue());}
        for (Movie movie : movieList) {
            movieRegisterList.getItems().add(movie.toString());
        }
    }

    @FXML
    private void selectMovie(MouseEvent event){
        //Displays a movie when it is selected if a user is logged in. This allows for rating and sets values for rating:
        if (movieRegisterList.getSelectionModel().getSelectedItem() != null && this.user != null){
            this.movie = convertSelectedItemToMovieObject();
            movieLabel.setText(": " + this.movie.getTitle());
            setRateVisibility(true, this.movie);
        }
    }

    private Movie convertSelectedItemToMovieObject(){
        //Retrives movie object from convertObservableList:
        movieRegisterList.getSelectionModel().getSelectedItem();
        String[] movieStr = ((String) movieRegisterList.getSelectionModel().getSelectedItem()).split("\t");
        return this.movieRegister.getMovie(movieStr[0], movieStr[1]);
    }

    @FXML
    private void handleAddMovieToRegister(){
        //Adds a new movie to the register and writes it to file, given that the input is valid and a user is logged in:
        try {
            //Fails is user is not logged in:
            isLoggedIn();

            //Fails if not valid input to generate movie object:
            new Movie(movieName.getText(), genreBox.getValue());

        } catch (Exception e) {
            errorActivation(e.getMessage());
        }
        this.movie = new Movie(movieName.getText(), genreBox.getValue());
        movieLabel.setText(": " + this.movie.getTitle());
        setRateVisibility(true, this.movie);
    }

    private void isLoggedIn(){
        //Throws IllegalState if user isn't logged in.
        if (this.user.equals(null)){
            throw new IllegalStateException("User not logged in.");
        }
    }

    @FXML
    private void handleRateButton(){
        //Saves new rating and writes this to file.
        this.movie.addRating(rateBox.getValue());
        this.movieRegister.updateMovie(movie);

    }

    //Error message
    private void errorActivation(String message) {
        //When called, displays a warning message
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Movie Rating");
        alert.setContentText(message);
        alert.showAndWait();
    }   
}
