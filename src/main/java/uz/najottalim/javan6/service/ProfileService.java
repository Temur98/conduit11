package uz.najottalim.javan6.service;

import org.springframework.http.ResponseEntity;
import uz.najottalim.javan6.dto.profiledto.ProfileResponse;

public interface ProfileService {
    ResponseEntity<ProfileResponse> getProfileByUsername(String username);
}
