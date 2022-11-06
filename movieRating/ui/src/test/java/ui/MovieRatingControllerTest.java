package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import data.MovieHandler;
import data.UserHandler;
import java.io.IOException;
import java.nio.file.Files;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

public class MovieRatingControllerTest extends ApplicationTest {

  private FxRobot robot = new FxRobot();

  private final String userFilename = "userTest";
  private final String movieFilename = "movieTest";

  
  private TextField username, password, movieName;
  private Button logIn, logOut, createUser, addMovieRegister, rateButton;
  private ChoiceBox<String> genreBox;
  private ChoiceBox<Integer> rateBox;
  private TextArea ratedMovie;
  private Label loggedIn, loggedOut, rateLabel, movieLabel, ratingscaleLabel;


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
    rateLabel = lookup("#rateLabel").query();
    movieLabel = lookup("#movieLabel").query();
    ratingscaleLabel = lookup("#ratingscaleLabel").query();
  }

  @BeforeEach
  public void initeUserAndMovie() {
    // MovieRegister movieRegister = new MovieRegister();
    // List <Movie> movieList = movieRegister.searchMovieTitle(movieName.getText());
    //UserRegister userRegister = new UserRegister();
    //User user1 = new User("Pauline", "1234567");
    // Movie movie1 = new Movie("The Notebook", "romance");
    // movieRegister.addMovie(movie1);
    // movieList.add(movie1);
    // movie1.addRating(5);
    // user1.rateMovie(movie1, 5);
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MovieRating.fxml"));
    MovieRatingController controller = new MovieRatingController("userTest", "movieTest");
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
 
  @Test
  @DisplayName("Test to create new user") 
  public void testCreateNewUser() {
    robot.clickOn(username).write("Pauline");
    robot.clickOn(password).write("1234567");
    robot.clickOn(createUser);
    sleep(500);
    //da skal dette skje
    assertFalse(logIn.isVisible());
    assertTrue(loggedIn.isVisible());
    assertTrue(addMovieRegister.isVisible());
    assertTrue(logOut.isVisible());

  }

  private void createUser() {
    robot.clickOn(this.username).write("Pauline");
    robot.clickOn(this.password).write("1234567");
    robot.clickOn(createUser);
    sleep(500);
  }
  
  @Test
  @DisplayName("Test to create user with unvalid input")
  public void testFailCreateUser(){
    robot.clickOn(username).write("");
    robot.clickOn(password).write("1234567");
    robot.clickOn(createUser);
    sleep(500);
    this.closeAlert();
  }

  @Test
  @DisplayName("Test log out")
  public void testLogOut() {
    createUser();
    sleep(700);
    robot.clickOn(logOut);
    assertTrue(loggedOut.isVisible());
    assertFalse(logOut.isVisible());
    assertTrue(logIn.isVisible());
    assertFalse(addMovieRegister.isVisible());
    assertFalse(rateLabel.isVisible());
    assertFalse(ratingscaleLabel.isVisible());
    assertFalse(rateBox.isVisible());
    assertFalse(rateButton.isVisible());
    assertFalse(ratedMovie.isVisible());
  }

  @Test 
  @DisplayName("Test to log in after creating an user")
  public void logInFirstTime(){
    createUser();
    sleep(700);
    robot.clickOn(logOut);
    sleep(700);
    robot.clickOn(username).write("Pauline");
    robot.clickOn(password).write("1234567");
    robot.clickOn(logIn);
    sleep(700);
    assertTrue(loggedIn.isVisible());
  }

  @Test
  @DisplayName("Test to try to create user with existing username")
  public void testCreateUserAlreadyExists() {
    createUser();
    robot.clickOn(logOut);
    sleep(500);
    robot.clickOn(username).write("Pauline");
    robot.clickOn(password).write("1234567");
    robot.clickOn(createUser);
    sleep(500);
    this.closeAlert();
  }
  
  @Test
  @DisplayName("Test to add a movie to the register")
  public void addMovie(){
    createUser();
    sleep(500);
    robot.clickOn(movieName).write("Titanic");
    robot.clickOn(genreBox).clickOn("romance");
    sleep(500);
    robot.clickOn(addMovieRegister);
    sleep(400);
    movieName.clear();
    robot.clickOn(movieName).write("Titanic");
    robot.clickOn(genreBox).clickOn("romance");
    sleep(500);
    robot.clickOn(addMovieRegister);
    //this.closeAlert();
  }
  //*/

  // @Test
  // @DisplayName("Sucessful search for a movie")
  // public void searchMovie() {
  //     robot.clickOn(movieName).write("The Notebook");
  //     robot.clickOn(searchMovie);
  //     assertEquals("The Notebook", ratedMovie.getText());
  // }

  // @Test
  // @DisplayName("Unsucessful search for a movie")
  // public void searchMovieNotFound() {
  //     clickOn(movieName).write("The Notebook");
  //     clickOn(searchMovie);
  //     //assertEquals("No movies with the title" + movieName.getText(), controller.errorActivation().getText());
  // }


  ///* 
  @Test
  @DisplayName("Test to add a movie to the register with rating")
  public void addMovieToRegister() {
    createUser();
    robot.clickOn(movieName).write("The Notebook");
    robot.clickOn(genreBox).clickOn("romance");
    robot.clickOn(addMovieRegister);
    robot.clickOn(rateBox).clickOn("5");
    robot.clickOn(rateButton);
    this.closeAlert();
    
    assertEquals("", movieName.getText());;
    assertEquals("", movieLabel.getText());
    assertEquals(null, ratedMovie.getText());
    assertEquals(null, genreBox.getValue());
    assertEquals(null, rateBox.getValue());
  }
  //*/

  ///* 
  @AfterEach
  @DisplayName("After each test reset files")
  public void resetData() {
    UserHandler userHandler = new UserHandler(userFilename);
    MovieHandler movieHandler = new MovieHandler(movieFilename);
    try {
      if(userHandler.fileExists()){
        Files.delete(userHandler.getFile().toPath());
      }
      if (movieHandler.fileExists()) {
        Files.delete(movieHandler.getFile().toPath());
      }
    } catch (IOException e) {
      throw new IllegalArgumentException();
    }
  }
  //*/
}
