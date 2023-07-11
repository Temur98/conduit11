package io.realworld.angular.conduit.repository.extension;

import io.realworld.angular.conduit.dto.ArticleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryExtension {

    List<ArticleDTO> getAllArticles(
            @RequestParam Optional<Integer> limit,
            @RequestParam Optional<Integer> offset,
            @RequestParam Optional<String> author,
            @RequestParam Optional<String> favorited,
            @RequestParam Optional<String> tag);
}
