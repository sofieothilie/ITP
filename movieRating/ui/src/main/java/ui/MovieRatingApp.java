package ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
  public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("MovieRatingApp"); // TODO: legge inn mellomrom
    primaryStage.setScene(
      new Scene(FXMLLoader.load(getClass().getResource("MovieRating.fxml")))
    );
    primaryStage.show();
  }
}
