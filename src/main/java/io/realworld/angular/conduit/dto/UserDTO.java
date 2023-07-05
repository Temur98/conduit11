package io.realworld.angular.conduit.dto;

public record UserDTO(
        Long id,
        String userName,
        String email,
        String password,
        String bio,
        String image,
        Boolean following

){
}
