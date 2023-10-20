package com.crio.starter.service;


import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.GetMemeResponses;
import com.crio.starter.exchange.ResponseDto;

public interface MemeService {
    ResponseDto postMeme(MemeEntity memeEntity);
    GetMemeResponses getMemes();
    Meme getMeme(String memeId);
}
