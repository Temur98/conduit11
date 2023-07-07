package uz.najottalim.javan6.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dto.UserDto;
import uz.najottalim.javan6.entity.User;

@Service
@RequiredArgsConstructor
public class UserMapper {
    public UserDto toDto(User profile){
        return new UserDto(
                profile.getId(),
                profile.getEmail(),
                profile.getUsername(),
                profile.getPassword()
        );
    }
    public User toEntity(UserDto profileDto){
        return new User(
                profileDto.getId(),
                profileDto.getEmail(),
                profileDto.getUsername(),
                profileDto.getPassword()
        );
    }
}
