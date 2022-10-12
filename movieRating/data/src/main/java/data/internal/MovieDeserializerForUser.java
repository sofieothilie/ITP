package data.internal;

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
import java.util.List;

public class MovieDeserializerForUser extends StdDeserializer<List<Movie>> {

  public MovieDeserializerForUser() { 
    this(null); 
  } 

  public MovieDeserializerForUser(Class<?> vc) { 
      super(vc); 
  }

  @Override
  public List<Movie> deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = p.getCodec().readTree(p);
    return deserializeSeveralMovies((JsonNode) treeNode);
  }

  private List<Movie> deserializeSeveralMovies(JsonNode jsonNodes){
    List<Movie> movies = new ArrayList<Movie>();
    for (JsonNode jsonNode : jsonNodes) {
      Movie movie = deserializeOneMovie(jsonNode);
      movies.add(movie);      
    }
    return movies;

    
  }

  private Movie deserializeOneMovie(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode objectNode) {
      JsonNode titleNode = objectNode.get("title");
      JsonNode genreNode = objectNode.get("genre");
      JsonNode ratingNode = objectNode.get("rating");
      if (titleNode instanceof TextNode && genreNode instanceof TextNode && ratingNode instanceof NumericNode) {
        Movie movie = new Movie(String.valueOf(titleNode), String.valueOf(genreNode));
        movie.addRating(Integer.valueOf(String.valueOf(ratingNode)));
        return movie;
      }
    }
    return null;
  }



}