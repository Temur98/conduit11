package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.mapper.ArticleMapper;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;


    @Override
    public ResponseEntity<ArticleResponse> getArticles(Integer limit, Integer offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleResponse> getArticleBySlag(String slug) {
        int i = slug.lastIndexOf("-");
        Long id = Long.valueOf(slug.substring(i + 1));

        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        //TODO change userId
        boolean favorited = articleRepository.isFavorited(0L, id);
        long favoritesCount = articleRepository.getFavoritesCount(id);
        Article article = optionalArticle.get();
        ArticleDTO articleDTO = ArticleMapper.toDto(article);
        articleDTO.withFavorited(favorited);
        articleDTO.withFavoritesCount(favoritesCount);
        return ResponseEntity.ok(new ArticleResponse(articleDTO));
    }

    @Override
    public ResponseEntity<ArticleResponse> getArticleComments(String slag) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleResponse> addArticle(ArticleDTO articleDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleResponse> updateArticle(ArticleDTO articleDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleResponse> deleteArticle(Long id) {
        return null;
    }
}
