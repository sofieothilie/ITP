package ui;

import java.util.Arrays;
import java.util.List;

import core.Movie;
import core.MovieRegister;
import core.User;
import core.UserHandling;
import core.UserRegister;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class MovieRatingController {
    //Fields
    private User user;
    private UserRegister userRegister = new UserRegister();
    private Movie movie;
    private UserHandling userHandling = new UserHandling();
    private MovieRegister movieRegister = new MovieRegister();
    private static List<String> genresList = Arrays.asList("action", "comedy", "drama", "fantasy", "horror", "mystery", "romance", "thriller"); 
    private static List<Integer> ratingList = Arrays.asList(1, 2, 3, 4, 5);   

    //FXML fields
    @FXML
    private TextField username, password, movieName;
    @FXML
    private Button addMovieRegister, createUser, addMovieToRegister, logOut, rateButton;
    @FXML
    private ChoiceBox<String> genreBox;
    @FXML
    private ChoiceBox<Integer> rateBox;
    @FXML
    private TextArea ratedMovie;
    @FXML
    private Label loggedIn, loggedOut, usernameLabel, passwordLabel, rateLabel, movieLabel, ratingscaleLabel;


    private void clearAllSearchFields(){
        movieName.clear();
        movieLabel.setText("");
        ratedMovie.setText(movie.toString());
        genreBox.setValue(null);
        rateBox.setValue(null);
        
    }
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

        createUser.visibleProperty().set(value);
    }

    private void loggedIn(boolean value){
        loggedOut.visibleProperty().set(!value);
        loggedIn.visibleProperty().set(value);
        addMovieRegister.setVisible(value);
    }

    private void setSearchVisibility(boolean value){
        //Sets the search-area to desired visibility:
        genreBox.visibleProperty().set(true);
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
            ratedMovie.setText(movie.toString());
        }
    }
    
    //User methods

    @FXML
    private void handleCreateUser(){
        //Creates a new user and sets desired fields and visibility.
        if (!(this.userRegister.existingUser(username.getText(), password.getText()))){
            this.user = new User(username.getText(), password.getText());  
            setLoginPossibility(false); 
            loggedIn(true); 
        }
        else{errorActivation("User already exists.");}
        
    }


    @FXML 
    private void handleLogOut(){
        //Logs user out. Resets desired fields and sets desired visibility.
        this.user = null;
        setLoginPossibility(true); 
        setRateVisibility(false, null); 
        loggedIn(false);
        clearAllSearchFields();
    }

    //Movie methods

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
        this.user.rateMovie(movie, rateBox.getValue());
        this.userHandling.writeUserToRegister(user);
        confirmationActivation("You rated " + this.movie.getTitle() + ": " + rateBox.getValue());
        clearAllSearchFields();
    }

    //Error message
    private void errorActivation(String message) {
        //When called, displays a warning message
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Movie Rating");
        alert.setContentText(message);
        alert.showAndWait();
    }   
    private void confirmationActivation(String message) {
        //When called, displays a warning message
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Movie Rating");
        alert.setContentText(message);
        alert.showAndWait();
    }   


}
