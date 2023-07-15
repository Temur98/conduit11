package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.dto.response.CommonResponse;
import io.realworld.angular.conduit.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/profiles")
@Slf4j
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;


    @GetMapping("/{username}")
    public ResponseEntity<ProfileDTO> getProfileByUsername(@PathVariable String username) {
        return profileService.getProfileByUsername(username);
    }

    @PostMapping("/{username}/follow")
    public ResponseEntity<ProfileDTO> followToProfile(@PathVariable String username, Principal principal) {
        return profileService.followToProfile(username,principal);
    }

    @DeleteMapping("/{username}/follow")
    public ResponseEntity<ProfileDTO> unfollowFromProfile(@PathVariable String username, Principal principal) {
        return profileService.unfollowFromProfile(username,principal);
    }


}
