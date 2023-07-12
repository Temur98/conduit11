package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.UserMapper;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserDTO> registerUser(UserDTO userDTO) {
        if(userDTO == null) {
            throw new NotFoundException("user not found");
        }
        User user= userMapper.toEntity(userDTO);
        User save = userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(save));
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User user1 = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new NotFoundException("user not found"));
        User save = userRepository.save(user1);

        return ResponseEntity.ok(userMapper.toDto(save));
    }

    @Override
    public ResponseEntity<UserDTO> loginUser(UserDTO user) {
        User user1 = userMapper.toEntity(user);
        User user2 = userRepository.findByUsername(user1.getUsername())
                .orElseThrow(() -> new NotFoundException("user not found"));
        return ResponseEntity.ok(userMapper.toDto(user2));
    }

    @Override

    public ResponseEntity<UserDTO> getCurrentUser() {
     return null;
    }
}
