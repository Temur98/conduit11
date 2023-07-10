package io.realworld.angular.conduit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleListDTO {
    private List<ArticleDTO> articles;
    private Integer size;
}
