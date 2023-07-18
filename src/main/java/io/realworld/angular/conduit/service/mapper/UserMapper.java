package io.realworld.angular.conduit.service.mapper;

import io.realworld.angular.conduit.dto.UserDto;
import io.realworld.angular.conduit.model.Profile;
import io.realworld.angular.conduit.model.Users;
import io.realworld.angular.conduit.repository.ProfileRepository;
import io.realworld.angular.conduit.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final ProfileRepository profileRepository;
    private final JwtService jwtService;
    public UserDto toDto(Users user){
        Profile profile = profileRepository.findByUserUsername(user.getUsername())
                .orElseThrow();
        UserDto userDto = new UserDto(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                profile.getBio(),
                profile.getImagePath(),
                ""
        );
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            userDto.setToken(jwtService.generateToken(authentication.getName(), "user"));
        }
        return userDto;
    }
    public Users toEntity(UserDto profileDto){
        return new Users(
                profileDto.getId(),
                profileDto.getEmail(),
                profileDto.getUsername(),
                profileDto.getPassword()
        );
    }
}