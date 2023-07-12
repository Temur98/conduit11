package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.utility.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final JwtUtility jwtUtility;
    public  User toEntity(UserDTO userDTO){
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
    public  UserDTO toDto(User user){
        if(user == null) return null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jwtToken = null;
        if (authentication != null && authentication.isAuthenticated()) {
            String username = ((User) authentication.getPrincipal()).getUsername();
            System.out.println(username);
            jwtToken = jwtUtility.generate(username,
                    authentication.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(", "))
            );
        }
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getBio(),
                user.getPassword(),
                user.getEmail(),
                user.getImage(),
                false,
                jwtToken
        );
    }

    public ProfileDTO toProfileDto (User user){
        Optional<User> byUsername = userRepository.findByUsername(user.getUsername());
        if(user == null) return null;
        return new ProfileDTO(
                user.getUsername(),
                user.getBio(),
                user.getImage(),
                userRepository.isFollowedToArticleOwner(user.getId(),byUsername.get().getId())
        );
    }
}
