package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.UserDTO;
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
    public ResponseEntity<UserDTO> registerUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> loginUser(UserDTO user) {
        return null;
    }

    @Override

    public ResponseEntity<UserDTO> getCurrentUser() {
        return null;
    }
}
