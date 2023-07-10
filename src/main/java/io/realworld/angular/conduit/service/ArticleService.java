package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface ArticleService {
    ResponseEntity<ArticleResponse> getById(String id);
    ResponseEntity<ArticleResponse> getArticleBySlag(String slag);
    ResponseEntity<ArticleResponse> getArticleComments(String slag);

    ResponseEntity<ArticleResponse> addArticle(ArticleDTO articleDTO);

    ResponseEntity<ArticleResponse> updateArticle(ArticleDTO articleDTO);

    ResponseEntity<ArticleResponse> deleteArticle(Long id);
//    ResponseEntity<ArticleResponse> deleteFavorite()
    ResponseEntity<ArticleResponse> getArticles(@RequestParam Integer limit,
                                                @RequestParam Integer offset,
                                                @RequestParam Optional<String> author,
                                                @RequestParam Optional<String> favorited,
                                                @RequestParam Optional<String> tag);
}
