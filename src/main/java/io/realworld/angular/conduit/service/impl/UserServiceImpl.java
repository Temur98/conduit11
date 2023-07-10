package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.dto.response.UserResponse;
import io.realworld.angular.conduit.mapper.UserMapper;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserResponse> registerUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> loginUser(UserDTO user) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> getCurrentUser() {
        return null;
    }
}
