package uz.najottalim.javan6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.customexseptions.NoResourceFoundException;
import uz.najottalim.javan6.dto.profiledto.ProfileResponse;
import uz.najottalim.javan6.entity.Profile;
import uz.najottalim.javan6.repository.ProfileRepository;
import uz.najottalim.javan6.service.ProfileService;
import uz.najottalim.javan6.service.mapper.ProfileMapper;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    @Override
    public ResponseEntity<ProfileResponse> getProfileByUsername(String username) {
        Profile profile = profileRepository.findByUserUsername(username)
                .orElseThrow(()->new NoResourceFoundException("No profile found"));
        return ResponseEntity.ok(new ProfileResponse(profileMapper.toDto(profile)));
    }
}
