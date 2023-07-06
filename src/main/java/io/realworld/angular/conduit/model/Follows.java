package io.realworld.angular.conduit.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "follows")
public class Follows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private Long followId;
    @ManyToOne(targetEntity = User.class)
    private User follower;
    @ManyToOne(targetEntity = User.class)
    private User following;
}
