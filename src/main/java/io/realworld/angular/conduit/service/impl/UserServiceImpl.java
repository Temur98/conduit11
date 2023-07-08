package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.dto.UserDto;
import io.realworld.angular.conduit.model.Users;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.UserService;
import io.realworld.angular.conduit.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public ResponseDto<UserDto> addNewUser(UserDto userDto) {
        try {
            return ResponseDto.<UserDto>builder()
                    .code(0)
                    .success(true)
                    .message("OK")
                    .data(userMapper.toDto(userRepository.save(userMapper.toEntity(userDto))))
                    .build();
        }catch (Exception e){
            return ResponseDto.<UserDto>builder()
                    .code(1)
                    .success(false)
                    .message("Database Error")
                    .data(userDto)
                    .build();
        }
    }

    @Override
    public ResponseDto<UserDto> getById(Long id) {
        return userRepository.findById(id)
                .map(users -> ResponseDto.<UserDto>builder()
                        .code(0)
                        .success(true)
                        .message("OK")
                        .data(userMapper.toDto(users))
                        .build())
                .orElse(ResponseDto.<UserDto>builder()
                        .code(-1)
                        .success(false)
                        .message("Not Found")
                        .build());
    }

    @Override
    public ResponseDto<UserDto> deleteById(Long id) {
        return userRepository.findById(id)
                .map(users -> {
                    userRepository.deleteById(id);
                    return ResponseDto.<UserDto>builder()
                            .code(0)
                            .success(true)
                            .message("OK")
                            .data(userMapper.toDto(users))
                            .build();
                })
                .orElse(ResponseDto.<UserDto>builder()
                        .code(-1)
                        .success(false)
                        .message("Not Found")
                        .build());
    }

    @Override
    public ResponseDto<UserDto> edit(UserDto userDto) {
        if (userDto.getId() == null){
            return ResponseDto.<UserDto>builder()
                    .code(-2)
                    .success(false)
                    .message("Validation Error")
                    .data(userDto)
                    .build();
        }
        Optional<Users> optionalUsers = userRepository.findById(userDto.getId());
        if (optionalUsers.isEmpty()){
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .success(false)
                    .message("Not Found")
                    .build();
        }
        Users users = optionalUsers.get();
        if (userDto.getPassword() != null){users.setPassword(userDto.getPassword());}
        if (userDto.getEmail() != null){users.setEmail(userDto.getEmail());}
        if (userDto.getUsername() != null){users.setUsername(userDto.getUsername());}
        try {
            userRepository.save(users);
            return ResponseDto.<UserDto>builder()
                    .code(0)
                    .success(true)
                    .message("OK")
                    .data(userMapper.toDto(users))
                    .build();
        }catch (Exception e){
            return ResponseDto.<UserDto>builder()
                    .code(1)
                    .success(false)
                    .message("Database Error")
                    .data(userDto)
                    .build();
        }
    }

    @Override
    public ResponseDto<List<UserDto>> getAllUser() {
        return ResponseDto.<List<UserDto>>builder()
                .code(0)
                .success(true)
                .message("OK")
                .data(userRepository.findAll().stream().map(userMapper::toDto).toList())
                .build();
    }
}
