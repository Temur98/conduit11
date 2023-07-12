package io.realworld.angular.conduit.service;


import io.realworld.angular.conduit.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    ResponseEntity<UserDTO> registerUser(UserDTO userDTO);

    ResponseEntity<UserDTO> updateUser(UserDTO userDTO);

    ResponseEntity<UserDTO> loginUser(UserDTO user);

    ResponseEntity<UserDTO> getCurrentUser();
}
