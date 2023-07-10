package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ArticleMapper {
    public static Article toEntity(ArticleDTO articleDTO){
        if(articleDTO == null) return null;
        return null;
    }
    public static ArticleDTO toDto(Article article){
        if(article == null) return null;
        return new ArticleDTO(
                article.getId(),
                article.getTitle().concat("-").concat(String.valueOf(article.getId())),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList().stream()
                        .map(TagMapper::toDTO)
                        .collect(Collectors.toList()),
                article.getCreatedAt(),
                article.getUpdateAt(),
                false,
                0L,
                UserMapper.toDto(article.getAuthor())
        );
    }
}
