package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.response.CommonResponse;
import io.realworld.angular.conduit.exceptionshandler.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.CommentMapper;
import io.realworld.angular.conduit.model.Comment;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.CommentRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final ArticleRepository articleRepository;


    @Override
    public ResponseEntity<CommonResponse<List<CommentDTO>>> getCommentsBySlug(String slug) {
        Long id = CommonService.getIdBySlug(slug);
        List<Comment> comments = commentRepository.findByArticle_Id(id).orElseThrow(() -> new NotFoundException("Comment not found"));
        CommonResponse<List<CommentDTO>> commonResponse = new CommonResponse<>();
        commonResponse.add("comments", comments.stream().map(commentMapper::toDto).toList());
        return ResponseEntity.ok(commonResponse);
    }

    @Override
    public ResponseEntity<CommonResponse<CommentDTO>> addCommentBySlug(String slug, CommonResponse<CommentDTO> commonResponse) {
        CommentDTO commentDTO = commonResponse.getProperties().get("comment");
        Comment entity = commentMapper.toEntity(commentDTO);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        entity.setUser(userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found")));
        entity.setArticle(articleRepository.findById(CommonService.getIdBySlug(slug)).orElseThrow(() -> new NotFoundException("Article not found")));
        Comment save = commentRepository.save(entity);
        commonResponse.clearProperties();
        commonResponse.add("comment",commentMapper.toDto(save));
        return ResponseEntity.ok(commonResponse);
    }

    @Override
    public void deleteComment(String slug, Long commentId) {
        commentRepository.delete(commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Comment not found")));
    }
}
