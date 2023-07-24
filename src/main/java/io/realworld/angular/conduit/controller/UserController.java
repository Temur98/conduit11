package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.dto.response.UserResponse;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserResponse userResponse){
        return userService.loginUser(userResponse);
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserResponse user){
        return userService.registerUser(user);
    }

    @GetMapping("/user")
    public ResponseEntity<CommentResponse> getCurrentUser(){
        return userService.getCurrentUser();
    }

    @PutMapping("/user")
    public ResponseEntity<CommentResponse<UserDTO>> updateUser(@RequestBody CommentResponse<UserDTO> userDTOResponse){
        return userService.updateUser(userDTOResponse);
    }

}
