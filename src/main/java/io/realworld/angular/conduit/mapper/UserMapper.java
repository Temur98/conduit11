package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.UserDTO;
import io.realworld.angular.conduit.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public static User toEntity(UserDTO userDTO){
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
    public static UserDTO userDTO(User user){
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
}
