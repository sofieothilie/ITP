package ui;

import java.util.ArrayList;
import java.util.List;

import core.Movie;
import core.MovieHandler;
import core.MovieRegister;
import core.User;
import core.Users;

import javafx.collections.ObservableList;
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
    private String userNameString;
    private Integer newRating;

    private User user;
    private Users users;
    private Movie movie;
    private MovieRegister movieRegister;
    private MovieHandler movieHandler;
    private ObservableList<String> observableMovie;

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
    private Label loggedIn, loggedOut, usernameLabel, passwordLabel, rateLabel, movieLabel;



    // Methods

    @FXML
    public void initialize() {
        //Starts up the app with correct visibility:
        setLoginPossibility(true);
        setSearchVisibility(true);
        setRateVisibility(false, null);        
    }
    
    private void setLoginPossibility(boolean value){
        //Sets the log-in-area to desired visibility:
        usernameLabel.visibleProperty().set(value);
        passwordLabel.visibleProperty().set(value);
        username.visibleProperty().set(value);
        username.clear();
        password.visibleProperty().set(value);
        password.clear();
        logIn.visibleProperty().set(value);
        createUser.visibleProperty().set(value);
        loggedOut.visibleProperty().set(!value);
        loggedIn.visibleProperty().set(value);
    }

    private void setSearchVisibility(boolean value){
        //Sets the search-area to desired visibility:
        searchMovie.visibleProperty().set(true);
        genreBox.visibleProperty().set(true);
        movieRegisterList.visibleProperty().set(true);
        if (!this.user.equals(null)){
            addMovieRegister.visibleProperty().set(value);
            //Denne må vurderes. hvordan skal den implementeres
        }
    }

    private void setRateVisibility(boolean value, Movie movie){
        //Sets the rate-area to desired visibility:
        rateBox.visibleProperty().set(value);
        rateButton.visibleProperty().set(value);
        movieLabel.visibleProperty().set(value);
        ratedMovie.visibleProperty().set(value);
        rateLabel.visibleProperty().set(value);
        if (!movie.equals(null) && value == true){
            ratedMovie.setText(movie.getTitle());
        }
    }
    
    //User methods
    @FXML
    private void handleLogIn(){
        //Tries to log in a user. If user excists: sets correct fields and visibility status.
        try {
            this.users.validUser(username.getText(), password.getText());      
        } catch (Exception e) {
            errorActivation(e.getMessage());
        }
        this.user = this.users.getUser(username.getText());
        setLoginPossibility(false);
    }

    @FXML
    private void handleCreateUser(){
        //Creates a new user and sets desired fields and visibility.
        try {
            this.users.existingUser(username.getText(), password.getText());      
        } catch (Exception e) {
            errorActivation(e.getMessage());
        }
        this.user = new User(username.getText(), password.getText());
        try {
            this.users.registerNewUser(this.user);
        } catch (Exception e) {
            errorActivation(e.getMessage());
        }
        setLoginPossibility(false); 
    }


    @FXML 
    private void handleLogOut(){
        //Logs user out. Resets desired fields and sets desired visibility.
        this.user = null;
        setLoginPossibility(true); 
        setRateVisibility(false, null); 
    }

    //Movie methods
    @FXML
    private void handleSearchMovie(){
        //Searches for movies by title and displays them in list view.
        List <Movie> movieList = movieRegister.searchMovieTitle(movieName.getText());
        for (Movie movie : movieList) {
            movieRegisterList.getItems().add(movie.toString());
        }
    }

    @FXML
    private void handleSearchGenre(){ //MANGLER I FXML
        //Searches for movies by genre and displays them in list view.
        List <Movie> movieList = movieRegister.searchGenre(genreBox.getValue());
        for (Movie movie : movieList) {
            movieRegisterList.getItems().add(movie.toString());
        }
    }

    @FXML
    private void selectMovie(MouseEvent event){
        //Displays a movie when it is selected if a user is logged in. This allows for rating.
        try {
            this.user.getUsername(); //fails if user is not logged in.
            
        } catch (Exception e) {
            errorActivation(e.getMessage());
        }
        //Sets values for rating:
        this.movie = convertSelectedItemToMovieObject();
        movieLabel.setText(": " + this.movie.getTitle());
        setRateVisibility(true, this.movie); 
    }

    private Movie convertSelectedItemToMovieObject(){
        //Retrives movie object from convertObservableList:
        String[] movieStr = ((String) movieRegisterList.getSelectionModel().getSelectedItem()).split("-");
        return this.movieRegister.getMovie(movieStr[0], movieStr[1]);
    }

    @FXML
    private void handleAddMovieToRegister(){
        //Adds a new movie to the register, given that the input is valid and a user is logged in:
        try {
            //Fails is user is not logged in:
            loggedIn();

            //Fails if not valid input to generate movie object:
            new Movie(movieName.getText(), genreBox.getValue());

        } catch (Exception e) {
            errorActivation(e.getMessage());
        }
        this.movie = new Movie(movieName.getText(), genreBox.getValue());
        this.movieHandler.addMovie(movie); //Denne må også lagres og skrives til fil
        selectMovie(null);
    }

    private void loggedIn(){
        //Throws IllegalState if user isn't logged in.
        if (this.user.equals(null)){
            throw new IllegalStateException("User not logged in.");
        } //TODO
    }

    @FXML
    private void handleRateButton(){
        //Saves new rating and writes this to file.
        this.movie.updateRating(rateBox.getValue());
        this.movieHandler.updateRating(this.movie.getTitle(), rateBox.getValue());
        //Trengs begge? Er movieHandler rett?

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
