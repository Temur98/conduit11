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
        @NotNull(groups = OnCreate.class)
        @Null(groups = OnUpdate.class)
        Long id,
        @NotNull
        String userName,
        @Email
        String email,
        @Length(min = 4, max = 16)
        String password,
        String bio,
        String image,
        String token,
        Boolean following

){
}
