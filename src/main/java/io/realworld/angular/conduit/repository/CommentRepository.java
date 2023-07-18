package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByProfileId(Long id);
    Optional<Comment> findByIdAndArticleId(Long Id, Long articleId);
}
