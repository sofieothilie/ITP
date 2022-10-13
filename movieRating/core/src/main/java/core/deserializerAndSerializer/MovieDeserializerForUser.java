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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDeserializerForUser extends StdDeserializer<HashMap<Movie, Integer>> {

  public MovieDeserializerForUser() { 
    this(null); 
  } 

  public MovieDeserializerForUser(Class<?> vc) { 
      super(vc); 
  }

  @Override
  public HashMap<Movie, Integer> deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = p.getCodec().readTree(p);
    return deserializeSeveralMovies((JsonNode) treeNode);
  }

  private HashMap<Movie, Integer> deserializeSeveralMovies(JsonNode jsonNodes){
    HashMap<Movie, Integer> ratedMovies = new HashMap<Movie, Integer>();
    for (JsonNode jsonNode : jsonNodes) {
      Map.Entry<Movie, Integer> movieRated = deserializeOneMovie(jsonNode);
      ratedMovies.put(movieRated.getKey(), movieRated.getValue());      
    }
    return ratedMovies; //KOPI
  }

  private Map.Entry<Movie,Integer> deserializeOneMovie(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode objectNode) {
      JsonNode titleNode = objectNode.get("title");
      JsonNode genreNode = objectNode.get("genre");
      JsonNode ratingNode = objectNode.get("rating");
      if (titleNode instanceof TextNode && genreNode instanceof TextNode && ratingNode instanceof NumericNode) {
        Movie movie = new Movie(titleNode.asText(), genreNode.asText());
        return Map.entry(movie, Integer.valueOf(String.valueOf(ratingNode)));
      }
    }
    return null;
  }



}