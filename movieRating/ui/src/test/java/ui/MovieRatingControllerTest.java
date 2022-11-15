package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import data.MovieHandler;
import data.MovieRegister;
import data.UserHandler;
import data.UserRegister;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import core.Movie;
import core.User;

public class MovieRatingControllerTest extends ApplicationTest {

  MovieRatingController controller = new MovieRatingController("userTest", "movieTest");

  private FxRobot robot = new FxRobot();
  

  private final String userFilename = "userTest";
  private final String movieFilename = "movieTest";

  
  private Pane ratePane;
  private Pane searchPane;
  private Pane ratedMoviesPane;
  

  private PasswordField password;

  private TextField username;
  private TextField movieName;

  private Button logIn;
  private Button createUser;
  private Button logOut;
  private Button rateButton;
  private Button createUserDone;
  private Button backToLogIn;
  private Button searchMovie;
  private Button resetButton;
  private Button addRatingButton;
  private Button cancelRatingButton;
  private Button deleteRatingButton;

  
  private ChoiceBox<String> genreBox;
  private ChoiceBox<Integer> rateBox;
  
  private TextArea ratedMovie;

  private ListView<Object> moviesFound;
  private ListView<Object> moviesRated;

  private Label loggedIn;
  private Label loggedOut;
  private Label usernameLabel;
  private Label passwordLabel;
  private Label rateLabel;
  private Label movieLabel;
  private Label ratingscaleLabel;
  private Label createNewUserText;
  private Label newUserLabel;
  private Label infoUserLabel;



  @BeforeEach
  public void intitFields() {
    ratePane = lookup("#ratePane").query();
    searchPane = lookup("#searchPane").query();
    ratedMoviesPane = lookup("#ratedMoviesPane").query();

    password = lookup("#password").query();

    username = lookup("#username").query();
    movieName = lookup("#movieName").query();

    logIn = lookup("#logIn").query();
    createUser = lookup("#createUser").query();
    logOut = lookup("#logOut").query();
    rateButton = lookup("#rateButton").query();
    createUserDone = lookup("#createUserDone").query();
    backToLogIn = lookup("#backToLogIn").query();
    searchMovie = lookup("#searchMovie").query();
    resetButton = lookup("#resetButton").query();
    addRatingButton = lookup("#addRatingButton").query();
    cancelRatingButton = lookup("#cancelRatingButton").query();
    deleteRatingButton = lookup("#deleteRatingButton").query();

    ratedMovie = lookup("#ratedMovie").query();

    genreBox = lookup("#genreBox").query();
    rateBox = lookup("#rateBox").query();

    loggedIn = lookup("#loggedIn").query();
    loggedOut = lookup("#loggedOut").query();
    usernameLabel = lookup("#usernameLabel").query();
    passwordLabel = lookup("#usernameLabel").query();
    rateLabel = lookup("#rateLabel").query();
    movieLabel = lookup("#movieLabel").query();
    ratingscaleLabel = lookup("#ratingscaleLabel").query();
    createNewUserText = lookup("#createNewUserText").query();
    newUserLabel = lookup("#newUserLabel").query();
    infoUserLabel = lookup("#infoUserLabel").query();
    
  }

  @BeforeEach
  public void initeUserAndMovie() {

    UserHandler userHandler = new UserHandler(userFilename);
    MovieHandler movieHandler = new MovieHandler(movieFilename);
    UserRegister userRegister = new UserRegister(userFilename, movieFilename);

    //MovieRegister movieRegister = new MovieRegister(movieFilename);

    User user1 = new User("per", "123");
    userHandler.writeUserToRegister(user1);

    List<Integer> allRatings = new ArrayList<>();
    allRatings.add(3);

    Movie movie1 = new Movie("The Notebook", "romance", allRatings);
    Movie movie2 = new Movie("Harry Potter", "fantasy");
    Movie movie3 = new Movie("Madagaskar", "drama", allRatings);

    movieHandler.writeMovieToRegister(movie1);
    movieHandler.writeMovieToRegister(movie2);
    movieHandler.writeMovieToRegister(movie3);

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
 
  /* 
  @Test
  @DisplayName("Test to create new user") 
  public void testCreateNewUser() {
    robot.clickOn(createUser);
    assertFalse(searchPane.isVisible());
    assertTrue(createUserDone.isVisible());
    assertTrue(backToLogIn.isVisible());
    assertTrue(createNewUserText.isVisible());
    robot.clickOn(createUserDone);
    this.closeAlert();

    robot.clickOn(password).write("1234567");
    robot.clickOn(createUserDone);
    sleep(500);
    this.closeAlert();
    username.clear();
    password.clear();

    robot.clickOn(createUser);
    robot.clickOn(username).write("hello");
    robot.clickOn(createUserDone);
    sleep(500);
    this.closeAlert();
    username.clear();
    password.clear();

    robot.clickOn(username).write("per");
    robot.clickOn(password).write("123");
    robot.clickOn(createUserDone);
    sleep(500);
    this.closeAlert();
    username.clear();
    password.clear();

    robot.clickOn(createUser);
    robot.clickOn(username).write("NTNU");
    robot.clickOn(password).write("123");
    robot.clickOn(createUserDone);
    sleep(500);
    assertTrue(loggedIn.isVisible());
    assertTrue(logOut.isVisible());
    assertTrue(ratedMoviesPane.isVisible());
    assertFalse(createUserDone.isVisible());
    assertFalse(backToLogIn.isVisible());
    assertFalse(createNewUserText.isVisible());
    assertFalse(username.isVisible());
    assertFalse(usernameLabel.isVisible());
    assertFalse(password.isVisible());
    assertFalse(passwordLabel.isVisible());
  }
  //*/
  
  private void createUserStandard() {
    robot.clickOn(createUser);
    robot.clickOn(this.username).write("Sina");
    robot.clickOn(this.password).write("123");
    robot.clickOn(createUserDone);
    sleep(500);
  }

  private void logInStandard(){
    robot.clickOn(this.username).write("per");
    robot.clickOn(this.password).write("123");
    robot.clickOn(logIn);
  }

  /* 
  @Test 
  @DisplayName("Test to log in")
  public void logIn(){
    robot.clickOn(this.username).write("elizabeth");
    robot.clickOn(this.password).write("123");
    robot.clickOn(logIn);
    this.closeAlert();
    username.clear();
    password.clear();

    robot.clickOn(this.password).write("123");
    robot.clickOn(logIn);
    this.closeAlert();

    robot.clickOn(this.username).write("fanny");
    robot.clickOn(logIn);
    this.closeAlert();
    username.clear();
    password.clear();

    robot.clickOn(createUser);
    assertFalse(searchPane.isVisible());
    assertTrue(createUserDone.isVisible());
    assertTrue(backToLogIn.isVisible());
    assertTrue(createNewUserText.isVisible());
    robot.clickOn(backToLogIn);
    assertFalse(createUserDone.isVisible());
    assertFalse(backToLogIn.isVisible());
    assertFalse(createNewUserText.isVisible());
    assertTrue(searchPane.isVisible());

    robot.clickOn(this.username).write("per");
    robot.clickOn(this.password).write("123");
    robot.clickOn(logIn);
    assertFalse(logIn.isVisible());
    assertFalse(createUser.isVisible());
    assertFalse(username.isVisible());
    assertFalse(usernameLabel.isVisible());
    assertFalse(password.isVisible());
    assertFalse(passwordLabel.isVisible());
    assertTrue(loggedIn.isVisible());
    assertTrue(logOut.isVisible());
    assertTrue(ratedMoviesPane.isVisible());
  }
  //*/

   /* 
  @Test
  @DisplayName("Test log out")
  public void testLogOut() {
    logInStandard();
    sleep(700);
    robot.clickOn(logOut);
    assertTrue(loggedOut.isVisible());
    assertFalse(logOut.isVisible());
    assertFalse(ratedMoviesPane.isVisible());
    assertFalse(ratePane.isVisible());
    assertTrue(logIn.isVisible());
    assertTrue(createUser.isVisible()); 
    assertTrue(username.isVisible());
    assertTrue(usernameLabel.isVisible());
    assertTrue(password.isVisible());
    assertTrue(passwordLabel.isVisible());
  }
  //*/

  /* 
  @Test
  public void testcreateUserandLogIn() {
    createUserStandard();
    sleep(700);
    robot.clickOn(logOut);
    sleep(700);
    robot.clickOn(username).write("Sina");
    robot.clickOn(password).write("123");
    robot.clickOn(logIn);
    sleep(700);
    assertTrue(loggedIn.isVisible());
    assertTrue(ratedMoviesPane.isVisible());
  }
  //*/

  /* 
  @Test
  @DisplayName("Test to search up movies in register")
  public void testsearchMovie() {
    robot.clickOn(searchMovie);
    this.closeAlert();

    robot.clickOn(genreBox).clickOn("horror");
    robot.clickOn(searchMovie);
    this.closeAlert();
    robot.clickOn(movieName).write("Impressive");
    robot.clickOn(searchMovie);
    this.closeAlert();

    robot.clickOn(movieName).write("Zavannah");
    robot.clickOn(genreBox).clickOn("horror");
    robot.clickOn(resetButton);
    assertEquals("", movieLabel.getText());
    assertEquals(null, genreBox.getValue());

    robot.clickOn(genreBox).clickOn("fantasy");
    robot.clickOn(searchMovie);
    clickOn(LabeledMatchers.hasText("Harry Potter; fantasy; 0.0"));
    this.closeAlert();

    logInStandard();
    clickOn(LabeledMatchers.hasText("Harry Potter; fantasy; 0.0"));
    assertTrue(ratePane.isVisible());
    assertEquals(": Harry Potter", movieLabel.getText());
    assertEquals("Harry Potter; fantasy; 0.0", ratedMovie.getText());
  }
  //*/
  
  /* 
  @Test
  @DisplayName("Test to add a rating")
  public void testAddRating(){
    logInStandard();
    robot.clickOn(genreBox).clickOn("romance");
    robot.clickOn(searchMovie);
    clickOn(LabeledMatchers.hasText("The Notebook; romance; 3,00"));
    assertTrue(ratePane.isVisible());
    assertEquals(": The Notebook", movieLabel.getText());
    assertEquals("The Notebook; romance; 3,00", ratedMovie.getText());
    robot.clickOn(rateBox).clickOn("4");
    robot.clickOn(rateButton);
    this.closeAlert();
    assertFalse(ratePane.isVisible());
    //her må det sjekkes at en rating kommer i listview
    
    robot.clickOn(movieName).write("Avengers");
    robot.clickOn(genreBox).clickOn("action");
    robot.clickOn(searchMovie);
    sleep(400);
    this.closeAlert();
    robot.clickOn(addRatingButton);
    this.closeAlert();
    assertTrue(ratePane.isVisible());
    robot.clickOn(rateButton);
    this.closeAlert();
    robot.clickOn(rateBox).clickOn("1");
    robot.clickOn(rateButton);
    this.closeAlert();
    assertFalse(ratePane.isVisible());
    //test her må det sjekkes at en rating kommer i listview

    robot.clickOn(movieName).write("History");
    robot.clickOn(genreBox).clickOn("drama");
    robot.clickOn(searchMovie);
    this.closeAlert();
    robot.clickOn(addRatingButton);
    this.closeAlert();
    assertTrue(ratePane.isVisible());
    robot.clickOn(cancelRatingButton);
    assertFalse(ratePane.isVisible());
    assertEquals("", movieLabel.getText());
    assertEquals(null, genreBox.getValue());
    //også her skal listview bli empty, må sjekke

    robot.clickOn(genreBox).clickOn("action");
    robot.clickOn(searchMovie);
    clickOn(LabeledMatchers.hasText("Avengers; action; 1,00"));
    this.closeAlert();
  }
  //*/

  @Test
  @DisplayName("Test to delete a rating")
  public void testDeleteRating() {
    logInStandard();
  }
  
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
  
}
