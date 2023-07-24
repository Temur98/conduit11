package io.realworld.angular.conduit.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO{
        private Long id;
        private String slug;
        private String title;
        private String description;
        private String body;
        private List<String> tagList;
        private LocalDate createdAt;
        private LocalDate updateAt;
        private Boolean favourited;
        private Long favoritesCount;
        private ProfileDTO author;

}
