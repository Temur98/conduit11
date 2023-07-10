package io.realworld.angular.conduit.dto;

import java.time.LocalDate;
import java.util.List;

public record ArticleDTO<slug>(
        Long id,
        String slug,
        String title,
        String description,
        String body,
        List<String> tagList,
        LocalDate createdAt,
        LocalDate updateAt,
        Boolean favorited,
        Long favoritesCount,
        Profile author
) {

}
