package io.realworld.angular.conduit.Service.impl;

import io.realworld.angular.conduit.Service.UserService;
import io.realworld.angular.conduit.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<UserDto> addUser(UserDto userDto) {
        return null;
    }

    @Override
    public ResponseEntity<UserDto> updateUser(UserDto userDto, Long id) {
        return null;
    }

    @Override
    public ResponseEntity<UserDto> deleteUser(Long id) {
        return null;
    }
}
