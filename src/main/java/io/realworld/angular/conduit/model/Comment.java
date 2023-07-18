package io.realworld.angular.conduit.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    @ManyToOne
//    @JoinColumn(name = "user_id")
    private Profile profile;
    @ManyToOne
//    @JoinColumn(name = "article_id")
    private Article article;
}
