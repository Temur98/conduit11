package io.realworld.angular.conduit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    @JsonProperty(value = "author")
    private ProfileDto profileDto;
    @JsonProperty(value = "article")
    private ArticleDto articleDto;
    private String body;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
