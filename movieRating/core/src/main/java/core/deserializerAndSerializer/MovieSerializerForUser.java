package core.deserializerAndSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Movie;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class MovieSerializerForUser extends JsonSerializer<HashMap<Movie, Integer>> {

  /** FORMAT:
   ratedMovies: [
      {
          title: string,
          genre: string,
          rating: int
        },
        {
          title: string,
          genre: string,
          rating: int
        }
    ]
   */

  @Override
  public void serialize(HashMap<Movie, Integer> movies, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      gen.writeStartArray();
      for (Entry<Movie, Integer> ratedMovieEntry : movies.entrySet()) {
        gen.writeStartObject();
        gen.writeStringField("title", ratedMovieEntry.getKey().getTitle());
        gen.writeStringField("genre", ratedMovieEntry.getKey().getGenre());
        gen.writeNumberField("rating", ratedMovieEntry.getValue());
        gen.writeEndObject();        
      }
      gen.writeEndArray();
  }

}
