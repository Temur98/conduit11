package io.realworld.angular.conduit.dto;

import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.model.User;

import java.time.LocalDate;
import java.util.List;

public record ArticleDTO(
        Long id,
        String slug,
        String title,
        String description,
        String body,
        LocalDate createAt,
        LocalDate updateAt,
        List<Tag> tagList,
        User user
) {
}
