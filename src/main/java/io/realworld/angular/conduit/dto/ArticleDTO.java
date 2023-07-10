package io.realworld.angular.conduit.dto;

import lombok.With;

import java.util.List;

public record ArticleDTO(
        Long id,
        String slug,
        String title,
        String description,
        String body,
        List<TagDTO> tagList,
        String createdAt,
        String updateAt,
        boolean author,
        @With
        long favorited,
        @With
        UserDTO favoritesCount
) {
}
