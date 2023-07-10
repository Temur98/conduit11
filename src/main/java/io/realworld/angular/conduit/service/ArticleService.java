package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ArticleService {
    ResponseEntity<ArticleDTO> findById(Long id);

    ResponseEntity<ArticleResponse> getAllArticles(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag);

    ResponseEntity<ArticleResponse> getArticleBySlag(String slag);

    ResponseEntity<ArticleResponse> getArticleComments(String slag);

    ResponseEntity<ArticleResponse> addArticle(ArticleDTO articleDTO);

    ResponseEntity<ArticleResponse> updateArticle(ArticleDTO articleDTO);

    ResponseEntity<ArticleResponse> deleteArticle(Long id);
}
