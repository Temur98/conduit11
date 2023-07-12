package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ProfileDTO;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface ProfileService {
    ResponseEntity<ProfileDTO> getProfileByUsername(String username);

    ResponseEntity<ProfileDTO> followToProfile(String username, Principal principal);

    ResponseEntity<ProfileDTO> unfollowFromProfile(String username, Principal principal);
}
