package com.crio.starter.repositoryService;

import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.Meme;

public interface MemeRepositoryService{
    Meme post(MemeEntity memeEntity);
    List<Meme> getLatest100Memes();
    Meme getMeme(String ID);

}
