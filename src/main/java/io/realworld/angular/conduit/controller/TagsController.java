package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
@Slf4j
@RequiredArgsConstructor
public class TagsController {
    private final TagService tagService;
}
