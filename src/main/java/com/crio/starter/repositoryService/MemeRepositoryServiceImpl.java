package com.crio.starter.repositoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.util.ArrayList;
import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.dto.Meme;
import java.util.Optional;
import com.crio.starter.repository.MemeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class MemeRepositoryServiceImpl implements MemeRepositoryService {
    
    @Autowired
    private final MemeRepository memeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Meme post(MemeEntity memeEntity) {
        MemeEntity meme = null;
        try {
            meme = memeRepository.insert(memeEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return (meme == null) ? new Meme() : this.modelMapper.map(meme, Meme.class);
    }

    @Override
    public List<Meme> getLatest100Memes() {
        List<MemeEntity> memesEntities = memeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        
        List<Meme> memes = new ArrayList<Meme>();

        for(MemeEntity memeEntity: memesEntities){
            memes.add(this.modelMapper.map(memeEntity, Meme.class));
        }
        
        return (memes.size() >= 100) ? memes.subList(0, 100) : memes ;
    }

    @Override
    public Meme getMeme(String ID) {
        Optional<MemeEntity> meme =  memeRepository.findById(ID);

        return (meme.isEmpty()) ? new Meme() : this.modelMapper.map(meme.get(), Meme.class); 
    }
    
}
