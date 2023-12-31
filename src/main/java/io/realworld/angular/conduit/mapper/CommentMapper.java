package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentDTO toDto(Comment comment);
    Comment toEntity(CommentDTO commentDTO);
}
