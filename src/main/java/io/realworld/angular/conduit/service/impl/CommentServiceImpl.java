package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.CommentDTO;
import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.exception.NotFoundException;
import io.realworld.angular.conduit.mapper.ArticleMapper;
import io.realworld.angular.conduit.model.Comment;
import io.realworld.angular.conduit.repository.CommentRepository;
import io.realworld.angular.conduit.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ArticleMapper commentMapper;

    @Override
    public ResponseEntity<CommonResponse<List<CommentDTO>>> getCommentsBySlug(String slug) {
        Long id = CommonService.getIdBySlug(slug);
        List<Comment> comments = commentRepository.findByArticle_Id(id).orElseThrow(() -> new NotFoundException("Comment not found"));
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.add("comments",comments.stream().map(comment -> commentMapper.toDto(comment)));
        return null;
    }

    @Override
    public ResponseEntity<CommentDTO> addCommentBySlug(String slug, CommentDTO comment) {
        return null;
    }

    @Override
    public void deleteComment(String slug, Long commentId) {

    }
}
