package io.realworld.angular.conduit.service;


import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;



public interface UserService{

    ResponseEntity<UserResponse> registerUser(UserResponse userResponse);

    ResponseEntity<UserDTO> updateUser(UserDTO userDTO);

    ResponseEntity<UserDTO> loginUser(UserDTO user);

    ResponseEntity<UserDTO> getCurrentUser();
}
