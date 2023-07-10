package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    ResponseEntity<CommentResponse> getById(Long id);

    ResponseEntity<CommentResponse> getAll();

    ResponseEntity<CommentResponse> addComment(CommentDTO commentDTO);

    ResponseEntity<CommentResponse> updateComment(CommentDTO commentDTO);

    ResponseEntity<CommentResponse> deleteComment(Long id);
}
