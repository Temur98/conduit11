package io.realworld.angular.conduit.repository.ext;

import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.model.Article;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryExtension {
    List<Article> getArticleListPageable(Integer limit, Integer offset, Optional<String> author, Optional<String> favorited, Optional<String> tag);

    List<Article> getArticlesByFollower(Long id, Integer limit, Integer offset);

//    @Transactional
//    void likeArticle(Long articleId, Long userId);

    @Transactional
    void deleteLike(Long userId, Long articleId);

    @Transactional
    boolean isFavorited(Long articleId, Long userId);
}
