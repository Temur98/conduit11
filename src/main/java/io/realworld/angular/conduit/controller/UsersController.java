package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.groups.OnCreate;
import io.realworld.angular.conduit.groups.OnUpdate;
import io.realworld.angular.conduit.service.UserService;
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
    public ResponseEntity<CommonResponse<UserDTO>> loginUser(@RequestBody CommonResponse<UserDTO> user){
        return userService.loginUser(user);
    }

    @PostMapping("/users")
    @Validated(OnCreate.class)
    public ResponseEntity<CommonResponse<UserDTO>> registerUser(@RequestBody CommonResponse<UserDTO> userDTOCommonResponse){
        return userService.registerUser(userDTOCommonResponse);
    }

    @GetMapping("/user")
    public ResponseEntity<CommonResponse<UserDTO>> getCurrentUser(){
        return userService.getCurrentUser();
    }

    @PutMapping("/user")
    @Validated(OnUpdate.class)
    public ResponseEntity<CommonResponse<UserDTO>> updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }
}
