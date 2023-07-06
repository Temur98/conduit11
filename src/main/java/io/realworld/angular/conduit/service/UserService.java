package io.realworld.angular.conduit.service;


import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;



public interface UserService{
    ResponseEntity<UserResponse> getById(Long id);

    ResponseEntity<UserResponse> addUser(UserDTO userDTO);

    ResponseEntity<UserResponse> updateUser(UserDTO userDTO);

    ResponseEntity<UserResponse> deleteUser(Long id);
}
