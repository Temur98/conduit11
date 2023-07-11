package io.realworld.angular.conduit.service;


import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.dto.UserDto;
import io.realworld.angular.conduit.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService{
    ResponseDto<UserDto> addNewUser(UserDto userDto);

    ResponseDto<UserDto> getById(Long id);

    ResponseDto<UserDto> deleteById(Long id);

    ResponseDto<UserDto> edit(UserDto userDto);

    ResponseDto<List<UserDto>> getAllUser();

    ResponseEntity<UserResponse> loginUser(UserResponse userResponse);

    ResponseEntity<UserResponse> registerUser(UserResponse userResponse);

    ResponseEntity<UserResponse> updateUser(UserResponse userResponse);
}
