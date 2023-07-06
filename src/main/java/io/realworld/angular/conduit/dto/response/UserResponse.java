package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UserDTO user;
}
