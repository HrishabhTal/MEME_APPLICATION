package com.crio.starter.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.crio.starter.dto.Meme;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMemeResponses {
    List<Meme> memes;
}
