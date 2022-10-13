package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import core.Movie;
import core.User;
import data.MovieRegister;
import data.UserRegister;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;


public class MovieRatingControllerTest extends ApplicationTest{

    private MovieRatingController controller;
    private Parent root;
    private static ArrayList<Movie> movieRegister = new ArrayList<Movie>();
    private static ArrayList<User> userRegister = new ArrayList<User>();
    private static List<String> genresList = Arrays.asList("action", "comedy", "drama", "fantasy", "horror", "mystery", "romance", "thriller"); 
    private static List<Integer> ratingList = Arrays.asList(1, 2, 3, 4, 5);  

    private TextField username, password, movieName;
    private Button logIn, addMovieRegister, createUser, searchMovie, addMovieToRegister, logOut, rateButton;
    private ChoiceBox<String> genreBox;
    private ChoiceBox<Integer> rateBox;
    private ListView<String> movieRegisterList;
    private TextArea ratedMovie;
    private Label loggedIn, loggedOut, usernameLabel, passwordLabel, rateLabel, movieLabel, ratingscaleLabel;


    @BeforeEach
    public void intitFields(){
        username = lookup("#username").query();
        password = lookup("#password").query();
        movieName = lookup("#movieName").query();
        logIn = lookup("#logIn").query();
        addMovieRegister = lookup("#addMovieRegister").query();
        createUser = lookup("#createUser").query();
        searchMovie = lookup("#searchMovie").query();
        //addMovieToRegister = lookup("#addMovieToRegister").query();
        logOut = lookup("#logOut").query();
        rateButton = lookup("#rateButton").query();
        genreBox = lookup("#genreBox").query();
        rateBox = lookup("#rateBox").query();
        movieRegisterList = lookup("#movieRegisterList").query();
        ratedMovie = lookup("#ratedMovie").query();
        loggedIn = lookup("#loggedIn").query();
        loggedOut = lookup("#loggedOut").query();
        usernameLabel = lookup("#usernameLabel").query();
        passwordLabel = lookup("#passwordLabel").query();
        rateLabel = lookup("#rateLabel").query();
        movieLabel = lookup("#movieLabel").query();
        ratingscaleLabel = lookup("#ratingscaleLabel").query();   
    }

    @BeforeEach
    public void initeUserAndMovie() {
        // MovieRegister movieRegister = new MovieRegister();
        // List <Movie> movieList = movieRegister.searchMovieTitle(movieName.getText());
        // UserRegister userRegister = new UserRegister();
        // User user1 = new User("Pauline", "1234567");
        // Movie movie1 = new Movie("The Notebook", "romance");
        // movieRegister.addMovie(movie1);
        // movieList.add(movie1);
        // movie1.addRating(5);
        // user1.rateMovie(movie1, 5);
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MovieRating.fxml"));
        root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    @DisplayName("Test to create new user")
    public void testCreateNewUser() {
        clickOn(username).write("Pauline");
        clickOn(password).write("1234567");
        clickOn(createUser);
        //da skal dette skje
        // assertFalse(logIn.isVisible());
        // assertTrue(loggedIn.isVisible());
        // assertTrue(addMovieToRegister.isVisible());
        // assertTrue(logOut.isVisible());
        //her - test som sjekker at denne brukeren blir lagt til i opprettede brukere

    }

    @Test
    @DisplayName("Test when typing in empty username")
    public void testCreateNewUserFail() {
        clickOn(username).write("");
        clickOn(password).write("1234567");
        clickOn(createUser);
        // da skal errorMessage vises
        //Assertions.assertEquals("Brukernavn og passord kan ikke være tomme",controller.errorActivation(message.getText());
    }

      
    @Test
    @DisplayName("Test successful login")
    public void testLogIn() {
        clickOn(username).write("Pauline");
        clickOn(password).write("1234567");
        clickOn(logIn);
        //da skal dette skje
        // assertFalse(logIn.isVisible());
        // assertTrue(loggedIn.isVisible());
        // assertTrue(addMovieRegister.isVisible());
        // assertTrue(logOut.isVisible());
    }

    @Test
    @DisplayName("Test log out")
    public void testLogOut() {
        //logge inn
        testCreateNewUser();
        //logge ut
        clickOn(logOut);
        //da skal dette skje
        // assertTrue(loggedOut.isVisible());
        // assertFalse(logOut.isVisible());
        // assertTrue(logIn.isVisible());

        //da skal alle disse gjøres usynlige

        //hele siden hvor man kan legge til rating skal bli usynlig
        // assertFalse(addMovieRegister.isVisible());
        // assertFalse(rateLabel.isVisible());
        // assertFalse(ratingscaleLabel.isVisible());
        // assertFalse(rateBox.isVisible());
        // assertFalse(rateButton.isVisible());
        // assertFalse(ratedMovie.isVisible());

        //knappen for å legge til film i registeret skal bli usynlig
    }


    @Test
    @DisplayName("Sucessful search for a movie")
    public void searchMovie() {
        clickOn(movieName).write("The Notebook");
        clickOn(searchMovie);
        //assertEquals("The Notebook", ratedMovie.getText());
    }

    @Test
    @DisplayName("Unsucessful search for a movie")
    public void searchMovieNotFound() {
        clickOn(movieName).write("The Notebook");
        clickOn(searchMovie);
        //assertEquals("No movies with the title" + movieName.getText(), controller.errorActivation().getText());
    }

    @Test
    @DisplayName("Test to add a movie to the register with rating")
    public void addMovieToRegister() {
        // clickOn(searchMovie).write("The Notebook");
        // clickOn(genreBox).clickOn("action");
        // clickOn(addMovieToRegister);
        // clickOn(rateBox).clickOn("5");
        // clickOn(rateButton);
        //assertEquals("The Notebook; action; 5", movieRegisterList.getItems());
    }
}