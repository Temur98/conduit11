package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.model.Comment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
public class CommentMapper {
    public CommentDTO toDto(Comment comment){
        return comment == null ? null : new CommentDTO(
                comment.getId(),
                comment.getBody(),
                comment.getCreateAt(),
                comment.getCreateAt(),
                comment.getUser()
        );
    }
    public Comment toEntity(CommentDTO commentDTO){
        if (commentDTO == null) return null;

        return new Comment(
                commentDTO.id(),
                commentDTO.body(),
                commentDTO.createdAt(),
                commentDTO.author(),
                null
        );
    }
}
