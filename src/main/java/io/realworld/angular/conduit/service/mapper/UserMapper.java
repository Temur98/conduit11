package io.realworld.angular.conduit.service.mapper;

import io.realworld.angular.conduit.dto.UserDto;
import io.realworld.angular.conduit.model.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends UniversalMapper<UserDto, Users>{
}
