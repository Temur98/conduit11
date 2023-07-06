package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.dto.response.UserResponse;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserResponse> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> addUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> deleteUser(Long id) {
        return null;
    }
}
