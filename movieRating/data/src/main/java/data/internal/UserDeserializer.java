package data.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import core.Movie;
import core.User;

public class UserDeserializer extends StdDeserializer<List<User>> {

  public UserDeserializer() { 
    this(null); 
  } 

  public UserDeserializer(Class<?> vc) { 
      super(vc); 
  }

  @Override
  public List<User> deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = p.getCodec().readTree(p);
    return deserialize((JsonNode) treeNode);
  }

  private List<User> deserialize(JsonNode jsonNodes) { 
    List<User> users = new ArrayList<User>();
    for (JsonNode jsonNode : jsonNodes) {
      User user = deserializeOneUser(jsonNode);
      users.add(user);      
    }
    return users;
  }

  private User deserializeOneUser(JsonNode jsonNode) {
    MovieDeserializerForUser movieDeserializer = new MovieDeserializerForUser();

    if (jsonNode instanceof ObjectNode objectNode) {
      JsonNode usernameNode = objectNode.get("username");
      JsonNode passwordNode = objectNode.get("password");
      JsonNode ratedMoviesNode = objectNode.get("ratedMovies");
      
      if (usernameNode instanceof TextNode && passwordNode instanceof TextNode) {
        User user = new User(String.valueOf(usernameNode), String.valueOf(passwordNode));
        List<Movie> ratedMovies = movieDeserializer.deserializeSeveralMovies(jsonNode);
        if(ratedMovies.size() == 0){
          return user;
        }
        for (Movie movie : ratedMovies) {
          user.rateMovie(movie, movie.getAllRatings().get(0));          
        }
        return user;
      }
    }
    return null;  
  }
}