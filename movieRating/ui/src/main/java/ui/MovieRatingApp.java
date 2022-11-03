package ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class that lauches the app.
 */
public class MovieRatingApp extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MovieRating.fxml"));
    MovieRatingController controller = new MovieRatingController();
    loader.setController(controller);
    Parent parent = loader.load();
    stage.setScene(new Scene(parent));
    stage.show();
  }
}
