package io.realworld.angular.conduit.dto;
import io.realworld.angular.conduit.model.User;

import java.time.LocalDate;
import java.util.List;
public record ArticleDto(

        Long id,
        User user,
        String title,
        String description,
        String body,
        Integer likes,
        LocalDate createdAt,
        LocalDate updatedAt
) {
}
