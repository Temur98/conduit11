package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.Service.UserService;
import io.realworld.angular.conduit.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id){
        return userService.updateUser(userDto,id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(Long id){
        return userService.deleteUser(id);
    }
}
