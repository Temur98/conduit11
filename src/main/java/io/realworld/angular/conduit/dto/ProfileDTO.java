package io.realworld.angular.conduit.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("profile")
public record ProfileDTO (
        String username,
        String bio,
        String image,
        Boolean followin){
}
