package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.repository.extension.ArticleExtension;

import java.util.List;
import java.util.Optional;

public class ArticleExtensionImpl implements ArticleExtension {
    @Override
    public List<ArticleDTO> getAllArticles(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {
        return null;
    }
}
