package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.CommentDTO;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<CommentDTO> getCommentsBySlug(String slug);

    ResponseEntity<CommentDTO> addCommentBySlug(String slug, CommentDTO comment);

    void deleteComment(String slug, Long commentId);
}
