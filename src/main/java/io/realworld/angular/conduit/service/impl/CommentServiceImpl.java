package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.CommentMapper;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Comment;
import io.realworld.angular.conduit.repository.CommentRepository;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<CommentDTO> getCommentsBySlug(String slug) {
        Long id = CommonService.getIdBySlug(slug);
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment not found"));
        CommentDTO dto = commentMapper.toDto(comment);
        return ResponseEntity.ok(dto);

    }

    @Override
    public ResponseEntity<CommentDTO> addCommentBySlug(String slug, CommentDTO comment) {
        Long id = CommonService.getIdBySlug(slug);
        Comment save = commentRepository.save(commentMapper.toEntity(comment));
        return ResponseEntity.ok(commentMapper.toDto(save));
    }

    @Override
    public void deleteComment(String slug, Long commentId) {
        Long id = CommonService.getIdBySlug(slug);
        commentRepository.deleteById(commentId);


    }
}
