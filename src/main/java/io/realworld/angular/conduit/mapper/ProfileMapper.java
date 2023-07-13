package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.ProfileDTO;
import io.realworld.angular.conduit.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDTO toProfile(User user);
}
