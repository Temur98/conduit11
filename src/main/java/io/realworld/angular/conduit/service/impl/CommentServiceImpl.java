package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Override
    public ResponseEntity<CommentResponse> getCommentsBySlug(String slug) {
        return null;
    }

    @Override
    public ResponseEntity<CommentResponse> addCommentBySlug(String slug, CommentDTO comment) {
        return null;
    }

    @Override
    public void deleteComment(String slug, Long commentId) {

    }
}
