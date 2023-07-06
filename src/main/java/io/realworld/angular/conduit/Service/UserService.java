package io.realworld.angular.conduit.Service;

import io.realworld.angular.conduit.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserDto> getUserById(Long id);

    ResponseEntity<UserDto> addUser(UserDto userDto);

    ResponseEntity<UserDto> updateUser(UserDto userDto, Long id);

    ResponseEntity<UserDto> deleteUser(Long id);
}
