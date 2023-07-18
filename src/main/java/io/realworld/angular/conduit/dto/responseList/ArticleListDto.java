package io.realworld.angular.conduit.dto.responseList;

import io.realworld.angular.conduit.dto.ArticleDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleListDto {
    private List<ArticleDto> articles;
}
