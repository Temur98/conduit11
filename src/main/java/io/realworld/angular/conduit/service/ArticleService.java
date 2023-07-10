package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ArticleService {
    ResponseEntity<ArticleResponse> getArticleBySlag(String slug);

    ResponseEntity<ArticleResponse> addArticle(ArticleDTO articleDTO);

    ResponseEntity<ArticleResponse> updateArticle(ArticleDTO articleDTO);


    ResponseEntity<ArticleResponse> updateArticleBySlag(String slag, ArticleDTO articleDTO);

    ResponseEntity<ArticleResponse> addFavorite(String slug);

    void deleteFavorite(String slug);

    void deleteArticle(String slug);
}
