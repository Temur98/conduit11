package uz.najottalim.javan6.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.najottalim.javan6.dto.profiledto.ProfileResponse;
import uz.najottalim.javan6.service.ProfileService;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponse> getProfileByUsername(@PathVariable String username){
       return profileService.getProfileByUsername(username);
    }
}
