package io.realworld.angular.conduit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slag;
    private String title;
    private String description;
    private String body;
    @ManyToMany
    @JoinTable(
            name = "article_tag"







    )
    private List<Tag> tagList;
    private String createdAt;
    private String updateAt;
    private Boolean favorited;
    private Long amountOfLikes;
//    private Profile profile;









}
