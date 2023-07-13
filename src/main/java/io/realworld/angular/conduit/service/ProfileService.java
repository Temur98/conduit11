package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.dto.ProfileDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface ProfileService {
    ResponseEntity<CommonResponse<ProfileDTO>> getProfileByUsername(String username);

    ResponseEntity<CommonResponse<ProfileDTO>> followToProfile(String username, Principal principal);

    ResponseEntity<CommonResponse<ProfileDTO>> unfollowFromProfile(String username, Principal principal);
}
