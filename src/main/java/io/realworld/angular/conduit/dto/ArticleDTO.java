package io.realworld.angular.conduit.dto;



import lombok.With;

import java.time.LocalDate;
import java.util.List;

public record ArticleDTO(
        Long id,
        String slug,
        String title,
        String description,
        String body,
        List<TagDTO> tagList,
        LocalDate createdAt,
        LocalDate updateAt,
        @With
        Boolean favorited,
        @With
        Long favoritesCount,
        UserDTO author
) {

}
