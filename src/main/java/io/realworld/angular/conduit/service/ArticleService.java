package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.dto.response.ArticleResponse;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.dto.responseList.CommentListDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    ResponseDto<ArticleDto> addNewArticle(ArticleDto articleDto);

    ResponseDto<ArticleDto> getById(Long id);

    ResponseDto<ArticleDto> deleteById(Long id);

    ResponseDto<ArticleDto> edit(ArticleDto articleDto);

    ResponseDto<List<ArticleDto>> getAllArticle();

    ResponseEntity<ArticleDto> getArticles(Integer limit, Integer offset, Optional<String> author, Optional<String> favorited, Optional<String> tag);

    ResponseEntity<ArticleResponse> getArticleBySlug(String slug);

    ResponseEntity<CommentListDto> getArticleComments(String slug);

    ResponseEntity<ArticleDto> getArticlesByToken(Integer limit, Integer offset);

    ResponseEntity<ArticleResponse> addArticle(ArticleResponse articleResponse);

    ResponseEntity<ArticleResponse> likeArticle(String slug);

    ResponseEntity<ArticleResponse> deleteLike(String slug);

    ResponseEntity<CommentResponse> addComment(String slug, CommentResponse commentResponse);

    void deleteComment(String slug, Long id);

    void deleteArticle(String slug);
}
