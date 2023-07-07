package io.realworld.angular.conduit.dto;

public record ProfileDTO (
        String userName,
        String bio,
        String image,
        Boolean following
){
}
