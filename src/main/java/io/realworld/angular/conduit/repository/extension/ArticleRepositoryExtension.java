package io.realworld.angular.conduit.repository.extension;

import io.realworld.angular.conduit.model.Article;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryExtension {

    List<Article> getAllArticles(
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> offset,
            @RequestParam Optional<String> author,
            @RequestParam Optional<String> favorited,
            @RequestParam Optional<String> tag);
}
