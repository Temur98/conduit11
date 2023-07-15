package io.realworld.angular.conduit.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.realworld.angular.conduit.groups.OnCreate;
import io.realworld.angular.conduit.groups.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.hibernate.validator.constraints.Length;

@JsonRootName("user")
public record UserDTO(
        Long id,
        String username,
        String email,
        @Length(min = 4, max = 16, message = "min length : 4, max length : 16")
        String password,
        String bio,
        String image,
        String token,
        Boolean following

){
}
