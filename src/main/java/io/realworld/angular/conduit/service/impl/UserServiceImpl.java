package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.exception.NotRegisteredException;
import io.realworld.angular.conduit.mapper.UserMapper;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("default")));
    }


    @Override
    public ResponseEntity<UserDTO> registerUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> loginUser(UserDTO userDTO) {
        User user = userRepository.findByUsername(userDTO.userName()).orElseThrow(() -> new NotFoundException("Username not found"));
        String encode = passwordEncoder.encode(userDTO.password());
        if (encode.equals(user.getPassword())){
            return ResponseEntity.ok(userMapper.toDto(user));
        }
        throw new NotRegisteredException("User not authentication");
    }

    @Override
    public ResponseEntity<UserDTO> getCurrentUser() {
        return null;
    }
}
