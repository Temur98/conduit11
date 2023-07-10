package io.realworld.angular.conduit.service;


import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;



public interface UserService{

    ResponseEntity<UserResponse> registerUser(UserDTO userDTO);

    ResponseEntity<UserResponse> updateUser(UserDTO userDTO);

    ResponseEntity<UserResponse> loginUser(UserDTO user);

    ResponseEntity<UserResponse> getCurrentUser();
}
