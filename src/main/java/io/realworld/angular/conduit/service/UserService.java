package io.realworld.angular.conduit.service;


import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    ResponseEntity<CommonResponse<UserDTO>> registerUser(CommonResponse<UserDTO> userDTOCommonResponse);

    ResponseEntity<CommonResponse<UserDTO>> updateUser(UserDTO userDTO);

    ResponseEntity<CommonResponse<UserDTO>> loginUser(CommonResponse<UserDTO> user);

    ResponseEntity<CommonResponse<UserDTO>> getCurrentUser();
}
