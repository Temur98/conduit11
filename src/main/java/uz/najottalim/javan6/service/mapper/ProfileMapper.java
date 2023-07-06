package uz.najottalim.javan6.service.mapper;

import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dto.ProfileDto;
import uz.najottalim.javan6.entity.Profile;

@Service
public class ProfileMapper {
    public ProfileDto toDto(Profile profile){
        return new ProfileDto(
                profile.getId(),
                profile.getEmail(),
                profile.getUsername(),
                profile.getPassword(),
                profile.getBio(),
                profile.getImagePath(),
                profile.getFollowing()
        );
    }
}
