package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.dto.response.CommonResponse;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.ProfileMapper;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.repository.extension.ProfileExtension;
import io.realworld.angular.conduit.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final ProfileExtension profileExtension;
    private final ProfileMapper profileMapper;

    @Override
    public ResponseEntity<CommonResponse> getProfileByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Profile not found"));
        CommonResponse<ProfileDTO> commonResponse = new CommonResponse<>();
        commonResponse.add("profile",profileMapper.toProfile(user));
        return ResponseEntity.ok(commonResponse);
    }

    @Override
    public ResponseEntity<ProfileDTO> followToProfile(String username, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new NotFoundException("Profile not found"));
        profileExtension.addFollow(
                    user.getId(),
                    userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Profile not found")).getId()
                );
        return ResponseEntity.ok(profileMapper.toProfile(user));
    }

    @Override
    public ResponseEntity<ProfileDTO> unfollowFromProfile(String username, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new NotFoundException("Profile not found"));
        profileExtension.unfollow(
                user.getId(),
                userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Profile not found")).getId()
        );
        return ResponseEntity.ok(profileMapper.toProfile(user));
    }
}
