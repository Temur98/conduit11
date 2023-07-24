package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
private UserDTO userDTO;
}
