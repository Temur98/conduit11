package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.response.ProfileResponse;
import io.realworld.angular.conduit.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    @Override
    public ResponseEntity<ProfileResponse> getProfileByUsername(String username) {
        return null;
    }

    @Override
    public ResponseEntity<ProfileResponse> followToProfile(String username) {
        return null;
    }

    @Override
    public ResponseEntity<ProfileResponse> unfollowFromProfile(String username) {
        return null;
    }
}
