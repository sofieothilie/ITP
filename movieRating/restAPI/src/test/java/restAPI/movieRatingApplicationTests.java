package restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.Movie;
import core.User;
import restapi.MovieRatingApplication;
import restapi.MovieRatingSpringController;
import restapi.MovieRegisterService;
import restapi.UserRegisterService;


@AutoConfigureMockMvc
@ContextConfiguration(classes = { MovieRatingSpringController.class, UserRegisterService.class, MovieRegisterService.class, MovieRatingApplication.class })
@WebMvcTest
public class movieRatingApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  private MovieRegisterService movRegSer;

  private UserRegisterService userRegSer;

  @BeforeAll
  public void setup(){
  User user1 = new User("ellica", "ellica123");
  User user1copy = new User(user1.getUsername(), user1.getPassword());
  User user2 = new User("spiderman", "spiderman123");
  User user2copy = new User(user2.getUsername(), user2.getUsername());
  Movie m1 = new Movie("Cinderella", "fantasy");
  Movie m2 = new Movie("Snowhite", "fantasy");
  Movie m3 = new Movie("The Notebook", "romance");
  Movie m4 = new Movie("Cinderella", "romance");
  this.movRegSer.addAMovie(m1);
  this.movRegSer.addAMovie(m2);
  this.movRegSer.addAMovie(m3);
  this.movRegSer.addAMovie(m4);
  this.userRegSer.registerNewUser(user1);
  this.userRegSer.registerNewUser(user2);
  }

  private void testAddUser(User user, String expectedResult) throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/movieRating/newUser").conten);

    String resultCZ = result.getResponse().getContentAsString();
    assertNotNull(resultCZ);
    assertEquals(czs, resultCZ);
    }

  // @Test
  // public void testMovieRatingController(){}

  // @Test
  // public void testGetMovieRegister(){}

  // @Test
  // public void testGetUserRegister(){}

  // @Test
  // public void testGetMovie(){}

  // @Test
  // public void testSearchGenre(){}

  // @Test
  // public void testSearchTitle(){}

  // @Test
  // public void testAddMovie(){}

  // @Test
  // public void testAddRatingToMovie(){}

  // @Test
  // public void testGetUser(){}

  // @Test
  // public void testGetExistingUser(){}

  // @Test
  // public void testRegisterNewUser(){}

  // @Test
  // public void testRateMovie(){}
}
