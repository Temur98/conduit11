package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.CommonResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface CommentService {
    ResponseEntity<CommonResponse<List<CommentDTO>>> getCommentsBySlug(String slug);

    ResponseEntity<CommonResponse<CommentDTO>> addCommentBySlug(String slug, CommentDTO comment, Principal principal);

    void deleteComment(String slug, Long commentId);
}
