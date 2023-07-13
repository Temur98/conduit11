package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.CommonResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArticleService {
    ResponseEntity<CommonResponse<List<ArticleDTO>>> getAllArticles(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag);
    ResponseEntity<CommonResponse<ArticleDTO>> getArticleBySlag(String slug);
    ResponseEntity<Map<String,ArticleDTO>> addArticle(Map<String,ArticleDTO> articleDTO, Principal principal);
    ResponseEntity<ArticleDTO> updateArticle(ArticleDTO articleDTO);
    ResponseEntity<ArticleDTO> updateArticleBySlag(String slag, ArticleDTO articleDTO);
    ResponseEntity<ArticleDTO> addFavorite(String slug);
    void deleteFavorite(String slug, Principal principal);
    void deleteArticle(String slug);
}
