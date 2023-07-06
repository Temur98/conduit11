package io.realworld.angular.conduit.Service.impl;

import io.realworld.angular.conduit.Service.ArticleService;
import io.realworld.angular.conduit.dto.ArticleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Override
    public ResponseEntity<List<ArticleDto>> getUserArticlesByUserId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<ArticleDto>> getUsersFavoritedArticlesById(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleDto> getArticleBySlug(String slug) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleDto> updateArticle(String slug, ArticleDto articleDto) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleDto> addArticle(ArticleDto articleDto) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleDto> deleteArticle(String slug) {
        return null;
    }
}
