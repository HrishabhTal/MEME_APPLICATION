package com.crio.starter.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.util.List;
import javax.validation.Valid;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.Meme;
import com.crio.starter.exchange.GetMemeResponses;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
@RequestMapping("/")
public class MemesController {
    
    @Autowired
    private final MemeService memeService;

    private final String APIENDPOINT = "memes";
    
    
    @PostMapping(APIENDPOINT)
    public ResponseEntity<?> postMeme(@RequestBody @Valid MemeEntity memeEntity){

        log.info("Meme Request received {}" + memeEntity);
        
        ResponseDto memeid = memeService.postMeme(memeEntity);
        String POSTEXIST = "Post Already Exist";
       

        if(memeid.getId() == null){
            return ResponseEntity.status(409).body(POSTEXIST);
        }
        
        log.info("Meme created {}" + memeid);

        return ResponseEntity.ok().body(memeid);
    }
    
    @GetMapping(APIENDPOINT)
    public ResponseEntity<?> getMemes(){
        GetMemeResponses responses = memeService.getMemes();
        
        log.info("Total memes " + responses.getMemes().size());
        List<Meme> memes = responses.getMemes();

        return ResponseEntity.ok().body(memes);
    }

    @GetMapping(APIENDPOINT + "/{Id}")
    public ResponseEntity<?> getMeme(@PathVariable @Valid String Id){
        
        log.info("Get the meme for id: " + Id);
        
        Meme meme = memeService.getMeme(Id);
        String NOTFOUND = "Post Not Found.";
        
        return (meme.getId() == null) ? ResponseEntity.status(404).body(NOTFOUND) : ResponseEntity.ok().body(meme); 
    }




}
