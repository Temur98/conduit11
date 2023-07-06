package io.realworld.angular.conduit.dto;

import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public record CommentDTO(
        Long id,
        String body,
        LocalDate createAt,
        User user,
        Article article
) {
}
