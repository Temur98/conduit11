package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ProfileDto;
import io.realworld.angular.conduit.dto.ResponseDto;

import java.util.List;

public interface ProfileService {
    ResponseDto<ProfileDto> addNewProfile(ProfileDto profileDto);

    ResponseDto<ProfileDto> getById(Long id);

    ResponseDto<ProfileDto> deleteById(Long id);

    ResponseDto<ProfileDto> edit(ProfileDto profileDto);

    ResponseDto<List<ProfileDto>> getAllProfile();
}
