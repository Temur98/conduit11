package io.realworld.angular.conduit.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.With;

import java.time.LocalDate;
import java.util.List;

@JsonRootName("article")
public record ArticleDTO(
        Long id,
        String slug,
        String title,
        String description,
        String body,
        List<String> tagList,
        LocalDate createdAt,
        @With
        LocalDate updateAt,
        @With
        Boolean favorited,
        @With
        Long favoritesCount,
        ProfileDTO author
) {

}
