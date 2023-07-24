package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.CommentMapper;
import io.realworld.angular.conduit.model.Comment;
import io.realworld.angular.conduit.repository.ArticleRepository;
import io.realworld.angular.conduit.repository.CommentRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.CommentService;
import io.realworld.angular.conduit.service.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    @Override
    public ResponseEntity<CommentResponse<List<CommentDTO>>> getCommentsBySlug(String slug) {
        Long id = CommonService.getIdBySlug(slug);
        List<Comment> comments = commentRepository.findByArticleId(id).orElseThrow(() -> new NotFoundException("Comment not found"));
        CommentResponse<List<CommentDTO>> commentResponse = new CommentResponse<>();
        commentResponse.add("comments", comments.stream().map(commentMapper::toDto).toList());
        return ResponseEntity.ok(commentResponse);
    }

    @Override
    public ResponseEntity<CommentResponse<CommentDTO>> addCommentBySlug(String slug, CommentResponse<CommentDTO> commentResponse) {
        CommentDTO commentDTO = commentResponse.getProperties().get("comment");
        Comment entity = commentMapper.toEntity(commentDTO);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        entity.setUser(userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found")));
        entity.setArticle(articleRepository.findById(CommonService.getIdBySlug(slug)).orElseThrow(() -> new NotFoundException("Article not found")));
        Comment save = commentRepository.save(entity);
        commentResponse.clearProperties();
        commentResponse.add("comment",commentMapper.toDto(save));
        return ResponseEntity.ok(commentResponse);    }

    @Override
    public void deleteComment(String slug, Long commentId) {

        commentRepository.delete(commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Comment not found")));
    }
}
