package io.realworld.angular.conduit.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("profile")
public record ProfileDTO (
        String userName,
        String bio,
        String image,
        Boolean following
){
}
