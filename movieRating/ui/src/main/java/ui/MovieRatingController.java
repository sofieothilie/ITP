package ui;

import core.Movie;
import core.User;
import data.MovieRegister;
import data.UserHandler;
import data.UserRegister;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * MovieRating controller class.
 */
public class MovieRatingController {
  //Fields
  private Movie movie;
  private final String movieFilename;
  public MovieRegister movieRegister;
  private User user;
  private final String userFilename;
  public UserRegister userRegister;
  private static List<String> genresList = Arrays.asList("action", "comedy",
      "drama", "fantasy", "horror", "mystery", "romance", "thriller"); 
  private static List<Integer> ratingList = Arrays.asList(1, 2, 3, 4, 5);  

  //FXML fields
  
  @FXML private Pane ratePane;
  @FXML private Pane searchPane;
  @FXML private Pane ratedMoviesPane;
  

  @FXML private PasswordField password;

  @FXML private TextField username;
  @FXML private TextField movieName;

  @FXML private Button logIn;
  @FXML private Button createUser;
  @FXML private Button logOut;
  @FXML private Button rateButton;
  @FXML private Button createUserDone;
  @FXML private Button backToLogIn;
  @FXML private Button searchMovie;
  @FXML private Button resetButton;
  @FXML private Button addRatingButton;
  @FXML private Button cancelRatingButton;
  @FXML private Button deleteRatingButton;
  @FXML private Button updateRatingButton;

  
  @FXML private ChoiceBox<String> genreBox;
  @FXML private ChoiceBox<Integer> rateBox;
  
  @FXML private TextArea ratedMovie;

  @FXML private ListView<Movie> moviesFound;
  @FXML private ListView<String> moviesRated;

  @FXML private Label loggedIn;
  @FXML private Label loggedOut;
  @FXML private Label usernameLabel;
  @FXML private Label passwordLabel;
  @FXML private Label rateLabel;
  @FXML private Label movieLabel;
  @FXML private Label ratingscaleLabel;
  @FXML private Label createNewUserText;
  @FXML private Label newUserLabel;
  @FXML private Label infoUserLabel;  

  /**
   * Constructor.
   */
  public MovieRatingController() {
    this.userFilename = "userRegistry";
    this.movieFilename = "movieRegistry";
    this.movieRegister = new MovieRegister(movieFilename);
    this.userRegister = new UserRegister(userFilename, movieFilename);
  }
  
  /**
   * Constructor for testing.
   */
  public MovieRatingController(String userFilename, String movieFilename) {
    this.userFilename = userFilename;
    this.movieFilename = movieFilename;
    this.movieRegister = new MovieRegister(movieFilename);
    this.userRegister = new UserRegister(userFilename, movieFilename);
  }

  // Methods

  /**
   * Method that initializes the app with correct visibility.
   */
  @FXML
  public void initialize() {
    loggedOut.visibleProperty().set(false);
    loggedIn.visibleProperty().set(false); 
    setLoginPossibility(true);
    setSearchVisibility(true);
    setRateVisibility(false, null);
    setUserRatedMovies(false);
    ratedMovie.setEditable(false);
    setGenres();
    setRating();  
    checkLogiIn(logIn); 
    addRatingButton.setVisible(false);
  }
    
  /**
   * Method that fills choice box with genres.
   */
  private void setGenres() {
    for (String str : genresList) {
      genreBox.getItems().add(str);
    }
  }

  /**
   * Method that fills choice box with rating options 1 to 5.
   */
  private void setRating() { 
    for (Integer integer : ratingList) {
      rateBox.getItems().add(integer);
    }
  }

  /**
   * Sets the log in- area to desired visibility.
   *
   * @param value true if log in possible and false if not
   */
  private void setLoginPossibility(boolean value) {
    usernameLabel.visibleProperty().set(value);
    passwordLabel.visibleProperty().set(value);
    logOut.setVisible(!value);
    username.visibleProperty().set(value);
    username.clear();
    password.visibleProperty().set(value);
    password.clear();
    logIn.visibleProperty().set(value);
    createUser.visibleProperty().set(value);
    loggedOut.visibleProperty().set(!value);
    createNewUserText.setVisible(!value);
    createUserDone.setVisible(false);
    newUserLabel.setVisible(value);
    backToLogIn.setVisible(!value);
  }


  //Sets the desired visibility based on rather a user is logged in or not:
  /**
   * Sets the desired visibility based on rather a user is logged in or not.
   *
   * @param value true if logged in, else false
   */
  public void loggedIn(boolean value) {
    setLoginPossibility(!value);
    loggedIn.visibleProperty().set(value);
    loggedOut.visibleProperty().set(!value);
    backToLogIn.setVisible(!value);
    createNewUserText.setVisible(!value);
    ratedMoviesPane.setVisible(value);
    infoUserLabel.setVisible(!value);
    //genreBox.setVisible(true);
  }

  /**
   * Sets the search-area to desired visibility.
   *
   * @param value true if search area is supposed to be visible
   */
  private void setSearchVisibility(boolean value) {
    searchPane.visibleProperty().set(value);

    // searchMovie.visibleProperty().set(true);
    // genreBox.visibleProperty().set(true);
    //movieRegisterList.visibleProperty().set(true);
  }

  /**
   * Sets the rate-area to desired visibility.
   *
   * @param value true if rate- area is supposed to be shown
   * @param movie the movie object to rate
   */
  private void setRateVisibility(boolean value, Movie movie) {
    ratePane.visibleProperty().set(value);
    // rateBox.visibleProperty().set(value);
    // rateButton.visibleProperty().set(value);
    // movieLabel.visibleProperty().set(value);
    // ratedMovie.visibleProperty().set(value);
    // rateLabel.visibleProperty().set(value);
    // ratingscaleLabel.setVisible(value);
    if (movie != (null) && value == true) {
      ratedMovie.setText(movie.toString());
    }
  }

  /*
   * Sets the usertated-area to desired visibility.
   */
  private void setUserRatedMovies(boolean value) {
    ratedMoviesPane.visibleProperty().setValue(value);
  }

  /**
   * Clears all search fields when called upon.
   */
  private void clearAllSearchFields() {
    //movieRegisterList.getItems().clear();
    movieName.clear();
    movieLabel.setText("");
    ratedMovie.setText(null);
    genreBox.setValue(null);
    rateBox.setValue(null);   
    moviesFound.getItems().clear();
    moviesRated.getItems().clear();
  }

  /**
   * Method that shows all movies the user has rated.
   */
  private void moviesRated() { 
    moviesRated.getItems().clear();
    for (Movie mov : user.getRatedMovies().keySet()) {
      moviesRated.getItems().add(mov.getTitle() + "; " + mov.getGenre() 
          + "; " + user.getRatedMovies().get(mov));
    }
  }

  /**
   * Method that checks if user has typed in username and password and then sets the log in button enable.
   *
   * @param event the event that triggers the method
   */
  private void checkLogiIn(Button button) {
    logIn.setDisable(true);
    ChangeListener<String> listener = new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable,
          String oldValue, String newValue) {
        if (newValue.equals("")) {
          button.setDisable(true);
        } else {
          button.setDisable(false);
        }
      }
    }; 
    username.textProperty().addListener(listener);
    password.textProperty().addListener(listener);

  }


  //User methods

  /**
   * Tries to log in a user. If user excists: sets correct fields and visibility status.
   */
  @FXML
  public void handleLogIn() {
    try {
      this.userRegister.existingUser(username.getText(), password.getText());  
      this.user = this.userRegister.getUser(username.getText());
      setLoginPossibility(false);
      loggedIn(true);  
      loggedOut.visibleProperty().set(false);
      moviesRated();
    } catch (IllegalArgumentException e) {
      errorActivation(e.getMessage());
    }          
  }
  
  /**
   * Gets user to new windom where user can create a new user.
   */
  @FXML
  public void handleCreateUser() { 
    checkLogiIn(createUserDone);
    logIn.setVisible(false);
    createNewUserText.setVisible(true);
    newUserLabel.setVisible(false);
    createUser.setVisible(false);
    setSearchVisibility(false);
    backToLogIn.setVisible(true);
    setRateVisibility(false, null);
    loggedOut.setVisible(false);
    createUserDone.setVisible(true);
    infoUserLabel.setVisible(false);
    

  }

  /**
   * Gets user back to log in windom.
   */
  @FXML
  private void handleBackToLogIn() {
    setLoginPossibility(true);
    setSearchVisibility(true);
  }


  /**
   * Creates a new user and sets desired fields and visibility.
   **/
  @FXML
  private void handleCreateUserDone() {
    try {
      this.user = new User(username.getText(), password.getText());
      this.userRegister.registerNewUser(this.user);
      loggedIn(true);
      loggedOut.visibleProperty().set(false);
      createNewUserText.setVisible(false);
      backToLogIn.setVisible(false);
      setSearchVisibility(true);
      setUserRatedMovies(true);
    } catch (Exception e) {
      errorActivation(e.getMessage());
    }
  }

  /**
   * Logs user out. Resets desired fields and sets desired visibility.
   */
  @FXML 
  private void handleLogOut() {
    this.user = null;
    //loggedIn(false); tror at man kun trenge denne og ikke den under
    setLoginPossibility(true); 
    setRateVisibility(false, null); 
    setUserRatedMovies(false);
    loggedIn.setVisible(false);
    loggedOut.visibleProperty().set(true);
    newUserLabel.setVisible(true);
    //sleep eller wait to remove "Your are logged out" message after 3 seconds
    clearAllSearchFields();
    infoUserLabel.setVisible(true);
    addRatingButton.setVisible(false);
  }

  //Movie methods

  /**
   * Searches for movies by title, genre or both and displays them in list view.
   */
  @FXML
  private void handleSearchMovie() {
    //kan legge til at delvise treff vises
    moviesFound.getItems().clear();
    //addRatingButton.setVisible(true);
    if(genreBox.getSelectionModel().isEmpty()){
      List<Movie> movieList = movieRegister.searchMovieTitle(movieName.getText());
      if (movieList.isEmpty()) { 
        errorActivation("No movies with title: " + movieName.getText()+ " found in the register. Click on 'Add rating' to add the movie to the register.");
        addRatingButton.setVisible(true);
      } 
      for (Movie movie : movieList) {
        moviesFound.getItems().add(movie);
      }
    }
    else if (movieName.getText().isEmpty()) {
      List<Movie> genreList = movieRegister.searchGenre((String) genreBox.getValue());
      if(genreList.isEmpty()) {
        errorActivation("No movies with genre: " + (String) genreBox.getValue()+ " found in the register. Click on 'Add rating' to add the movie to the register.");
        addRatingButton.setVisible(true);
      }
      for (Movie movie: genreList){
        moviesFound.getItems().add(movie);
      }
    }
    else{
      try{
        Movie foundMovie = movieRegister.getMovie(movieName.getText(), (String) genreBox.getValue());
        moviesFound.getItems().add(foundMovie);
      }catch (IllegalArgumentException e){
        errorActivation("No movies with title: " + movieName.getText() 
            + " and  genre: " + (String) genreBox.getValue() + " found in the register. Click on 'Add rating' to add the movie to the register.");
        addRatingButton.setVisible(true);
      }
    }
  }

  /**
   * Displays a movie when it is selected if a user is logged in.
   * This allows for rating and sets values for rating:
   *
   * @param event the listener for the click on an item
   */
  @FXML
  private void handleChooseMovie() {
    //når handleRateButton trykkes må denne oppdateres
    addRatingButton.visibleProperty().set(false);
    ratedMovie.setText("");
    if(this.user == null){
      errorActivation("You must log in or create user to rate a movie.");
    }
    if (moviesFound.getSelectionModel().getSelectedItem() != null && this.user != null) {
      this.movie = convertSelectedItemToMovieObject();
      setRateVisibility(true, this.movie);
      movieLabel.setText(": " + this.movie.getTitle());
    }
  }

  /**
   * Retrives movie object from convertObservableList.
   *
   * @return the movie object to retrieve
   */
  private Movie convertSelectedItemToMovieObject() {
    //når handleRateButton trykkes må denne oppdateres, lage en update metode 
    moviesFound.getSelectionModel().getSelectedItem();
    String[] movieStr = moviesFound.getSelectionModel().getSelectedItem().toString().split(" ");
        return this.movieRegister.getMovie(movieStr[0].substring(0, movieStr[0].length() -1)
        , movieStr[1].substring(0, movieStr[1].length() -1));
  }



  /*
   * Method for reset button, then search field and genre box is cleared.
   */
  @FXML
  private void handleResetButton(){
    movieName.clear();
    genreBox.setValue(null);
    moviesFound.getItems().clear();
  }


  /**
   * Adds a new movie to the register and writes it to file.
   * given that the input is valid and a user is logged in
   */

  @FXML
  private void handleAddRating(){
    try{
      if(movieName.getText().isEmpty() && genreBox.getSelectionModel().isEmpty()){
        errorActivation("You must type in a movie title and a genre");
      }
      else if(movieName.getText().isEmpty()){
        errorActivation("You must type in a movie title");
      }
      else if(genreBox.getSelectionModel().isEmpty()){
        errorActivation("You must choose a genre");
      }
      else{
      movieRegister.addMovie(new Movie(movieName.getText(), genreBox.getValue()));
      this.movie = new Movie(movieName.getText(), genreBox.getValue());
      movieLabel.setText(": " + this.movie.getTitle());
      confirmationActivation(this.movie.getTitle() + " was added to the register.");
      setRateVisibility(true, this.movie);
      }
    }
    catch (Exception e){
      errorActivation(e.getMessage());
    }
    
  }

  /**
   * Saves new rating and writes this to file.
   */
  @FXML
  private void handleRateButton() {
    //legge til oppdatering
    try{
      if(rateBox.getSelectionModel().isEmpty()){
        errorActivation("You must choose a rating");
      }
    this.user.rateMovie(movie, rateBox.getValue());
    this.userRegister.updateRatedMovie(user, movie);
    confirmationActivation("You rated " + this.movie.getTitle() + ": " + rateBox.getValue());
    moviesRated();
    rateBox.setValue(null);
    ratedMovie.setText(this.movie.toString());
    cancelRatingButton.visibleProperty().set(false);
    
    }
    catch (Exception e){
      errorActivation(e.getMessage());
    }
  }

  /**
   * Cancels rating and resets values.
   */
  @FXML
  private void handleCancelRating(){
    ratePane.setVisible(false);
    rateBox.setValue(null);
    ratedMovie.clear();
  }


  @FXML
  private void handleDeleteRating(){

  }


  @FXML
  private void handleUpdateRating(){

  }

  @FXML
  private void handleEditMovie(){

  }


  /**
   * When called, displays an error message.
   *
   * @param message the warning message that shows
   */
  void errorActivation(String message) {
    //When called, displays a warning message
    //fikse på meldingene
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Movie Rating");
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * When called, displays a confirmation message.
   *
   * @param message the warning message that shows
   */
  private void confirmationActivation(String message) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Movie Rating");
    alert.setContentText(message);
    alert.showAndWait();
  }
}

