package uz.najottalim.javan6.service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dto.commentdto.CommentDto;
import uz.najottalim.javan6.entity.Comment;

@Service
@RequiredArgsConstructor
public class CommentMapper {
    private final ArticleMapper articleMapper;
    private final ProfileMapper profileMapper;
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
}
