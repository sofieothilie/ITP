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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import core.User;

public class MovieRatingAppTest extends ApplicationTest{

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
        addMovieToRegister = lookup("#addMovieToRegister").query();
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

    private MovieRatingController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("App.fxml"));
        root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    @DisplayName("Test to create new user")
    public void testCreateNewUser() {
        clickOn("#username").write("Pauline");
        clickOn("#password").write("1234567");
        clickOn("#createUser");
        assertTrue(loggedIn.isVisible());
        assertTrue(addMovieRegister.isVisible());
    }

    @Test
    public void createTestUsers() throws IOException {
        try {
            clickOn(username).write("Pauline");
            clickOn(password).write("1234567");
            clickOn(createUser);
        } catch (Exception e) {
          e.getMessage();
        }
      }
      
    @Test
    @DisplayName("Test if the user can log in")
    public void testLogIn() {
        clickOn(username).write("Pauline");
        clickOn(password).write("1234567");
        clickOn(logIn);
    }

    @Test
    @DisplayName("Test if the user can log out")
    public void testLogOut() {
        clickOn(username).write("Pauline");
        clickOn(password).write("1234567");
        clickOn(createUser);
        clickOn(logOut);
        assertTrue(loggedOut.isVisible());
        assertFalse(addMovieRegister.isVisible());
    }


    @Test
    @DisplayName("Test to search for a movie")
    public void searchMovie() {
        clickOn(movieName).write("The Godfather");
        clickOn(searchMovie);
        assertTrue(ratedMovie.isVisible());
    }

    @Test
    @DisplayName("Test to add a movie to the register with rating")
    public void addMovieToRegister() {
        clickOn(searchMovie).write("The Notebook");
        clickOn(genreBox).clickOn("action");
        clickOn(addMovieToRegister);
        clickOn(rateBox).clickOn("5");
        clickOn(rateButton);
    }
}
        
