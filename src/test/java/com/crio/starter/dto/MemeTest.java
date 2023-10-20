package com.crio.starter.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class MemeTest {
    @Test
    public void serializeAndDeserializeRestaurantJson() throws IOException, JSONException {
      final String jsonString =
          "{\n"
              + "  \"id\": \"1\",\n"
              + "  \"name\": \"Blank\",\n"
              + "  \"caption\": \"Yo\",\n"
              + "  \"url\": \"www.test.com\"\n"
              + "}";
  
    
      Meme meme = new Meme();
      meme= new ObjectMapper().readValue(jsonString, Meme.class);
  
      
      String actualJsonString = "";
      actualJsonString = new ObjectMapper().writeValueAsString(meme);
      JSONAssert.assertEquals(jsonString, actualJsonString, true);
    }
}
