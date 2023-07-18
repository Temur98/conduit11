package io.realworld.angular.conduit.service.mapper;

import io.realworld.angular.conduit.dto.ProfileDto;
import io.realworld.angular.conduit.model.Profile;
import io.realworld.angular.conduit.model.Users;
import io.realworld.angular.conduit.repository.ProfileRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileMapper{
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    public ProfileDto toDto(Profile entity) {
        if ( entity == null ) {
            return null;
        }
        return new ProfileDto(
                entity.getId(),
                entity.getUser().getUsername(),
                entity.getBio(),
                entity.getImagePath(),
                entity.getFollowing()
        );
    }
    public Profile toEntity(ProfileDto dto) {
        if ( dto == null ) {
            return null;
        }
        return new Profile(
                dto.getId(),
                dto.getBio(),
                dto.getImagePath(),
                dto.getFollowing(),
                userRepository.findByUsername(dto.getUsername()).orElseThrow()
        );
    }
}
