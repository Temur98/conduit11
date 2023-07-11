package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Tag;
import org.springframework.stereotype.Service;

@Service
public class ArticleMapperWithoutRepositories {
    public ArticleDTO toDto(Article article){
        return article == null ? null : new ArticleDTO(
                article.getId(),
                article.getTitle(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList().stream().map(Tag::getName).toList(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                false,
                1L,
                article.getAuthor() != null ? new ProfileDTO(
                                article.getAuthor().getUsername(),
                                article.getAuthor().getBio(),
                                article.getAuthor().getImage(),
                                false
                        ) : null
                );
    }
}
