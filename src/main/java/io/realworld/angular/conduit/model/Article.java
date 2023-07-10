package io.realworld.angular.conduit.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slag;
    @NotEmpty
    @Column(name = "title", nullable = false)
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
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;









}
