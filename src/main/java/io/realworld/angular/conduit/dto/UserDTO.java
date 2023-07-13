package io.realworld.angular.conduit.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("user")
public record UserDTO(
        Long id,
        String username,
        String email,
        String password,
        String bio,
        String image,
        Boolean following,
        String token

){
}
