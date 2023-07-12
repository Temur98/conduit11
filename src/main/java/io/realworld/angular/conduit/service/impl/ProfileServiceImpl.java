package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.ProfileMapper;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ResponseEntity<ProfileDTO> getProfileByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Profile not found"));
        return ResponseEntity.ok(profileMapper.toProfile(user));
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
