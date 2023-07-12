package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.groups.OnCreate;
import io.realworld.angular.conduit.groups.OnUpdate;
import io.realworld.angular.conduit.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@Validated
public class UsersController {
    private final UserService userService;

    @PostMapping("/users/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO user){
        return userService.loginUser(user);
    }

    @PostMapping("/users")
    @Validated(OnCreate.class)
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user){
        return userService.registerUser(user);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getCurrentUser(){
        return userService.getCurrentUser();
    }

    @PutMapping("/user")
    @Validated(OnUpdate.class)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }
}
