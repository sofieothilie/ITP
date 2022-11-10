package gr2240.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Class for running spring boot application.
 */

@SpringBootApplication
public class MovieRatingApplication {
  /**
   * Runs springboot.
   * @param args
   */
  public static void main(final String[] args) {
    SpringApplication.run(MovieRatingApplication.class, args);
  }
}
