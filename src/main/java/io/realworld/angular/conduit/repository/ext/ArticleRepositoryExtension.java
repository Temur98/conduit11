package io.realworld.angular.conduit.repository.ext;

import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryExtension {
    List<ArticleDto> getArticleListPageable(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag);

}
