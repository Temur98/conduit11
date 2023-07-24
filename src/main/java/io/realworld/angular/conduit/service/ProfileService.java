package io.realworld.angular.conduit.service;


import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface ProfileService {
    ResponseEntity<CommentResponse> getProfileByUsername(String username);

    ResponseEntity<ProfileDTO> followToProfile(String username, Principal principal);

    ResponseEntity<ProfileDTO> unfollowFromProfile(String username, Principal principal);
}
