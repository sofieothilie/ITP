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
import data.UserRegister;

import java.io.IOException;
import java.nio.file.Files;

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

import core.User;

public class MovieRatingControllerTest extends ApplicationTest {

  private FxRobot robot = new FxRobot();
  private User user1 = new User("per", "123", null);
  

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
    // MovieRegister movieRegister = new MovieRegister();
    // List <Movie> movieList = movieRegister.searchMovieTitle(movieName.getText());
    // UserHandler userHandler = new UserHandler(movieFilename);
    // UserRegister userRegister = new UserRegister(movieFilename, movieFilename);
    // User user1 = new User("per", "123");
    // userHandler.writeUserToRegister(user1);
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
 
  /* 
  @Test
  @DisplayName("Test to create new user") 
  public void testCreateNewUser() {
    robot.clickOn(createUser);
    robot.clickOn(username).write("NTNU");
    robot.clickOn(password).write("123");
    robot.clickOn(createUserDone);
    sleep(500);
    //da skal dette skje
    assertFalse(logIn.isVisible());
    assertTrue(loggedIn.isVisible());
    assertTrue(logOut.isVisible());
    assertTrue(ratedMoviesPane.isVisible());
  }
  */
  
  private void createUser() {
    robot.clickOn(createUser);
    robot.clickOn(this.username).write("Sina");
    robot.clickOn(this.password).write("123");
    robot.clickOn(createUserDone);
    sleep(500);
  }

  private void logIn(){
    robot.clickOn(this.username).write("Per");
    robot.clickOn(this.password).write("123");
    robot.clickOn(logIn);
  }

  /* 
  @Test
  @DisplayName("Test to create user with unvalid input")
  public void testFailCreateUser(){
    robot.clickOn(createUser);
    robot.clickOn(username).write("");
    robot.clickOn(password).write("1234567");
    robot.clickOn(createUserDone);
    sleep(500);
    this.closeAlert();
    username.clear();
    password.clear();

    //skal feile da denne brukeren egt skal eksistere

    // robot.clickOn(username).write("per");
    // robot.clickOn(password).write("123");
    // robot.clickOn(createUserDone);
    // sleep(500);
    // this.closeAlert();
  }
  */

 /* 
  @Test
  @DisplayName("Test log out")
  public void testLogOut() {
    createUser();
    sleep(700);
    robot.clickOn(logOut);
    assertTrue(loggedOut.isVisible());
    assertFalse(logOut.isVisible());
    assertTrue(logIn.isVisible());
    assertFalse(ratedMoviesPane.isVisible());
    assertFalse(ratePane.isVisible());
  }
  */

/* 
  @Test 
  @DisplayName("Test to log in after creating an user")
  public void logInFirstTime(){
    createUser();
    sleep(700);
    robot.clickOn(logOut);
    sleep(700);
    robot.clickOn(username).write("Sina");
    robot.clickOn(password).write("123");
    robot.clickOn(logIn);
    sleep(700);
    assertTrue(loggedIn.isVisible());
  }
  */

  /*
  @Test
  @DisplayName("Test to try to create user with existing username")
  public void testCreateUserAlreadyExists() {
    createUser();
    robot.clickOn(logOut);
    sleep(500);
    robot.clickOn(createUser);
    robot.clickOn(username).write("Sina");
    robot.clickOn(password).write("123");
    robot.clickOn(createUserDone);
    sleep(500);
    this.closeAlert();
  }
  */
  
  @Test
  @DisplayName("Test to add a movie to the register")
  public void addMovie(){
    createUser();
    sleep(500);
    robot.clickOn(movieName).write("Titanic");
    robot.clickOn(genreBox).clickOn("romance");
    robot.clickOn(searchMovie);
    sleep(500);
    this.closeAlert();
    robot.clickOn(addRatingButton);
    sleep(400);
    this.closeAlert();
    assertTrue(ratePane.isVisible());
    robot.clickOn(rateBox).clickOn("3");
    sleep(400);
    robot.clickOn(rateButton);
    this.closeAlert();
    assertFalse(ratePane.isVisible());

    robot.clickOn(movieName).write("Titanic");
    robot.clickOn(genreBox).clickOn("romance");
    robot.clickOn(searchMovie);
    sleep(400);
  }
  

  /* 
  @Test
  @DisplayName("Sucessful search for a movie")
  public void searchMovie() {
      robot.clickOn(movieName).write("The Notebook");
      robot.clickOn(searchMovie);
      assertEquals("The Notebook", ratedMovie.getText());
  }

  @Test
  @DisplayName("Unsucessful search for a movie")
  public void searchMovieNotFound() {
      clickOn(movieName).write("The Notebook");
      clickOn(searchMovie);
      //assertEquals("No movies with the title" + movieName.getText(), controller.errorActivation().getText());
  }
*/

  /* 
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
  */

  
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
