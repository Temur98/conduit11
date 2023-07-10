package io.realworld.angular.conduit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record Profile (
    String username,
    String bio,
    String image,
    Boolean following){

}
