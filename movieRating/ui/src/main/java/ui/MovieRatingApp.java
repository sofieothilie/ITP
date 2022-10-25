package ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MovieRatingApp extends Application {
	/**
	 *@param args
	 */
	public static void main(String[] args){
		Application.launch(args);
	}
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("MovieRatingApp"); // TODO: legge inn mellomrom
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MovieRating.fxml"))));
		primaryStage.show();
	}
}