package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ArticleServiceImpl implements ArticleService {


    @Override
    public ResponseEntity<ArticleResponse> getById(String id) {
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
