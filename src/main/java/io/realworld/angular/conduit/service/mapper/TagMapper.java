package io.realworld.angular.conduit.service.mapper;

import io.realworld.angular.conduit.dto.TagDto;
import io.realworld.angular.conduit.model.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper extends UniversalMapper<TagDto, Tag>{
}
