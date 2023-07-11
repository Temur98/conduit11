package io.realworld.angular.conduit.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "articles")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String body;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @ManyToMany
    @JoinTable(
            name = "article_tag",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tagList;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
