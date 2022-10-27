package core.deserializerAndSerializer;

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

public class MovieDeserializerForUser extends StdDeserializer<HashMap<Movie, Integer>> {

  public MovieDeserializerForUser() {
    // Constructor
    this(null); 
  } 

  public MovieDeserializerForUser(Class<?> vc) {
    // Constructor
      super(vc); 
  }

  @Override
  public HashMap<Movie, Integer> deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
      //deserializer all objects in file
    TreeNode treeNode = p.getCodec().readTree(p);
    return deserializeSeveralMovies((JsonNode) treeNode);
  }

  private HashMap<Movie, Integer> deserializeSeveralMovies(JsonNode jsonNodes){ 
    //Deserialize several objects, calls for dseserializeonemovie
    HashMap<Movie, Integer> ratedMovies = new HashMap<Movie, Integer>();
    for (JsonNode jsonNode : jsonNodes) {
      Map.Entry<Movie, Integer> movieRated = deserializeOneMovie(jsonNode);
      if (movieRated != null){
        ratedMovies.put(movieRated.getKey(), movieRated.getValue());  
      }
    }
    return new HashMap<>(ratedMovies);
  }

  private Map.Entry<Movie,Integer> deserializeOneMovie(JsonNode jsonNode) {
    //Deserializes one movie from file
    if (jsonNode instanceof ObjectNode) {
      JsonNode titleNode = jsonNode.get("title");
      JsonNode genreNode = jsonNode.get("genre");
      JsonNode ratingNode = jsonNode.get("rating");
      if (titleNode instanceof TextNode && genreNode instanceof TextNode && ratingNode instanceof NumericNode) {
        Movie movie = new Movie(titleNode.asText(), genreNode.asText());
        
        return Map.entry(movie, ratingNode.asInt());
      }
    }
    return null;
  }
}