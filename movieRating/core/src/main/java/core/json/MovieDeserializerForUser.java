package core.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import core.Movie;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Realizes a movie object from user JSON file.
 */
public class MovieDeserializerForUser
    extends StdDeserializer<HashMap<Movie, Integer>> {
  /**
 * Realizes a movie object from user JSON file.
 */
  public MovieDeserializerForUser() { //TODO: spørre studass
    this(null);
  }

  public MovieDeserializerForUser(Class<?> vc) { //TODO: spørre studass
    super(vc);
  }

  @Override
  public HashMap<Movie, Integer> deserialize(
      JsonParser p,
      DeserializationContext ctxt
  )
      throws IOException, JsonProcessingException {
    TreeNode treeNode = p.getCodec().readTree(p);
    return deserializeSeveralMovies((JsonNode) treeNode);
  }

  private HashMap<Movie, Integer> deserializeSeveralMovies(JsonNode jsonNodes) {
    //Hjelpemetode, tar alle filmer og lager objekter ut i fra de
    HashMap<Movie, Integer> ratedMovies = new HashMap<Movie, Integer>();
    for (JsonNode jsonNode : jsonNodes) {
      Map.Entry<Movie, Integer> movieRated = deserializeOneMovie(jsonNode);
      if (movieRated != null) {
        ratedMovies.put(movieRated.getKey(), movieRated.getValue());
      }
    }
    return new HashMap<>(ratedMovies);
  }

  private Map.Entry<Movie, Integer> deserializeOneMovie(JsonNode jsonNode) {
    //Hjelpemetode, tar en film og lager et objekt ut ifra det
    if (jsonNode instanceof ObjectNode) {
      //ObjectNode objectNode = new ObjectNode(null);
      JsonNode titleNode = jsonNode.get("title");
      JsonNode genreNode = jsonNode.get("genre");
      JsonNode ratingNode = jsonNode.get("rating");
      if (
          titleNode instanceof TextNode 
          && genreNode instanceof TextNode 
          && ratingNode instanceof NumericNode
      ) {
        Movie movie = new Movie(titleNode.asText(), genreNode.asText());
        return Map.entry(movie, ratingNode.asInt());
      }
    }
    return null;
  }
}
