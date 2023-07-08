package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.dto.UserDto;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("add-new-user")
    public ResponseDto<UserDto> addNewUser(@RequestBody UserDto userDto){
        return userService.addNewUser(userDto);
    }
    @GetMapping("get-by-id")
    public ResponseDto<UserDto> getById(@RequestParam Long id){
        return userService.getById(id);
    }
    @DeleteMapping("delete-by-id")
    public ResponseDto<UserDto> deleteById(@RequestParam Long id){
        return userService.deleteById(id);
    }
    @PutMapping("edit")
    public ResponseDto<UserDto> edit(@RequestBody UserDto userDto){
        return userService.edit(userDto);
    }
    @GetMapping("get-all-user")
    public ResponseDto<List<UserDto>> getAllUser(){
        return userService.getAllUser();
    }
}
