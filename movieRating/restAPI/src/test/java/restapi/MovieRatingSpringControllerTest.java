package restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Movie;
import core.User;


@AutoConfigureMockMvc
@ContextConfiguration(classes = { MovieRatingSpringController.class, UserRegisterService.class, MovieRegisterService.class, MovieRatingApplication.class })
@WebMvcTest
public class MovieRatingSpringControllerTest{

  @Autowired
  private MockMvc mockMvc;

  private User user1;
  private User user1copy;
  private User user2;
  private User user2copy;
  private Movie m1;
  private Movie m2;
  private Movie m3;
  private Movie m4;
  private List<User> userList = new ArrayList<User>();
  private List<User> userCopyList = new ArrayList<User>();

  @BeforeEach
  public void setup(){
    this.user1 = new User("ellica", "ellica123");
    this.user1copy = new User(user1.getUsername(), user1.getPassword());
    this.user2 = new User("spiderman", "spiderman123");
    this.user2copy = new User(user2.getUsername(), user2.getUsername());
    this.m1 = new Movie("Cinderella", "fantasy");
    this.m2 = new Movie("Snowhite", "fantasy");
    this.m3 = new Movie("The Notebook", "romance");
    this.m4 = new Movie("Cinderella", "romance");
    userList.add(user1);
    userList.add(user2);
    userCopyList.add(user1copy);
    userCopyList.add(user2copy);
  }

  @Test
  private void testUsers() throws Exception {
    for (User  user : this.userList) {
      mockMvc.perform(MockMvcRequestBuilders.put("newUser" + userID(user)));      
    }
    ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/movieRating/users"));
    assertNotNull(result);
    assertEquals(result, "users");
  }

  private String userID(User user) {
    return "?username=" + user.getUsername() + "password=" + user.getPassword();
  }
}
