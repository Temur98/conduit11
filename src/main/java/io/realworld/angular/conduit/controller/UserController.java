package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.service.TagService;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
