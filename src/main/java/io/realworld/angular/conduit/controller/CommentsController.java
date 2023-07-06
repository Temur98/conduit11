package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.Service.CommentService;
import io.realworld.angular.conduit.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/comments")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> addComment(@RequestBody CommentDto commentDto){
        return commentService.addComment(commentDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }

}
