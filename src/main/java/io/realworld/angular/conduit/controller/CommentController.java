package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.CommentDto;
import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("add-new-comment")
    public ResponseDto<CommentDto> addNewComment(@RequestBody CommentDto commentDto){
        return commentService.addNewComment(commentDto);
    }
    @GetMapping("get-by-id")
    public ResponseDto<CommentDto> getById(@RequestParam Long id){
        return commentService.getById(id);
    }
    @DeleteMapping("delete-by-id")
    public ResponseDto<CommentDto> deleteById(@RequestParam Long id){
        return commentService.deleteById(id);
    }
    @PutMapping("edit")
    public ResponseDto<CommentDto> edit(@RequestBody CommentDto commentDto){
        return commentService.edit(commentDto);
    }
    @GetMapping("get-all-comment")
    public ResponseDto<List<CommentDto>> getAllComment(){
        return commentService.getAllComment();
    }
}
