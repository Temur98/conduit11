package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

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
    public ResponseEntity<UserDTO> loginUser(UserDTO user) {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> getCurrentUser() {
        return null;
    }
}
