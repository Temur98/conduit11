package io.realworld.angular.conduit.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.realworld.angular.conduit.model.User;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@JsonRootName("article")
public record ArticleDTO(
        Long id,
        String slug,
        String title,
        String description,
        String body,
        List<TagDTO> tagList,
        LocalDate createdAt,
        LocalDate updateAt,
        Boolean favorited,
        Long favoritesCount,
        ProfileDTO author
) {


}
