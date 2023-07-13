package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.TagRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.repository.extension.LikesExtension;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleMapper {
    private final TagRepository tagRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleDTO toDto(Article article){
        if (article == null) return null;
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(name);
        Long userId = null;
        if (user.isPresent()){
            userId = user.get().getId();
        }
        User author = article.getAuthor();
        ProfileDTO profileDTO = new ProfileDTO(author.getUsername(),author.getBio(),author.getImage(),true);
        return new ArticleDTO(
                article.getId(),
                toSlug(article.getTitle(), article.getId()),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList().stream().map(Tag::getName).toList(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                userId != null && articleRepository.isFavorited(userId, article.getId()),
                articleRepository.getFavoritesCount(article.getId()),
                profileDTO
        );
    }


    public Article toEntity(ArticleDTO articleDTO){
        if (articleDTO == null) return null;
        List<Tag> tagList = new ArrayList<>();
        articleDTO.tagList().forEach(tag -> tagList.add(tagRepository.findByName(tag).orElseThrow(() -> new NotFoundException("Tag not found"))));
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new NotFoundException("User not found"));

        return new Article(
                articleDTO.id(),
                articleDTO.title(),
                articleDTO.description(),
                articleDTO.body(),
                articleDTO.createdAt(),
                articleDTO.updateAt(),
                tagList,
                Collections.singletonList(null),
                user
        );
    }

    private String toSlug(String title,Long id){
        return title.replace(" ", "-")+"-"+id;
    }
}
