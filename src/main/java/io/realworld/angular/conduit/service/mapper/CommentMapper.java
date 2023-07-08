package io.realworld.angular.conduit.service.mapper;

import io.realworld.angular.conduit.dto.CommentDto;
import io.realworld.angular.conduit.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper extends UniversalMapper<CommentDto, Comment>{
}
