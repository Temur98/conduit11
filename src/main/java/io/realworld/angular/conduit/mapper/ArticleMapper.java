package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticleMapper {
    public static Article toEntity(ArticleDTO articleDTO){
        if(articleDTO == null) return null;
        return new Article(

        );
    }
    public static ArticleDTO toDto(Article article){
        if(article == null) return null;
        return new ArticleDTO(
                article.getId(),
                article.getSlug(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getCreateAt(),
                article.getUpdateAt(),
                article.getTagList(),
                article.getUser()
        );
    }
}
