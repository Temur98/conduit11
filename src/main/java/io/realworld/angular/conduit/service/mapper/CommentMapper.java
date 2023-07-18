package io.realworld.angular.conduit.service.mapper;

import io.realworld.angular.conduit.dto.CommentDto;
import io.realworld.angular.conduit.model.Comment;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper{
    private final ProfileMapper profileMapper;
    private final ArticleMapper articleMapper;
    public CommentDto toDto(Comment comment){
        return new CommentDto(
                comment.getId(),
                profileMapper.toDto(comment.getProfile()),
                articleMapper.toDto(comment.getArticle()),
                comment.getBody(),
                comment.getCreateAt(),
                comment.getUpdateAt()
        );
    }
    public Comment toEntity(CommentDto commentDto){
        return new Comment(
                commentDto.getId(),
                commentDto.getBody(),
                commentDto.getCreateAt(),
                commentDto.getUpdateAt(),
                null,
                null
        );
    }
}
