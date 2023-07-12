package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
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
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getBio(),
                user.getPassword(),
                user.getEmail(),
                user.getImage(),
                false
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
