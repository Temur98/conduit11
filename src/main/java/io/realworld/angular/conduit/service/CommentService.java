package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    ResponseEntity<CommentResponse<List<CommentDTO>>> getCommentsBySlug(String slug);
    ResponseEntity<CommentResponse<CommentDTO>> addCommentBySlug(String slug, CommentResponse<CommentDTO> comment);
    void deleteComment(String slug, Long commentId);
}
