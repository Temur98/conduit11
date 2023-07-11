package io.realworld.angular.conduit.service.mapper;

import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.dto.ProfileDto;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mapping(target = "slug", source = "article", qualifiedByName = "toDtoSlug")
    @Mapping(target = "favorited", source = "article", qualifiedByName = "toDtoFavorited")
    @Mapping(target = "favoritesCount", source = "article", qualifiedByName = "toDtoFavoritesCount")
    @Mapping(target = "author", source = "article", qualifiedByName = "toDtoAuthor")
    ArticleDto toDto(Article article, @Context ArticleRepository articleRepository, @Context UserRepository userRepository);

    Article toEntity(ArticleDto articleDTO);

    @Named("toDtoSlug")
    default String toDtoSlug(Article article){
        if (article == null) return null;
        return article.getTitle().concat("-").concat(String.valueOf(article.getId()));
    }

    @Named("toDtoFavorited")
    default boolean toDtoFavorited(@Context ArticleRepository articleRepository, Article article){
        if (article == null) return false;
        Long id = article.getId();
        //TODO change userId
        return articleRepository.isFavorited(0L, id);
    }

    @Named("toDtoFavoritesCount")
    default Long toDtoFavoritesCount(@Context ArticleRepository articleRepository, Article article){
        if (article == null) return null;
        return articleRepository.getFavoritesCount(article.getId());
    }

    @Named("toDtoAuthor")
    default ProfileDto toDtoAuthor(@Context UserRepository userRepository, Article article){
        //TODO change userId
        if (article == null) return null;
        return new ProfileDto(
                article.getId(),
                article.getAuthor().getUsername(),
                article.getAuthor().getBio(),
                article.getAuthor().getImage(),
                userRepository.isFollowedToArticleOwner(article.getAuthor().getId(), 0L)
        );
    }
}
