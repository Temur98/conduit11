package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getById (@PathVariable Long id){
        return commentService.getById(id);
    }

    @GetMapping
    public ResponseEntity<CommentResponse> getAll (){
        return commentService.getAll();
    }
    @PostMapping
    public ResponseEntity<CommentResponse> addComment(@RequestBody CommentDTO commentDTO){
        return commentService.addComment(commentDTO);
   }

   @PostMapping
    public ResponseEntity<CommentResponse> updateComment(@RequestBody CommentDTO commentDTO){
        return commentService.updateComment(commentDTO);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
   }


}
