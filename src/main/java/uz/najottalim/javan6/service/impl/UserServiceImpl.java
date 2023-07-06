package uz.najottalim.javan6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.customexseptions.NoResourceFoundException;
import uz.najottalim.javan6.dto.UserDto;
import uz.najottalim.javan6.entity.Profile;
import uz.najottalim.javan6.entity.User;
import uz.najottalim.javan6.repository.ProfileRepository;
import uz.najottalim.javan6.repository.UserRepository;
import uz.najottalim.javan6.service.UserService;
import uz.najottalim.javan6.service.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<UserDto> loginUser(UserDto userDto) {
        User user = userRepository
                .findByEmail(userDto.getEmail())
                        .orElseThrow(() -> new NoResourceFoundException("Not found"));
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @Override
    public ResponseEntity<UserDto> registerUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User save = userRepository.save(userMapper.toEntity(userDto));

        Profile profile = new Profile(
                null,save.getEmail(), save.getUsername(),save.getPassword(),"","",false,save
        );
        profileRepository.save(profile);
        return ResponseEntity.ok(userMapper.toDto(save));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NoResourceFoundException("Not Found"));
        return org.springframework.security.core.userdetails
                .User.builder()
                .username(email)
                .password(user.getPassword())
                .authorities("user")
                .build();
    }
}
