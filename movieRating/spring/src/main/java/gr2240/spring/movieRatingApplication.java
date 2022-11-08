package gr2240.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class movieRatingApplication {
    @RequestMapping("/")
    String home() {
        return "Hello Worldsssss!";
    }

    // hente filmer
    // 
    // Movie getMovies() {
    //     // bruk lokal lagring til å hente filmer, og returner de som json til javafx klienten
    // }


    public static void main(String[] args) {
        SpringApplication.run(movieRatingApplication.class, args);
    }
}