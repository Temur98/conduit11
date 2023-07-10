package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.Profile;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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
                article.getTagList().stream().map(Tag::getName).collect(Collectors.toList()),
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

    public Article toEntity(ArticleDTO<? extends Object> articleDTO) {
        return articleDTO == null ? null : new Article(
                articleDTO.id(),
                articleDTO.slug(),
                articleDTO.title(),
                articleDTO.description(),
                articleDTO.body(),
                articleDTO.createdAt(),
                articleDTO.updateAt(),
                articleDTO.tagList().stream().map(tag -> tagRepository.findByName(tag).orElseThrow(() -> new NotFoundException("Tag not found"))).toList(),
                null
        );
    }
}
