package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.ArticleDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleResponse {
    private ArticleDto article;
}
