package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.customexseption.EmailAlreadyRegisteredException;
import io.realworld.angular.conduit.customexseption.NoResourceFoundException;
import io.realworld.angular.conduit.customexseption.UsernameAlreadyRegisteredException;
import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.exceptionshandler.exception.NotFoundException;
import io.realworld.angular.conduit.exceptionshandler.exception.NotRegisteredException;
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
    public ResponseEntity<CommonResponse<UserDTO>> registerUser(CommonResponse<UserDTO> userDTOCommonResponse) {
        UserDTO userDTO = userDTOCommonResponse.getProperties().get("user");
        User user = userMapper.toEntity(userDTO);
        System.out.println(user);
        userRepository.findByUsername(user.getUsername())
                .ifPresent(value -> {throw new UsernameAlreadyRegisteredException("has already been taken");
                });

        userRepository.findByEmail(user.getEmail())
                .ifPresent(value -> {throw new EmailAlreadyRegisteredException("has already been taken");
                });

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setImage("https://www.google.com/search?q=imige&oq=imige&aqs=chrome..69i57j46i512l2j0i512l2j46i175i199i512j0i512l3.16049j0j7&sourceid=chrome&ie=UTF-8#vhid=9eM66gXcGVzSiM&vssid=l");

        userRepository.save(user);
        Authentication auth = new UsernamePasswordAuthenticationToken
                (user.getEmail(), null,List.of(new SimpleGrantedAuthority("user")));
        SecurityContextHolder.getContext().setAuthentication(auth);

        CommonResponse<UserDTO> commonResponse = new CommonResponse<>();
        commonResponse.add("user", userMapper.toDto(user));

        return ResponseEntity.ok(commonResponse);
    }

    @Override
    public ResponseEntity<CommonResponse<UserDTO>> updateUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User profile = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new NoResourceFoundException("user not found"));
        userRepository.findByUsername(user.getUsername())
                .ifPresent(value -> {throw new EmailAlreadyRegisteredException("has already ben taken");
                });
        userRepository.findByEmail(user.getEmail())
                .ifPresent(value -> {throw new EmailAlreadyRegisteredException("has already ben taken");
                });

        profile.setBio(user.getBio());
        profile.setImage(user.getImage());
        profile.setUsername(user.getUsername());
        profile.setPassword(user.getPassword());
        profile.setEmail(user.getEmail());
        User save = userRepository.save(profile);

        CommonResponse<UserDTO> commonResponse = new CommonResponse<>();
        commonResponse.add("user", userMapper.toDto(save));
        return ResponseEntity.ok(commonResponse);
    }

    @Override
    public ResponseEntity<CommonResponse<UserDTO>> loginUser(CommonResponse<UserDTO> userDTO) {
        UserDTO userDTO1 =userDTO.getProperties().get("user");
        User user1 = userMapper.toEntity(userDTO1);
        User user = userRepository
                .findByEmail(user1.getEmail())
                .orElseThrow(() -> new NoResourceFoundException("Not found"));
        if( ! passwordEncoder.matches(user1.getPassword(),user.getPassword())){
            throw  new NoResourceFoundException("not authenticated");
        }
        Authentication auth = new UsernamePasswordAuthenticationToken
                (user.getEmail(), null,List.of(new SimpleGrantedAuthority("user")));
        SecurityContextHolder.getContext().setAuthentication(auth);
            CommonResponse<UserDTO> commonResponse = new CommonResponse<>();
            commonResponse.add("user",userMapper.toDto(user));
            return ResponseEntity.ok(commonResponse);

    }

    @Override
    public ResponseEntity<CommonResponse<UserDTO>> getCurrentUser() {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NoResourceFoundException("user not found"));
        CommonResponse<UserDTO> commonResponse = new CommonResponse<>();
        commonResponse.add("user", userMapper.toDto(user));
        return ResponseEntity.ok(commonResponse);
    }
}
