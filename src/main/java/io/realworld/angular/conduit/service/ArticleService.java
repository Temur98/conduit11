package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ArticleService {
    ResponseEntity<ArticleResponse> getArticles(Optional<Integer> pageNum, Optional<Integer> size);
    ResponseEntity<ArticleResponse> getArticleBySlag(String slag);
    ResponseEntity<ArticleResponse> getArticleComments(String slag);
    ResponseEntity<ArticleResponse> addArticle(ArticleDTO articleDTO);

    ResponseEntity<ArticleResponse> updateArticle(ArticleDTO articleDTO);

    ResponseEntity<ArticleResponse> deleteArticle(Long id);


}
