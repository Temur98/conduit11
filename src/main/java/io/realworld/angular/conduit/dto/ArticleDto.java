package io.realworld.angular.conduit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDto {
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String body;
    @JsonProperty(value = "tagList")
    private List<TagDto> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @JsonProperty(value = "favorited",defaultValue = "false")
    private Boolean favorite;
    @JsonProperty(defaultValue = "0")
    private Long favoritesCount;
    @JsonProperty(value = "author")
    private ProfileDto profileDto;
}
