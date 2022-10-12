package data.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Movie;

import java.io.IOException;
import java.util.HashMap;

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
      gen.writeStartObject();
      gen.writeFieldName("ratedMovies");
      gen.writeStartArray();
      for (Movie movie : movies.keySet()) {
        gen.writeStartObject();
        gen.writeStringField("title", movie.getTitle());
        gen.writeStringField("genre", movie.getGenre());
        gen.writeNumberField("rating", movies.get(movie));
        gen.writeEndObject();
      }
      gen.writeEndArray();
      gen.writeEndObject();
  }

}
