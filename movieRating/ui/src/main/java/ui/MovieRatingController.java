package ui;

import core.Movie;
import core.User;
import data.MovieRegister;
import data.UserRegister;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * MovieRating controller class.
 */
public class MovieRatingController {
  //Fields
  private Movie movie;
  private final String movieFilename = "movieRegistry";
  private MovieRegister movieRegister = new MovieRegister(movieFilename);
  private User user;
  private final String userFilename = "userRegistry";
  private UserRegister userRegister = new UserRegister(userFilename, movieFilename);
  private static List<String> genresList = Arrays.asList("action",
      "comedy", "drama", "fantasy", "horror", "mystery", "romance", "thriller"); 
  private static List<Integer> ratingList = Arrays.asList(1, 2, 3, 4, 5);   

  //FXML fields
  @FXML
  private TextField username;
  private TextField password;
  private TextField movieName;
  @FXML
  private Button logIn;
  private Button createUser;
  private Button addMovieRegister;
  private Button logOut;
  private Button rateButton;
  @FXML
  private ChoiceBox<String> genreBox;
  @FXML
  private ChoiceBox<Integer> rateBox;
  @FXML
  private TextArea ratedMovie;
  @FXML
  private Label loggedIn;
  private Label loggedOut;
  private Label usernameLabel;
  private Label passwordLabel;
  private Label rateLabel;
  private Label movieLabel;
  private Label ratingscaleLabel;


  /**
   * Method that initializes the app with correct visibility.
   */
  @FXML
  public void initialize() {
    setLoginPossibility(true);
    setSearchVisibility(true);
    setRateVisibility(false, null);
    loggedOut.visibleProperty().set(false);
    loggedIn.visibleProperty().set(false); 
    addMovieRegister.setVisible(false);
    setGenres();
    setRating();      
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
  }

  /**
   * Sets the desired visibility based on rather a user is logged in or not.
   *
   * @param value true if logged in, else false
   */
  private void loggedIn(boolean value) {
    setLoginPossibility(false);
    addMovieRegister.setVisible(true);
    loggedIn.visibleProperty().set(true);
    //loggedOut.visibleProperty().set(false);
  }

  /**
   * Sets the search-area to desired visibility.
   *
   * @param value true if search area is supposed to be visible
   */
  private void setSearchVisibility(boolean value) {
    //searchMovie.visibleProperty().set(true);
    genreBox.visibleProperty().set(true);
    //movieRegisterList.visibleProperty().set(true);
  }

  /**
   * Sets the rate-area to desired visibility.
   *
   * @param value true if rate- area is supposed to be shown
   * @param movie the movie object to rate
   */
  private void setRateVisibility(boolean value, Movie movie) {
    rateBox.visibleProperty().set(value);
    rateButton.visibleProperty().set(value);
    movieLabel.visibleProperty().set(value);
    ratedMovie.visibleProperty().set(value);
    rateLabel.visibleProperty().set(value);
    ratingscaleLabel.setVisible(value);
    if (movie != (null) && value == true) {
      ratedMovie.setText(movie.toString());
    }
  }

  /**
   * Clears all search fields when called upon.
   */
  private void clearAllSearchFields() {
    //movieRegisterList.getItems().clear();
    movieName.clear();
    movieLabel.setText("");
    ratedMovie.setText(movie.toString());
    genreBox.setValue(null);
    rateBox.setValue(null);   
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
    } catch (IllegalArgumentException e) {
      errorActivation(e.getMessage());
    }          
  }
  
  /**
   * Creates a new user and sets desired fields and visibility.
   */
  @FXML
  private void handleCreateUser() {
    try {
      //this.userRegister.ableToCreateNewUser(new User(username.getText(), password.getText()));
      this.user = new User(username.getText(), password.getText());
      this.userRegister.registerNewUser(this.user);
      loggedIn(true);
      loggedOut.visibleProperty().set(false);
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
    loggedIn.setVisible(false);
    addMovieRegister.setVisible(false);
    loggedOut.visibleProperty().set(true);
    clearAllSearchFields();
  }

  //Movie methods

  // /**
  //  * Searches for movies by title and displays them in list view.
  //  */
  // @FXML
  // private void handleSearchMovie() {
  //   //kan legge til at delvise treff vises
  //   List<Movie> movieList = movieRegister.searchMovieTitle(movieName.getText());
  //   if (movieList.isEmpty()) { 
  //     errorActivation("No movies with title " + movieName.getText());
  //   } 
  //   for (Movie movie : movieList) {
  //     movieRegisterList.getItems().add(movie.toString());
  //   }
  // }

  // /**
  //  * Searches for movies by genre and displays them in list view.
  //  */
  // @FXML
  // private void handleSearchGenre() {
  //   List<Movie> movieList = movieRegister.searchGenre(genreBox.getValue());
  //   if (movieList.isEmpty()) {
  //     errorActivation("No movies with genre " + genreBox.getValue()); 
  //   }
  //   for (Movie movie : movieList) {
  //     movieRegisterList.getItems().add(movie.toString());
  //   }
  // }

  // /**
  //  * Displays a movie when it is selected if a user is logged in.
  //  * This allows for rating and sets values for rating:
  //  *
  //  * @param event the event that is supposed to be displayed
  //  */
  // @FXML
  // private void selectMovie(MouseEvent event) {
  //   //når handleRateButton trykkes må denne oppdateres
  //   ratedMovie.setText("");
  //   if (movieRegisterList.getSelectionModel().getSelectedItem() != null && this.user != null) {
  //     this.movie = convertSelectedItemToMovieObject();
  //     movieLabel.setText(": " + this.movie.getTitle());
  //     setRateVisibility(true, this.movie);
  //   }
  // }

  // /**
  //  * Retrives movie object from convertObservableList.
  //  *
  //  * @return the movie object to retrieve
  //  */
  // private Movie convertSelectedItemToMovieObject() {
  //   //når handleRateButton trykkes må denne oppdateres, lage en update metode 
  //   movieRegisterList.getSelectionModel().getSelectedItem();
  //   String[] movieStr = ((String) movieRegisterList
  //    .getSelectionModel().getSelectedItem()).split("\t");
  //   return this.movieRegister.getMovie(movieStr[0], movieStr[1]);
  // }

  /**
   * Adds a new movie to the register and writes it to file.
   * given that the input is valid and a user is logged in
   */
  @FXML
  private void handleAddMovieToRegister() {
    //trenger ikke nødvendigvis try-catch siden man ikke kan legge til film uten å være logget inn
    //valdiering om filmen finnes fra før, kalles fra movieRegister
    //validere om brukeren har ratet filem fra før, enten legge til eller oppdatere
    try {
      //Fails if not valid input to generate movie object:
      //new Movie(movieName.getText(), genreBox.getValue());
      movieRegister.addMovie(new Movie(movieName.getText(), genreBox.getValue()));
      this.movie = new Movie(movieName.getText(), genreBox.getValue());
      movieLabel.setText(": " + this.movie.getTitle());
      setRateVisibility(true, this.movie);

    } catch (Exception e) {
      errorActivation(e.getMessage());
    }
  }

  /**
   * Saves new rating and writes this to file.
   */
  @FXML
  private void handleRateButton() {
    //legge til oppdatering
    this.movie.addRating(rateBox.getValue());
    this.user.rateMovie(movie, rateBox.getValue());
    //this.movieRegister.updateMovie(movie);
    this.userRegister.updateRatedMovie(user, movie);
    //if (this.userRegister.getUser(this.user.getUsername()) != null){
    //this.userRegister.updateRatedMovie(user, movie);
    //}
    //else {
    //  this.userRegister.registerNewUser(this.user);
    //}
    confirmationActivation("You rated " + this.movie.getTitle() + ": " + rateBox.getValue());
    clearAllSearchFields();
  }

  //Error message
  private void errorActivation(String message) {
    //When called, displays a warning message
    //fikse på meldingene
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Movie Rating");
    alert.setContentText(message);
    alert.showAndWait();
  } 

  /**
   * When called, displays a warning message.
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

