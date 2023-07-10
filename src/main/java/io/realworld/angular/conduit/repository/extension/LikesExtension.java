package io.realworld.angular.conduit.repository.extension;

import io.realworld.angular.conduit.dto.ArticleDTO;
import org.springframework.http.ResponseEntity;

public interface LikesExtension {
    ResponseEntity<ArticleDTO> addLike(Long idBySlug, Long userId);

    void removeLike(Long idBySlug, Long userId);
}
