package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    public static Comment toEntity(CommentDTO commentDTO){
        if(commentDTO == null) return null;
        return new Comment(
                commentDTO.id(),
                commentDTO.body(),
                commentDTO.createAt(),
                commentDTO.user(),
                commentDTO.article()
        );
    }

    public static CommentDTO toDto(Comment comment){
        if(comment == null) return null;
        return new CommentDTO(
                comment.getId(),
                comment.getBody(),
                comment.getCreateAt(),
                comment.getUser(),
                comment.getArticle()
        );
    }
}
