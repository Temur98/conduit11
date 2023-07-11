package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.model.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ArticleWithOwtDto {
    public ArticleDTO toDTO(Article article){
        return article == null ? null : new ArticleDTO(
                article.getId(),
                article.getTitle().concat("-").concat(String.valueOf(article.getId())),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList().stream().map(TagMapper::toDto).collect(Collectors.toList()),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                false,
                1L,
                article.getAuthor() != null ? new ProfileDTO(
                        article.getAuthor().getUsername(),
                        article.getAuthor().getBio(),
                        article.getAuthor().getImage(),
                        null
                ) : null
        );
    }
}
