package gr2240.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mvoieRatingController {

    @RequestMapping("/hello")
    String home() {
        return "Hello Worldsssss!";
    }
    
}
