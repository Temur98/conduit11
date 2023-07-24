package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final ProfileService profileService;
    @GetMapping("/{username}")
    public ResponseEntity<CommentResponse> getProfileByUsername(@PathVariable String username) {
        return profileService.getProfileByUsername(username);
    }

    @PostMapping("/{username}/follow")
    public ResponseEntity<ProfileDTO> followToProfile(@PathVariable String username, Principal principal) {
        return profileService.followToProfile(username, principal);
    }

    @DeleteMapping("/{username}/follow")
    public ResponseEntity<ProfileDTO> unfollowFromProfile(@PathVariable String username, Principal principal) {
        return profileService.unfollowFromProfile(username, principal);
    }


}
