package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    @Override
    public ResponseEntity<ProfileDTO> getProfileByUsername(String username) {
        return null;
    }

    @Override
    public ResponseEntity<ProfileDTO> followToProfile(String username) {
        return null;
    }

    @Override
    public ResponseEntity<ProfileDTO> unfollowFromProfile(String username) {
        return null;
    }
}
