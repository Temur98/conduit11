package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.CommentMapper;
import io.realworld.angular.conduit.model.Comment;
import io.realworld.angular.conduit.repository.CommentRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public ResponseEntity<CommonResponse<List<CommentDTO>>> getCommentsBySlug(String slug) {
        Long id = CommonService.getIdBySlug(slug);
        List<Comment> comments = commentRepository.findByArticle_Id(id).orElseThrow(() -> new NotFoundException("Comment not found"));
        CommonResponse<java.util.stream.Stream<Object>> commonResponse = new CommonResponse<>();
        commonResponse.add("comments",comments.stream().map(commentMapper::toDto));
        return null;
    }

    @Override
    public ResponseEntity<CommentDTO> addCommentBySlug(String slug, CommentDTO commentDTO, Principal principal) {
        Comment entity = commentMapper.toEntity(commentDTO);
        entity.setUser(userRepository.findByUsername(principal.getName()).orElseThrow(() -> new NotFoundException("User not found")));
        Comment save = commentRepository.save(entity);
        return ResponseEntity.ok(commentMapper.toDto(save));
    }

    @Override
    public void deleteComment(String slug, Long commentId) {
        commentRepository.delete(commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException("Comment not found")));
    }
}
