package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.UserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private UserDto user;
}
