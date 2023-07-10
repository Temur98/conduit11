package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.Profile;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleMapper {
    private final TagRepository tagRepository;

    public ArticleDTO toDto(Article article) {
        return article == null ? null : new ArticleDTO(
                article.getId(),
                article.getSlug(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList().stream().map(Tag::getName).toList(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                false,
                1000L,
                new Profile(
                        article.getUser().getUsername(),
                        article.getUser().getBio(),
                        article.getUser().getImage(),
                        false
                )
        );
    }

    public Article toEntity(ArticleDTO articleDTO) {
        return articleDTO == null ? null : new Article(
                articleDTO.getId(),
                articleDTO.getSlug(),
                articleDTO.getTitle(),
                articleDTO.getDescription(),
                articleDTO.getBody(),
                articleDTO.getCreateAt(),
                articleDTO.getUpdateAt(),
                articleDTO.getTagList().stream().map(tag -> tagRepository.findByName(tag).orElseThrow(() -> new NotFoundException("Tag not found"))).toList(),
                null
        );
    }
}
