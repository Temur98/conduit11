package uz.najottalim.javan6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uz.najottalim.javan6.dto.UserDto;
import uz.najottalim.javan6.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/users/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody UserDto userDto){
        return userService.loginUser(userDto);
    }
    @PostMapping("/users")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        return userService.registerUser(userDto);
    }
}
