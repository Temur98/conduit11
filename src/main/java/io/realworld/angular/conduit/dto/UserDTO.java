package io.realworld.angular.conduit.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import org.hibernate.validator.constraints.Length;

@JsonRootName("user")
public record UserDTO(
        Long id,
        String username,
        String email,
        @Length(min = 5, max = 25, message = "min length : 5, max length : 25")
        String password,
        String bio,
        String image,
        String token,
        Boolean following


){
}
