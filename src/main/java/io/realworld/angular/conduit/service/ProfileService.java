package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ProfileDto;
import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.dto.response.ProfileResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProfileService {
    ResponseDto<ProfileDto> addNewProfile(ProfileDto profileDto);

    ResponseDto<ProfileDto> getById(Long id);

    ResponseDto<ProfileDto> deleteById(Long id);

    ResponseDto<ProfileDto> edit(ProfileDto profileDto);

    ResponseDto<List<ProfileDto>> getAllProfile();

    ResponseEntity<ProfileResponse> getProfileByUsername(String username);

    ResponseEntity<ProfileResponse> addFollower(String username);

    ResponseEntity<ProfileResponse> deleteFollower(String username);
}
