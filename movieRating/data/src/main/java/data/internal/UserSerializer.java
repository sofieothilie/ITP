package data.internal;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import core.User;

public class UserSerializer extends StdSerializer<List<User>> {

  public UserSerializer() {
    this(null);
  }

  public UserSerializer(Class<List<User>> t) {
      super(t);
  }

  /** FORMAT:
   [{
      "username" : "username",
      "password" : "password",
      "ratedMovies" : [
        {
          title: string,
          genre: string,
          rating: int
        },{
          title: string,
          genre: string,
          rating: int
        }
        ...
      ]
    }]
   */
   
    public void serializeOneUser(User user, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
      MovieSerializerForUser movieSerializer = new MovieSerializerForUser();
      gen.writeStartObject();
      gen.writeStringField("username", user.getUsername());
      gen.writeStringField("password", user.getPassword());
      movieSerializer.serialize(user.getRatedMovies(), gen, serializers); 
      gen.writeEndObject();
    }

    @Override
    public void serialize(List<User> users, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeStartArray();
      for (User user : users) {
        serializeOneUser(user, gen, provider);        
      }
      gen.writeEndArray();      
    }
}
