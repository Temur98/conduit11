package io.realworld.angular.conduit.Service.impl;

import io.realworld.angular.conduit.Service.CommentService;
import io.realworld.angular.conduit.dto.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public ResponseEntity<CommentDto> addComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public ResponseEntity<CommentDto> deleteComment(Long id) {
        return null;
    }
}
