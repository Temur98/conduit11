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
import lombok.RequiredArgsConstructor;
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
    private final TagMapper tagMapper;

    public ArticleDTO toDto(Article article) {
        if (article == null) return null;
        String email     = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email);
        Long userId = null;
        if (user.isPresent()) {
            userId = user.get().getId();
        }
        User author = article.getAuthor();
        ProfileDTO profileDTO = new ProfileDTO(author.getUsername(), author.getBio(), author.getImage(), true);

        System.out.println(article.getTagList());
        return new ArticleDTO(
                article.getId(),
                toSlug(article.getTitle(), article.getId()),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList().stream().map(Tag::getName).toList(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                articleRepository.isFavorited(userId, article.getId()) == 0,
                articleRepository.getFavoritesCount(article.getId()),
                profileDTO
        );
    }


    public Article toEntity(ArticleDTO articleDTO) {
        if (articleDTO == null) return null;
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(name).orElseThrow(() -> new NotFoundException("User not found"));

        return new Article(
                articleDTO.getId(),
                articleDTO.getTitle(),
                articleDTO.getDescription(),
                articleDTO.getBody(),
                articleDTO.getCreatedAt(),
                articleDTO.getUpdateAt(),
                tagMapper.toEntities(articleDTO.getTagList()),
                Collections.singletonList(null),
                user
        );
    }

    private String toSlug(String title, Long id) {
        return title.replace(" ", "-") + "-" + id;
    }
}
