package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;


    @Override
    public ResponseEntity<ArticleResponse> getById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleResponse> getArticleBySlag(String slag) {
        return null;
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

    @Override
    public ResponseEntity<ArticleResponse> getArticles(Integer limit, Integer offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {
        return null;
    }
}
