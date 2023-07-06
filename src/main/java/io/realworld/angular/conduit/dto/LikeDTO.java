package io.realworld.angular.conduit.dto;


import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.User;

public record LikeDTO(
        Long id,
        User user,
        Article article
) {
}
