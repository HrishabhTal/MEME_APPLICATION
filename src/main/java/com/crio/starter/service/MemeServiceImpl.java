package com.crio.starter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.GetMemeResponses;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repositoryService.MemeRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class MemeServiceImpl implements MemeService {
    
    @Autowired
    private final MemeRepositoryService memeRepositoryService;

    @Override
    public ResponseDto postMeme(MemeEntity memeEntity) {
        
        log.info("Meme Sent to {} " + memeEntity);
        
        Meme meme = memeRepositoryService.post(memeEntity);
        
        log.info("Meme received " + meme);
        
        return new ResponseDto(meme.getId());
    }

    @Override
    public GetMemeResponses getMemes() {
        List<Meme> memes = memeRepositoryService.getLatest100Memes();
        
        return new GetMemeResponses(memes);
    }

    @Override
    public Meme getMeme(String memeId) {
        Meme meme = memeRepositoryService.getMeme(memeId);
        
        log.info("Meme Received by Service " + meme);
        
        return meme;
    }

    
}
