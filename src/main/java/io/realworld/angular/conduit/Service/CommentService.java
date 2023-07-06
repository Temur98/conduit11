package io.realworld.angular.conduit.Service;

import io.realworld.angular.conduit.dto.CommentDto;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    ResponseEntity<CommentDto> addComment(CommentDto commentDto);

    ResponseEntity<CommentDto> deleteComment(Long id);
}
