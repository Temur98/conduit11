package io.realworld.angular.conduit.Service;

import io.realworld.angular.conduit.dto.ArticleDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticleService  {


    ResponseEntity<List<ArticleDto>> getUserArticlesByUserId(Long id);

    ResponseEntity<List<ArticleDto>> getUsersFavoritedArticlesById(Long userId);

    ResponseEntity<ArticleDto> getArticleBySlug(String slug);

    ResponseEntity<ArticleDto> updateArticle(String slug, ArticleDto articleDto);

    ResponseEntity<ArticleDto> addArticle(ArticleDto articleDto);

    ResponseEntity<ArticleDto> deleteArticle(String slug);
}
