package io.realworld.angular.conduit.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private String createAt;
    @ManyToOne(targetEntity = User.class)
    private User authors;
    @ManyToOne(targetEntity = Article.class)
    private Article article;

}
