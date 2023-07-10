package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.dto.response.TagResponse;
import io.realworld.angular.conduit.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Slf4j
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> getById (@PathVariable Long id){
        return tagService.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAll (){
        return tagService.getAll();
    }
    @PostMapping
    public ResponseEntity<TagResponse> addTag(@RequestBody TagDTO tagDTO){
        return tagService.addTag(tagDTO);
    }

    @PostMapping
    public ResponseEntity<TagResponse> updateTag(@RequestBody TagDTO tagDTO){
        return tagService.updateTag(tagDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TagResponse> deleteTag(@PathVariable Long id){
        return tagService.deleteTag(id);
    }

}
