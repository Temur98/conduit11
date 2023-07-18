package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ProfileDto;
import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.dto.response.ProfileResponse;
import io.realworld.angular.conduit.model.Profile;
import io.realworld.angular.conduit.repository.ProfileRepository;
import io.realworld.angular.conduit.service.ProfileService;
import io.realworld.angular.conduit.service.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ResponseEntity<ProfileResponse> getProfileByUsername(String username) {
        return ResponseEntity.ok(new ProfileResponse(
                profileMapper.toDto(profileRepository.findByUserUsername(username).orElseThrow())
                ));
    }

    @Override
    public ResponseEntity<ProfileResponse> addFollower(String username) {
        Profile user = profileRepository.findByUserUsername(username).orElseThrow();
        Profile profile = profileRepository.findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow();
        profileRepository.addFollow(user.getId(), profile.getId());
        user.setFollowing(true);
        profileRepository.updateFollow(true, user.getId());
        ProfileDto profileDto = profileMapper.toDto(user);
        profileDto.setFollowing(profileRepository.isFollowed(user.getId(), profile.getId()));
        return ResponseEntity.ok(new ProfileResponse(profileDto));
    }

    @Override
    public ResponseEntity<ProfileResponse> deleteFollower(String username) {
        Profile user = profileRepository.findByUserUsername(username).orElseThrow();
        Profile profile = profileRepository.findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow();
        user.setFollowing(false);
        profileRepository.updateFollow(false, user.getId());
        profileRepository.deleteFollow(user.getId(), profile.getId());
        return ResponseEntity.ok(new ProfileResponse(profileMapper.toDto(user)));
    }
//    @Override
//    public ResponseDto<ProfileDto> addNewProfile(ProfileDto profileDto) {
//        try {
//            return ResponseDto.<ProfileDto>builder()
//                    .code(0)
//                    .success(true)
//                    .message("OK")
//                    .data(profileMapper.toDto(profileRepository.save(profileMapper.toEntity(profileDto))))
//                    .build();
//        }catch (Exception e){
//            return ResponseDto.<ProfileDto>builder()
//                    .code(1)
//                    .success(false)
//                    .message("Database Error")
//                    .data(profileDto)
//                    .build();
//        }
//    }
//
//    @Override
//    public ResponseDto<ProfileDto> getById(Long id) {
//        return profileRepository.findById(id)
//                .map(profile -> ResponseDto.<ProfileDto>builder()
//                        .code(0)
//                        .success(true)
//                        .message("OK")
//                        .data(profileMapper.toDto(profile))
//                        .build())
//                .orElse(ResponseDto.<ProfileDto>builder()
//                        .code(-1)
//                        .success(false)
//                        .message("Not Found")
//                        .build());
//    }
//
//    @Override
//    public ResponseDto<ProfileDto> deleteById(Long id) {
//        return profileRepository.findById(id)
//                .map(profile -> {
//                    profileRepository.deleteById(id);
//                    return ResponseDto.<ProfileDto>builder()
//                            .code(0)
//                            .success(true)
//                            .message("OK")
//                            .data(profileMapper.toDto(profile))
//                            .build();
//                })
//                .orElse(ResponseDto.<ProfileDto>builder()
//                        .code(-1)
//                        .success(false)
//                        .message("Not Found")
//                        .build());
//    }
//
//    @Override
//    public ResponseDto<ProfileDto> edit(ProfileDto profileDto) {
//        if (profileDto.getId() == null){
//            return ResponseDto.<ProfileDto>builder()
//                    .code(-2)
//                    .success(false)
//                    .message("Validation Error")
//                    .data(profileDto)
//                    .build();
//        }
//        Optional<Profile> optionalProfile = profileRepository.findById(profileDto.getId());
//        if (optionalProfile.isEmpty()){
//            return ResponseDto.<ProfileDto>builder()
//                    .code(-1)
//                    .success(false)
//                    .message("Not Found")
//                    .build();
//        }
//        Profile profile = optionalProfile.get();
//        if (profileDto.getBio() != null){profile.setBio(profileDto.getBio());}
//        if (profileDto.getImagePath() != null){profile.setImagePath(profileDto.getImagePath());}
//        if (profileDto.getUsername() != null){profile.getUser().setUsername(profileDto.getUsername());}
//        try {
//            return ResponseDto.<ProfileDto>builder()
//                    .code(0)
//                    .success(true)
//                    .message("OK")
//                    .data(profileMapper.toDto(profile))
//                    .build();
//        }catch (Exception e){
//            return ResponseDto.<ProfileDto>builder()
//                    .code(1)
//                    .success(false)
//                    .message("Database Error")
//                    .data(profileDto)
//                    .build();
//        }
//    }
//
//    @Override
//    public ResponseDto<List<ProfileDto>> getAllProfile() {
//        return ResponseDto.<List<ProfileDto>>builder()
//                .code(0)
//                .success(true)
//                .message("OK")
//                .data(profileRepository.findAll().stream().map(profileMapper::toDto).toList())
//                .build();
//    }
}
