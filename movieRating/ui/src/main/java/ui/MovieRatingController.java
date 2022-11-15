package ui;

import core.Movie;
import core.User;
import data.MovieRegister;
import data.UserHandler;
import data.UserRegister;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

  
  @FXML private ChoiceBox<String> genreBox;
  @FXML private ChoiceBox<Integer> rateBox;
  
  @FXML private TextArea ratedMovie;

  @FXML private ListView<Object> moviesFound;
  @FXML private ListView<Object> moviesRated;

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
   * Constructor for ui test.
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
    setLoginPossibility(true);
    searchPane.isVisible();
    ratePane.setVisible(false);
    setGenres();
    setRating();  
    checkLogiIn(logIn); 
    addRatingButton.setVisible(false);
    loggedOut.visibleProperty().set(false);
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
    password.visibleProperty().set(value);
    logIn.visibleProperty().set(value);
    createUser.visibleProperty().set(value);
    createNewUserText.setVisible(!value);
    createUserDone.setVisible(!value);
    newUserLabel.setVisible(value);
    backToLogIn.setVisible(!value);
    loggedIn.setVisible(!value);
    ratedMoviesPane.visibleProperty().set(!value);
  }


  //Sets the desired visibility based on rather a user is logged in or not:
  /**
   * Sets the desired visibility based on rather a user is logged in or not.
   *
   * @param value true if logged in, else false
   */
  public void loggedIn(boolean value) {
    setLoginPossibility(!value);
    loggedOut.visibleProperty().set(!value);
    backToLogIn.setVisible(!value);
    createNewUserText.setVisible(!value);
    ratedMoviesPane.setVisible(value);
    infoUserLabel.setVisible(!value);
    createUserDone.setVisible(!value);
  }


  /**
   * Sets the rate-area to desired visibility.
   *
   * @param value true if rate- area is supposed to be shown
   * @param movie the movie object to rate
   */
  private void setRateVisibility(boolean value, Movie movie) {
    ratePane.visibleProperty().set(value);
    rateButton.visibleProperty().set(value);
    cancelRatingButton.visibleProperty().set(value);
    if (movie != (null) && value == true) {
      ratedMovie.setText(movie.toString());
    }
  }


  /**
   * Clears all search fields when called upon.
   */
  private void clearAllSearchFields() {
    username.clear();
    password.clear();
    movieName.clear();
    movieLabel.setText("");
    ratedMovie.setText(null);
    genreBox.setValue(null);
    rateBox.setValue(null);   
    moviesFound.getItems().clear();
    //moviesRated.getItems().clear();
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
   * Method that checks if user has typed in username and password, log in button gets enable.
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
      moviesRated();
    } catch (IllegalArgumentException e) {
      errorActivation(e.getMessage());
      username.clear();
      password.clear();
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
    searchPane.setVisible(false);;
    backToLogIn.setVisible(true);
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
    searchPane.setVisible(true);
  }


  /**
   * Creates a new user and sets desired fields and visibility.
   **/
  @FXML
  private void handleCreateUserDone() {
    try {
      this.user = new User(username.getText(), password.getText());
      this.userRegister.registerNewUser(this.user);
      //setLoginPossibility(false);
      loggedIn(true);
      createNewUserText.setVisible(false);
      backToLogIn.setVisible(false);
      searchPane.setVisible(true);
      //createUser.setVisible(false);
    } catch (Exception e) {
      errorActivation(e.getMessage());
      username.clear();
      password.clear();
    }
  }

  /**
   * Logs user out. Resets desired fields and sets desired visibility.
   */
  @FXML 
  private void handleLogOut() {
    this.user = null; //må vi ha denne
    setLoginPossibility(true);
    setRateVisibility(false, null);
    loggedOut.visibleProperty().set(true);
    //addRatingButton.setVisible(false);
    clearAllSearchFields();
    moviesRated.getItems().clear();
  }

  /**
   * Searches for movies by title, genre or both and displays them in list view.
   */
  @FXML
  private void handleSearchMovie() {
    //kan legge til at delvise treff vises
    moviesFound.getItems().clear();
    //addRatingButton.setVisible(true);
    if (genreBox.getSelectionModel().isEmpty()) {
      List<Movie> movieList = movieRegister.searchMovieTitle(movieName.getText());
      if (movieList.isEmpty()) { 
        errorActivation("No movies with title: " + movieName.getText()
            + " found in the register");
        genreBox.setValue(null);
      } 
      for (Movie movie : movieList) {
        moviesFound.getItems().add(movie);
      }
    } else if (movieName.getText().isEmpty()) {
      List<Movie> genreList = movieRegister.searchGenre((String) genreBox.getValue());
      if (genreList.isEmpty()) {
        errorActivation("No movies with genre: " + (String) genreBox.getValue()
            + " found in the register");
        movieName.clear();
      }
      for (Movie movie : genreList) {
        moviesFound.getItems().add(movie);
      }
    } else {
      try {
        Movie foundMovie = movieRegister.getMovie(movieName.getText(), 
            (String) genreBox.getValue());
        moviesFound.getItems().add(foundMovie);
      } catch (IllegalArgumentException e) {
        errorActivation("No movies with title: " + movieName.getText() 
            + " and  genre: " + (String) genreBox.getValue() 
            + " found in the register. Click on 'Add rating' to add the movie to the register.");
        if (this.user != null) {
          addRatingButton.setVisible(true);
        }
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
    addRatingButton.visibleProperty().set(false);
    ratedMovie.setText("");
    if (this.user == null) {
      errorActivation("You must log in or create user to rate a movie.");
    }
    if (moviesFound.getSelectionModel().getSelectedItem() != null && this.user != null) {
      this.movie = (Movie) convertSelectedItemToMovieObject(moviesFound);
      if (!this.user.hasRatedMovie(this.movie)) {
        setRateVisibility(true, this.movie);
        movieLabel.setText(": " + this.movie.getTitle());
      } else {
        errorActivation("You have already rated this movie."
            + "If you want to add a new rating, you must delete the old one.");
      }
    }
  }

  /**
   * Retrives movie object from convertObservableList.
   *
   * @return the movie object to retrieve
   */
  private Movie convertSelectedItemToMovieObject(ListView<Object> view) {
    //når handleRateButton trykkes må denne oppdateres, lage en update metode 
    String[] movieStr = view.getSelectionModel().getSelectedItem().toString().split(" ");
    String title = "";
    int length = movieStr.length;
    if (length > 2) {
      for (int i = 0; i < length - 3; i++) {
        title += movieStr[i] + " ";
      }
    }
    title += movieStr[length - 3].substring(0, movieStr[length - 3].length() - 1);
    String genre = movieStr[length - 2].substring(0, movieStr[length - 2].length() - 1);
    return movieRegister.getMovie(title, genre);
  }



  /*
   * Method for reset button, then search field and genre box is cleared.
   */
  @FXML
  private void handleResetButton() {
    movieName.clear();
    genreBox.setValue(null);
    moviesFound.getItems().clear();
  }


  /**
   * Adds a new movie to the register and writes it to file.
   * given that the input is valid and a user is logged in
   */

  @FXML
  private void handleAddRating() {
    try {
      movieRegister.addMovie(new Movie(movieName.getText(), genreBox.getValue()));
      this.movie = new Movie(movieName.getText(), genreBox.getValue());
      movieLabel.setText(": " + this.movie.getTitle());
      confirmationActivation(this.movie.getTitle() + " was added to the register.");
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
    try {
      this.user.rateMovie(movie, rateBox.getValue());
      this.userRegister.updateRatedMovie(user, movie);
      confirmationActivation("You rated " + this.movie.getTitle() + ": " + rateBox.getValue());
      moviesRated();
      //rateBox.setValue(null);
      ratedMovie.setText(this.movie.toString());
      cancelRatingButton.visibleProperty().set(false);
      clearAllSearchFields();

      ratePane.visibleProperty().set(false);
    } catch (Exception e) {
      errorActivation(e.getMessage());
    }
  }

  /**
   * Cancels rating and resets values.
   */
  @FXML
  private void handleCancelRating() {
    ratePane.setVisible(false);
    // rateBox.setValue(null);
    // ratedMovie.clear();
    // movieName.clear();
    // genreBox.setValue(null);
    clearAllSearchFields();

  }


  @FXML
  private void handleEditMovie() {
    deleteRatingButton.setVisible(true);
  }

  @FXML
  private void handleDeleteRating() {
    String deleteMovie = (String) moviesRated.getSelectionModel().getSelectedItem();
    String[] deleteMovieList = deleteMovie.split(" ");
    Integer rating = Integer.parseInt(deleteMovieList[deleteMovieList.length - 1]);
    Movie movie = convertSelectedItemToMovieObject(moviesRated);
    if (confirmation(movie)) {
      movie.deleteMovie(rating);
      this.user.deleteMovie(movie);
      userRegister.updateRatedMovie(user, movie);
      moviesRated.getItems().remove(deleteMovie);
    }
  }

  private boolean confirmation(Movie movie) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Delete " + movie.getTitle() + ", " + movie.getGenre());
    alert.setContentText("Are you sure you want to delete " 
        + movie.getTitle() + ", " + movie.getGenre() + "?");
    Optional<ButtonType> result = alert.showAndWait();
    if (!result.isPresent() || result.get() != ButtonType.OK) {
      return false;
    }
    return true; 
  }




  /**
   * When called, displays an error message.
   *
   * @param message the warning message that shows
   */
  private void errorActivation(String message) {
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