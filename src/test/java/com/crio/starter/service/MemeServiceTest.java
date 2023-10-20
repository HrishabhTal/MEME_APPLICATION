package com.crio.starter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repositoryService.MemeRepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)

public class MemeServiceTest {
    @Mock
    private MemeRepositoryService memeRepositoryService;
  
    @InjectMocks
    private MemeServiceImpl memeService;

    @Test
    void postMeme(){
        
        MemeEntity memeEntity = new MemeEntity();
    
        memeEntity.setName("Blank");
        memeEntity.setUrl("www.test.com");
        memeEntity.setCaption("Tal");
        
       Meme meme = new Meme(memeEntity.getId(), "Blank", "www.test.com", "Tal");
       
        Mockito.doReturn(meme)
        .when(memeRepositoryService).post(memeEntity);

        ResponseDto response = memeService.postMeme(memeEntity);

        ResponseDto expected = new ResponseDto(memeEntity.getId());

        assertEquals(expected, response);
    }

    @Test
    void getMeme(){
        Meme meme = new Meme("1","Blank","www.test.com","Test");
        
        Mockito.doReturn(meme)
        .when(memeRepositoryService).getMeme("1");

        Meme response = memeService.getMeme("1");

        Meme expected = new Meme("1","Blank","www.test.com","Test");

        assertEquals(expected, response);
    }
}
