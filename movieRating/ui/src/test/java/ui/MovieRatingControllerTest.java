package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.Movie;
import core.User;
import data.MovieRegister;
import data.UserRegister;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
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
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterAll;
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

public class MovieRatingControllerTest extends ApplicationTest {

  private FxRobot robot = new FxRobot();
  //private static MovieRegister movieRegister = new MovieRegister();
  //private static UserRegister userRegister = new UserRegister();
  private static List<String> genresList = Arrays.asList(
    "action",
    "comedy",
    "drama",
    "fantasy",
    "horror",
    "mystery",
    "romance",
    "thriller"
  );
  private static List<Integer> ratingList = Arrays.asList(1, 2, 3, 4, 5);


  private MovieRatingController controller = new MovieRatingController("userregister", "movieregister");
  
  private TextField username, password, movieName;
  private Button logIn, logOut, createUser, addMovieRegister, rateButton;
  private ChoiceBox<String> genreBox;
  private ChoiceBox<Integer> rateBox;
  private TextArea ratedMovie;
  private Label loggedIn, loggedOut, usernameLabel, passwordLabel, rateLabel, movieLabel, ratingscaleLabel;


  @BeforeEach
  public void intitFields() {
    username = lookup("#username").query();
    password = lookup("#password").query();
    movieName = lookup("#movieName").query();
    logIn = lookup("#logIn").query();
    addMovieRegister = lookup("#addMovieRegister").query();
    createUser = lookup("#createUser").query();
    //searchMovie = lookup("#searchMovie").query();
    //addMovieToRegister = lookup("#addMovieToRegister").query();
    logOut = lookup("#logOut").query();
    rateButton = lookup("#rateButton").query();
    genreBox = lookup("#genreBox").query();
    rateBox = lookup("#rateBox").query();
    //movieRegisterList = lookup("#movieRegisterList").query();
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
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MovieRating.fxml"));
    MovieRatingController controller = new MovieRatingController("userregister", "movieregister");
    loader.setController(controller);
    Parent parent = loader.load();
    stage.setScene(new Scene(parent));
    stage.show();
  }

  /** 
  *closes an alert window
  */
  private void closeAlert(){
    robot.clickOn("OK");
  }

  /*
  @Test
  @DisplayName("Test to create new user") 
  public void testCreateNewUser() {
    robot.clickOn(username).write("adfgslajb");
    robot.clickOn(password).write("1234567");
    robot.clickOn(createUser);
    sleep(500);
    //da skal dette skje
    assertFalse(logIn.isVisible());
    assertTrue(loggedIn.isVisible());
    assertTrue(addMovieRegister.isVisible());
    assertTrue(logOut.isVisible());
  }
  */


  /* 
  @Test
  @DisplayName("Test to create user with unvalid input")
  public void testFailCreateUser(){
    robot.clickOn(username).write("");
    robot.clickOn(password).write("1234567");
    robot.clickOn(createUser);
    sleep(500);
    this.closeAlert();
  }

  */

  ///* 
  @Test
  @DisplayName("Test log out")
  public void testLogOut() {
    robot.clickOn(username).write("aadfgdw");
    robot.clickOn(password).write("1234567");
    robot.clickOn(createUser);
    sleep(700);
      //logge ut
      robot.clickOn(logOut);
      //da skal dette skje
      assertTrue(loggedOut.isVisible());
      assertFalse(logOut.isVisible());
      assertTrue(logIn.isVisible());

      //da skal alle disse gjøres usynlige

      //hele siden hvor man kan legge til rating skal bli usynlig
      assertFalse(addMovieRegister.isVisible());
      assertFalse(rateLabel.isVisible());
      assertFalse(ratingscaleLabel.isVisible());
      assertFalse(rateBox.isVisible());
      assertFalse(rateButton.isVisible());
      assertFalse(ratedMovie.isVisible());

      //knappen for å legge til film i registeret skal bli usynlig
  }
  //*/

  

  /* 
  @Test
  @DisplayName("Test to try to create user with existing username")
  public void testCreateUserAlreadyExists() {
    robot.clickOn(username).write("aafahbh");
    robot.clickOn(password).write("1234567");
    robot.clickOn(createUser);
    robot.clickOn(logOut);
    sleep(500);
    robot.clickOn(username).write("aafahbh");
    robot.clickOn(password).write("1234567");
    robot.clickOn(createUser);
    sleep(500);
    this.closeAlert();
  }
  */
  

  /* 
  @Test
  @DisplayName("Test to add a movie to the register")
  public void addMovie(){
    //controller.loggedIn(true);

    robot.clickOn(username).write("fgddw");
    robot.clickOn(password).write("1234567");
    robot.clickOn(createUser);

    sleep(500);
    robot.clickOn(movieName).write("Titaicccccccc");
    robot.clickOn(genreBox).clickOn("romance");
    sleep(500);
    robot.clickOn(addMovieRegister);
    sleep(400);

    movieName.clear();
    robot.clickOn(movieName).write("Titaicccccccc");
    robot.clickOn(genreBox).clickOn("romance");
    sleep(500);
    robot.clickOn(addMovieRegister);
    this.closeAlert();
  }
  */

  // @Test
  // @DisplayName("Sucessful search for a movie")
  // public void searchMovie() {
  //     clickOn(movieName).write("The Notebook");
  //     clickOn(searchMovie);
  //     //assertEquals("The Notebook", ratedMovie.getText());
  // }

  // @Test
  // @DisplayName("Unsucessful search for a movie")
  // public void searchMovieNotFound() {
  //     clickOn(movieName).write("The Notebook");
  //     clickOn(searchMovie);
  //     //assertEquals("No movies with the title" + movieName.getText(), controller.errorActivation().getText());
  // }


  /* 
  @Test
  @DisplayName("Test to add a movie to the register with rating")
  public void addMovieToRegister() {
    robot.clickOn(movieName).write("The Notebook");
    robot.clickOn(genreBox).clickOn("action");
    robot.clickOn(addMovieRegister);
    robot.clickOn(rateBox).clickOn("5");
    robot.clickOn(rateButton);
    assertEquals("The Notebook; action; 5", controller.movieRegisterList.getItems());
  }
  */

  // @AfterAll
  //   private void reset() {
  //       controller.userFilename.reset();
  //       controller.movieFilename.reset();

  //   }
}
