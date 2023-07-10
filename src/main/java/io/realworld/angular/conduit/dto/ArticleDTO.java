package io.realworld.angular.conduit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
    private LocalDate createAt;
    private LocalDate updateAt;
    private Boolean favourited;
    private Long favoritesCount;
    private Profile author;
}
