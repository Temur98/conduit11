package io.realworld.angular.conduit.repository.extension;

import io.realworld.angular.conduit.dto.ArticleDTO;

import java.util.List;
import java.util.Optional;

public interface ArticleExtension {
    List<ArticleDTO> getAllArticles(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag);
}
