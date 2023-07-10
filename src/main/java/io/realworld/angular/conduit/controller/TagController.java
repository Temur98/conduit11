package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Slf4j
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<TagDTO>>> getAll (){
        return tagService.getAll();
    }

}
