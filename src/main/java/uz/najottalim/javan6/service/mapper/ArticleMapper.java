package uz.najottalim.javan6.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dto.articledto.ArticleDto;
import uz.najottalim.javan6.entity.Article;
import uz.najottalim.javan6.entity.Tag;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleMapper {
    private final TagMapper tagMapper;
    private final ProfileMapper profileMapper;

    public ArticleDto toDto(Article article){
        return new ArticleDto(
                article.getId(),
                article.getTitle().replace(" ","-")+"-"+article.getId(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTags().stream().map(Tag::getName).collect(Collectors.toList()),
                article.getCreateAt(),
                article.getUpdateAt(),
                false,
                0L,
                profileMapper.toDto(article.getProfile())
        );
    }
}
