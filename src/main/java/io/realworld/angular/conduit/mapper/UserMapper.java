package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.config.JWTSecurityGeneratorFilter;
import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.exception.NotRegisteredException;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.utility.JWTUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final JWTUtility jwtUtility;

    public User toEntity(UserDTO userDTO){
        if(userDTO == null) return null;
        return new User(
                userDTO.id(),
                userDTO.userName(),
                userDTO.bio(),
                userDTO.password(),
                userDTO.email(),
                userDTO.image()
        );

    }
    public UserDTO toDto(User user){
        if(user == null) return null;
        String token = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            token = jwtUtility.generate(authentication.getName(),
                    authentication.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(", "))
            );
            return new UserDTO(
                    user.getId(),
                    user.getUsername(),
                    user.getBio(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getImage(),
                    token,
                    false
            );
        }
        throw new NotRegisteredException("User not registered");

    }
}
