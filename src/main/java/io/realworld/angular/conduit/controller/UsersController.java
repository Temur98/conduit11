package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.dto.response.UserResponse;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
