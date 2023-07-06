package io.realworld.angular.conduit.service;


import io.realworld.angular.conduit.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService{
    ResponseEntity<UserDTO> getById(Long id);
}
