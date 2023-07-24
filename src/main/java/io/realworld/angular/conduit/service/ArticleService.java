package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArticleService {
    ResponseEntity<CommentResponse<List<ArticleDTO>>>getAllArticles(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag);

    ResponseEntity<CommentResponse<ArticleDTO>> getArticleBySlag(String slug);

    ResponseEntity<Map<String,ArticleDTO>> addArticle(Map<String,ArticleDTO> articleDTO);

    ResponseEntity<ArticleDTO> updateArticle(ArticleDTO articleDTO);


    ResponseEntity<ArticleDTO> updateArticleBySlag(String slag, ArticleDTO articleDTO);
    ResponseEntity<CommentResponse<ArticleDTO>> getOwnUserArticleByPagination(Optional<Integer> limit, Optional<Integer> offset);

    ResponseEntity<ArticleDTO> addFavorite(String slug);

    void deleteFavorite(String slug, Principal principal);

    void deleteArticle(String slug);
}
