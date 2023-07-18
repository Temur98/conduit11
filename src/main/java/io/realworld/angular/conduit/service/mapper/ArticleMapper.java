package io.realworld.angular.conduit.service.mapper;

import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.dto.ProfileDto;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.ProfileRepository;
import io.realworld.angular.conduit.repository.TagRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleMapper {
    private final ArticleRepository articleRepository;
    private final ProfileMapper profileMapper;
    private final ProfileRepository profileRepository;
    private final TagRepository tagRepository;
    public ArticleDto toDto(Article article){
        ArticleDto articleDto =  new ArticleDto(
                article.getId(),
                article.getTitle() + "-" + article.getId(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTags().stream().map(Tag::getName).toList(),
                article.getCreateAt(),
                article.getUpdateAt(),
                false,
                articleRepository.getFavoritesCount(article.getId()),
                profileMapper.toDto(article.getProfile()),
                null

        );
        profileRepository.findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .ifPresent(profile -> {
                    articleDto.setFavorite(articleRepository.isFavorited(profile.getId(), article.getId()));
                });
        return articleDto;
    }

    public Article toEntity(ArticleDto articleDto){
        List<Tag> tags = new ArrayList<>();
        articleDto.getTags().forEach(tag->{
            if (tagRepository.findByName(tag).isPresent()){
                tags.add(tagRepository.findByName(tag).get());
            }else {
                tags.add(tagRepository.save(new Tag(null, tag)));
            }
        });
        return new Article(
                articleDto.getId(),
                articleDto.getTitle(),
                articleDto.getDescription(),
                articleDto.getBody(),
                tags,
                articleDto.getCreatedAt(),
                articleDto.getUpdatedAt(),
                null,
                profileRepository.findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                        .orElseThrow()
        );
    }


}
