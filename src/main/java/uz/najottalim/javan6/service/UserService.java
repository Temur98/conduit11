package uz.najottalim.javan6.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.javan6.dto.UserDto;

public interface UserService {
    ResponseEntity<UserDto> loginUser(UserDto profileDto);

    ResponseEntity<UserDto> registerUser(UserDto userDto);
}
