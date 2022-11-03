package core.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import core.Movie;
import core.User;

public class MovieSerializerForUserTest {

private Movie movie1;
private Movie movie2;
private Movie movie3;
private HashMap<Movie, Integer> ratedMovies = new HashMap<Movie, Integer>();

  @BeforeEach
  public void setUp() {
    this.movie1 = new Movie("Snow White", "fantasy");
    this.movie2 = new Movie("Titanic", "drama");
    this.movie3 = new Movie("Batman", "action");
    this.ratedMovies.put(movie1, 3);
    this.ratedMovies.put(movie2, 2);
    this.ratedMovies.put(movie3, 5);
  }


@DisplayName("Tests if a user is serialized as expected.")
@Test
public void serializerTest() throws JsonProcessingException {
  String actual = new ObjectMapper().writeValueAsString(new User("tester", "ztr0ngPassw0rd", this.ratedMovies));
  String actualCopy = new ObjectMapper().writeValueAsString(new User("tester", "ztr0ngPassw0rd", this.ratedMovies));
  String expected =  "{\"username\":\"tester\",\"password\":\"ztr0ngPassw0rd\",\"ratedMovies\":[{\"title\":\"Snow White\",\"genre\":\"fantasy\",\"rating\":3},{\"title\":\"Titanic\",\"genre\":\"drama\",\"rating\":2},{\"title\":\"Batman\",\"genre\":\"action\",\"rating\":5}]}";
  assertEquals(expected, actual, "User was not serialized correctly.");
  assertEquals(actual, actualCopy, "Serializer should serialize same objects equal.");
}

}
