package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.ArticleDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleResponse {
    private ArticleDTO article;
    private List<ArticleDTO> articles;

}
