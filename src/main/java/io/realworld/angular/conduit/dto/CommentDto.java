package io.realworld.angular.conduit.dto;

import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.User;


import java.time.LocalDate;

public record CommentDto(
        Long id,
        String body,
        Article article,
        User user,
        LocalDate createdAt
) {

}
