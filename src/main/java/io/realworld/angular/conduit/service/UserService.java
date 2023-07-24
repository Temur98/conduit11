package io.realworld.angular.conduit.service;


import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService{
    ResponseEntity<UserResponse> registerUser(UserResponse UserResponse);

    ResponseEntity<CommentResponse<UserDTO>> updateUser(CommentResponse<UserDTO> userDTO);

    ResponseEntity<UserResponse> loginUser(UserResponse userResponse);

    ResponseEntity<CommentResponse> getCurrentUser();
}
