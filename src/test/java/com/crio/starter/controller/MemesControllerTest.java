package com.crio.starter.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.MemeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest

public class MemesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemeService memeService;

 
    @Test
    void postMeme() throws Exception{
    MemeEntity memeEntity = new MemeEntity();
    
    memeEntity.setName("Blank");
    memeEntity.setUrl("www.test.com");
    memeEntity.setCaption("Tal");
    
    ResponseDto responseDto = new ResponseDto(memeEntity.getId());
   

    when(memeService.postMeme(any(MemeEntity.class))).thenReturn(responseDto);

   
    mockMvc.perform(post("/memes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(memeEntity)))
            .andExpect(status().isOk())
            .andExpect(content().json(new ObjectMapper().writeValueAsString(responseDto)));
    }
    @Test
    void getMeme() throws Exception{

        Mockito.doReturn(new Meme("1", "Blank", "Yo", "URL"))
            .when(memeService).getMeme("1");


        URI uri = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8081/memes/1")
            .build().toUri();

        MockHttpServletResponse response = mockMvc.perform(
            get(uri.toString()).accept(APPLICATION_JSON_VALUE)
        ).andReturn().getResponse();

       
        String responseStr = response.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Meme meme = mapper.readValue(responseStr, Meme.class);
        Meme res = new Meme("1", "Blank", "Yo", "URL");

        assertEquals(meme, res);
        Mockito.verify(memeService, Mockito.times(1)).getMeme("1");
    }
}
