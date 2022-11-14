package restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Class for running spring boot application.
 */

@SpringBootApplication
public class MovieRatingApplication {
  /**
   * Starts the application.
   * @param args string
   */
  public static void main(final String[] args) {
    SpringApplication.run(MovieRatingApplication.class, args);
  }
}
