package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<CommentResponse> getCommentsBySlug(String slug);

    ResponseEntity<CommentResponse> addCommentBySlug(String slug, CommentDTO comment);

    void deleteComment(String slug, Long commentId);
}
