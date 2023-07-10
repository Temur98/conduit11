package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.response.ProfileResponse;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    ResponseEntity<ProfileResponse> getProfileByUsername(String username);

    ResponseEntity<ProfileResponse> followToProfile(String username);

    ResponseEntity<ProfileResponse> unfollowFromProfile(String username);
}
