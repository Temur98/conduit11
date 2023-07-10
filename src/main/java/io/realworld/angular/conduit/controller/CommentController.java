package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @GetMapping("/{slug}/comments")
    public ResponseEntity<CommonResponse<List<CommentDTO>>> getCommentsBySlug(@PathVariable String slug){
        return commentService.getCommentsBySlug(slug);
    }
    @PostMapping("/{slug}/comments")
    public ResponseEntity<CommentDTO> addCommentBySlug(@PathVariable String slug, @RequestBody CommentDTO comment){
        return commentService.addCommentBySlug(slug, comment);
    }

    @DeleteMapping("/{slug}/comments/{commentId}")
    public void deleteComment(@PathVariable String slug, @PathVariable Long commentId){
        commentService.deleteComment(slug, commentId);
    }



}
