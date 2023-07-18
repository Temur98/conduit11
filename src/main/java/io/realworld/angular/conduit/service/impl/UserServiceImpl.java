package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.response.UserResponse;
import io.realworld.angular.conduit.model.Profile;
import io.realworld.angular.conduit.model.Users;
import io.realworld.angular.conduit.repository.ProfileRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.UserService;
import io.realworld.angular.conduit.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ProfileRepository profileRepository;

    @Override
    public ResponseEntity<UserResponse> loginUser(UserResponse userResponse) throws Exception {
        Users user = userRepository
                .findByEmail(userResponse.getUser().getEmail())
                .orElseThrow();
        if( ! passwordEncoder.matches(userResponse.getUser().getPassword(),user.getPassword())){
            throw  new Exception("not authenticated");
        };

        Authentication auth = new UsernamePasswordAuthenticationToken
                (user.getEmail(), null,List.of(new SimpleGrantedAuthority("user")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return ResponseEntity.ok(new UserResponse(userMapper.toDto(user)));
    }

    @Override
    public ResponseEntity<UserResponse> registerUser(UserResponse userResponse) {
        userRepository.findByUsername(userResponse.getUser().getUsername())
                .ifPresent(value -> {
                    try {
                        throw new Exception("has already been taken");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        userRepository.findByEmail(userResponse.getUser().getEmail())
                .ifPresent(value -> {
                    try {
                        throw new Exception("has already been taken");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        userResponse.getUser().setPassword(passwordEncoder.encode(userResponse.getUser().getPassword()));

        Users save = userRepository.save(userMapper.toEntity(userResponse.getUser()));

        Profile profile = new Profile(
                null,"", "https://st2.depositphotos.com/25790974/44392/v/600/depositphotos_443928634-stock-illustration-smile-happy-face-vector-design.jpg", false,save
        );
        profileRepository.save(profile);
        Authentication auth = new UsernamePasswordAuthenticationToken
                (save.getEmail(), null,List.of(new SimpleGrantedAuthority("user")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return ResponseEntity.ok( new UserResponse(userMapper.toDto(save)));
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(UserResponse userResponse) {
        Profile profile = profileRepository.findByUserEmail(userResponse.getUser().getEmail())
                .orElseThrow();

        userRepository.findByUsername(userResponse.getUser().getUsername())
                .ifPresent(value -> {
                    try {
                        if (!value.getUsername().equals(userResponse.getUser().getUsername()))
                            throw new Exception("has already been taken");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        userRepository.findByEmail(userResponse.getUser().getEmail())
                .ifPresent(value -> {
                    try {
                        if (!value.getEmail().equals(userResponse.getUser().getEmail()))
                            throw new Exception("has already been taken");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });


//        profile.setBio(userResponse.getUser().getBio());
//        profile.setImagePath(userResponse.getUser().getImagePath());
        Users user = profile.getUser();
        user.setUsername(userResponse.getUser().getUsername());
        user.setPassword(userResponse.getUser().getPassword());
        user.setEmail(userResponse.getUser().getEmail());
        profile.setUser(user);

        Users save1 = userRepository.save(user);
        Profile save = profileRepository.save(profile);

        return ResponseEntity.ok(new UserResponse(userMapper.toDto(save1)));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository
                .findByEmail(email)
                .orElseThrow();
        return org.springframework.security.core.userdetails.User.builder().build();
    }

    @Override
    public ResponseEntity<UserResponse> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users user = userRepository.findByEmail(authentication.getName())
                .orElseThrow();
        return ResponseEntity.ok(new UserResponse(userMapper.toDto(user)));
    }

//    @Override
//    public ResponseDto<UserDto> addNewUser(UserDto userDto) {
//        try {
//            return ResponseDto.<UserDto>builder()
//                    .code(0)
//                    .success(true)
//                    .message("OK")
//                    .data(userMapper.toDto(userRepository.save(userMapper.toEntity(userDto))))
//                    .build();
//        }catch (Exception e){
//            return ResponseDto.<UserDto>builder()
//                    .code(1)
//                    .success(false)
//                    .message("Database Error")
//                    .data(userDto)
//                    .build();
//        }
//    }
//
//    @Override
//    public ResponseDto<UserDto> getById(Long id) {
//        return userRepository.findById(id)
//                .map(users -> ResponseDto.<UserDto>builder()
//                        .code(0)
//                        .success(true)
//                        .message("OK")
//                        .data(userMapper.toDto(users))
//                        .build())
//                .orElse(ResponseDto.<UserDto>builder()
//                        .code(-1)
//                        .success(false)
//                        .message("Not Found")
//                        .build());
//    }
//
//    @Override
//    public ResponseDto<UserDto> deleteById(Long id) {
//        return userRepository.findById(id)
//                .map(users -> {
//                    userRepository.deleteById(id);
//                    return ResponseDto.<UserDto>builder()
//                            .code(0)
//                            .success(true)
//                            .message("OK")
//                            .data(userMapper.toDto(users))
//                            .build();
//                })
//                .orElse(ResponseDto.<UserDto>builder()
//                        .code(-1)
//                        .success(false)
//                        .message("Not Found")
//                        .build());
//    }
//
//    @Override
//    public ResponseDto<UserDto> edit(UserDto userDto) {
//        if (userDto.getId() == null){
//            return ResponseDto.<UserDto>builder()
//                    .code(-2)
//                    .success(false)
//                    .message("Validation Error")
//                    .data(userDto)
//                    .build();
//        }
//        Optional<Users> optionalUsers = userRepository.findById(userDto.getId());
//        if (optionalUsers.isEmpty()){
//            return ResponseDto.<UserDto>builder()
//                    .code(-1)
//                    .success(false)
//                    .message("Not Found")
//                    .build();
//        }
//        Users users = optionalUsers.get();
//        if (userDto.getPassword() != null){users.setPassword(userDto.getPassword());}
//        if (userDto.getEmail() != null){users.setEmail(userDto.getEmail());}
//        if (userDto.getUsername() != null){users.setUsername(userDto.getUsername());}
//        try {
//            userRepository.save(users);
//            return ResponseDto.<UserDto>builder()
//                    .code(0)
//                    .success(true)
//                    .message("OK")
//                    .data(userMapper.toDto(users))
//                    .build();
//        }catch (Exception e){
//            return ResponseDto.<UserDto>builder()
//                    .code(1)
//                    .success(false)
//                    .message("Database Error")
//                    .data(userDto)
//                    .build();
//        }
//    }
//
//    @Override
//    public ResponseDto<List<UserDto>> getAllUser() {
//        return ResponseDto.<List<UserDto>>builder()
//                .code(0)
//                .success(true)
//                .message("OK")
//                .data(userRepository.findAll().stream().map(userMapper::toDto).toList())
//                .build();
//    }
}
