package io.realworld.angular.conduit.service;


import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.dto.UserDto;
import io.realworld.angular.conduit.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UserService{
//    ResponseDto<UserDto> addNewUser(UserDto userDto);
//
//    ResponseDto<UserDto> getById(Long id);
//
//    ResponseDto<UserDto> deleteById(Long id);
//
//    ResponseDto<UserDto> edit(UserDto userDto);
//
//    ResponseDto<List<UserDto>> getAllUser();

    ResponseEntity<UserResponse> loginUser(UserResponse userResponse) throws Exception;

    ResponseEntity<UserResponse> registerUser(UserResponse userResponse);

    ResponseEntity<UserResponse> updateUser(UserResponse userResponse);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    ResponseEntity<UserResponse> getUser();
}
