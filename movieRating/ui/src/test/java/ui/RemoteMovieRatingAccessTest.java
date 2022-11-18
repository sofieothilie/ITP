package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import core.Movie;
import core.User;

public class RemoteMovieRatingAccessTest {
  private WireMockConfiguration wireMockConfig;
  private WireMockServer wireMockServer;
  private ObjectMapper om;
  private static RemoteMovieRatingAccess remoteMovieRatingAccess;

  @BeforeEach
  @DisplayName("Set up for tests")
  public void setup() throws URISyntaxException {
    om = new ObjectMapper();
    wireMockConfig = WireMockConfiguration.wireMockConfig().port(8008);
    wireMockServer = new WireMockServer(wireMockConfig.portNumber());
    wireMockServer.start();
    WireMock.configureFor("localhost", wireMockConfig.portNumber());
    remoteMovieRatingAccess = new RemoteMovieRatingAccess(new URI("http://localhost:" + wireMockServer.port() + "/api/v1/"));
  }

  
  @Test
  @DisplayName("Testing getMovieRegister")
  public void testGetMovieRegister() throws Exception{
    List<Movie> movieList = new ArrayList<>();
    Movie movie1 = new Movie("hei", "romance");
    movieList.add(movie1);
    final String expectedString = om.writeValueAsString(movieList);

    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("movies/")))
        .withHeader("Accept", WireMock.equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(expectedString)));

    assertEquals(movieList, remoteMovieRatingAccess.getMovieRegister());

    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("movies/")))
        .withHeader("Accept", WireMock.equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(405)
            .withHeader("Content-Type", "application/json")));

    assertThrows(RuntimeException.class, () -> remoteMovieRatingAccess.getMovieRegister());
  }
  

  @Test
  @DisplayName("Testing getUserRegister")
  public void testGetUserRegister() throws Exception{
    List<User> userList = new ArrayList<>();
    User user1 = new User("testUserName", "testPassword");
    userList.add(user1);
    final String expectedString = om.writeValueAsString(userList);

    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("users/")))
        .withHeader("Accept", WireMock.equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(expectedString)));

    assertEquals(userList, remoteMovieRatingAccess.getAllUsers());

    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("users/")))
        .withHeader("Accept", WireMock.equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(405)
            .withHeader("Content-Type", "application/json")));

    assertThrows(RuntimeException.class, () -> remoteMovieRatingAccess.getAllUsers());
  }

  @Test
  @DisplayName("Testing getMovie")
  public void testGetMovie() throws Exception {
    Movie movie = new Movie("Cinderella", "fantasy");
  
    final String expectedString = om.writeValueAsString(movie);
    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("movies/Cinderella&fantasy")))
        .withHeader("Accept", WireMock.equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(expectedString)));

    assertEquals(movie, remoteMovieRatingAccess.getMovie("Cinderella", "fantasy"));

    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("movies/Cinderella&fantasy")))
        .withHeader("Accept", WireMock.equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(405)
            .withHeader("Content-Type", "application/json")));

    assertThrows(RuntimeException.class, () -> remoteMovieRatingAccess.getMovie("movie?title=Cinderella", "genre=fantasy"));
  }

  @Test
  @DisplayName("Testing getUser")
  public void testGetUser() throws Exception {
    User user1 = new User("userTest1", "password");
    final String expectedString = om.writeValueAsString(user1);

    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("users/userTest1")))
        .withHeader("Accept", WireMock.equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(expectedString)));

    assertEquals(user1, remoteMovieRatingAccess.getUser("userTest1"));

    WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(getUrl("users/userTest1")))
        .withHeader("Accept", WireMock.equalTo("application/json"))
        .willReturn(WireMock.aResponse()
            .withStatus(405)
            .withHeader("Content-Type", "application/json")));

    assertThrows(RuntimeException.class, () -> remoteMovieRatingAccess.getUser("userTest1"));
  }



  @AfterEach
  @DisplayName("Stops wiremock server after each test")
  public void stopWireMockServer() {
   wireMockServer.stop();
  }

  private String getUrl(String... segments) {
  String url = "/api/v1";
  for (String segment : segments) {
    url = url + "/" + segment;
  }
  return url;
}

  public RemoteMovieRatingAccess getRemoteMovieRatingAccess() {
      return remoteMovieRatingAccess;
  }
}
