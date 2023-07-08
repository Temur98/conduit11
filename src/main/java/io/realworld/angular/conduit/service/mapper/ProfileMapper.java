package io.realworld.angular.conduit.service.mapper;

import io.realworld.angular.conduit.dto.ProfileDto;
import io.realworld.angular.conduit.model.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper extends UniversalMapper<ProfileDto, Profile>{
}
