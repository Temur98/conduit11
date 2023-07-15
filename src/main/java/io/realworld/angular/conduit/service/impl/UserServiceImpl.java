package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.dto.response.CommonResponse;
import io.realworld.angular.conduit.dto.response.UserResponse;
import io.realworld.angular.conduit.exception.NotRegisteredException;
import io.realworld.angular.conduit.exception.SimpleException;
import io.realworld.angular.conduit.exception.UsernameOrPasswordInvalid;
import io.realworld.angular.conduit.mapper.UserMapper;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("default")));
    }


    @Override
    public ResponseEntity<UserResponse> registerUser(UserResponse userResponse) {
        UserDTO userDTO = userResponse.getUser();
        userRepository.findByUsername(userDTO.username()).ifPresent(user -> {
            throw new SimpleException("Username already exists");
        });
        userRepository.findByEmail(userDTO.username()).ifPresent(user -> {
            throw new SimpleException("Email already exists");
        });

        User entity = userMapper.toEntity(userDTO);

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setImage("https://icons.veryicon.com/png/o/system/ali-mom-icon-library/random-user.png"); // default user image
        User save = userRepository.save(entity);

        Authentication auth = new UsernamePasswordAuthenticationToken(save.getEmail(),null,Collections.singleton(new SimpleGrantedAuthority("user")));
        SecurityContextHolder.getContext().setAuthentication(auth);
        userResponse.setUser(userMapper.toDto(save));
        return ResponseEntity.ok(userResponse);
    }

    @Override
    public ResponseEntity<CommonResponse<UserDTO>> updateUser(CommonResponse<UserDTO> userDTOCommonResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()){
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotRegisteredException("User not registered"));
            UserDTO userDTO = userDTOCommonResponse.getProperties().get("user");
            if (userDTO.image() != null){
                user.setImage(userDTO.image());
            }
            if (userDTO.password() != null){
                user.setPassword(passwordEncoder.encode(userDTO.password()));
            }
            if (userDTO.bio() != null){
                user.setBio(userDTO.bio());
            }
            if (userDTO.username() != null){
                user.setUsername(userDTO.username());
            }

            System.out.println(user);
            User save = userRepository.save(user);

            CommonResponse<UserDTO> commonResponse = new CommonResponse<>();
            commonResponse.add("user",userMapper.toDto(save));
            return ResponseEntity.ok(commonResponse);
        }

        throw new SimpleException("User not updated");
    }

    @Override
    public ResponseEntity<UserResponse> loginUser(UserResponse userResponse) {
        UserDTO userDTO = userResponse.getUser();

        User user = userRepository.findByEmail(userDTO.email()).orElseThrow(() -> new UsernameOrPasswordInvalid("Username or password invalid"));

        if (passwordEncoder.matches(userDTO.password(), user.getPassword())) {

            SecurityContext context = SecurityContextHolder.getContext();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), null, List.of(new SimpleGrantedAuthority("user")));
            context.setAuthentication(usernamePasswordAuthenticationToken);
            SecurityContextHolder.setContext(context);

            userResponse.setUser(userMapper.toDto(user));
            return ResponseEntity.ok(userResponse);
        } else {
            throw new UsernameOrPasswordInvalid("Username or password invalid");
        }
    }

    @Override
    public ResponseEntity<CommonResponse> getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(email);
        if (email != null){
            User user = userRepository.findByEmail(email).orElseThrow(() -> new NotRegisteredException("Current user is not present"));
            CommonResponse<UserDTO> commonResponse = new CommonResponse<>();
            commonResponse.add("user",userMapper.toDto(user));
            return ResponseEntity.ok(commonResponse);
        }
        throw new NotRegisteredException("Current user is not present");
    }
}
