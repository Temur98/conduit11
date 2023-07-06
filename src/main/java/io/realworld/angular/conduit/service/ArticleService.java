package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import org.springframework.http.ResponseEntity;

public interface ArticleService {
    ResponseEntity<ArticleResponse> getById(String id);

    ResponseEntity<ArticleResponse> addArticle(ArticleDTO articleDTO);

    ResponseEntity<ArticleResponse> updateArticle(ArticleDTO articleDTO);

    ResponseEntity<ArticleResponse> deleteArticle(Long id);
}
