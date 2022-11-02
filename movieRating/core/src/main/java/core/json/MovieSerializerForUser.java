package core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.Movie;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

/** 
 * Converts a movie object to JSON format.
 * See FORMAT below.
*/
public class MovieSerializerForUser extends JsonSerializer<HashMap<Movie, Integer>> {
  /**
   * FORMAT: '' .
   *ratedMovies: [
   * {
   *      title: string,
   *      genre: string,
   *      rating: int
   *     },
   *     {
   *      title: string,
   *      genre: string,
   *      rating: int
   *     }
   *]
   */

  @Override
  public void serialize(HashMap<Movie, Integer> movies, 
      JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    // Få formatet på filmen på rett måte, skriver til fil
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

  //TODO: logge inn, så rate film, må spørre studass

}
