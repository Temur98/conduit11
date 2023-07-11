package io.realworld.angular.conduit.repository.extension;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.model.Article;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ArticleExtension {
    List<Article> getArticlesPageable(Integer limit, Integer offset, Optional<String> author, Optional<String> favorited, Optional<String> tag);
    void likeArticle(Long articleId, Long userId);
    boolean isCurrentUserLiked(Long articleId,Long userId);
}
