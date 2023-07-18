package io.realworld.angular.conduit.service.impl;


import io.realworld.angular.conduit.dto.CommentDto;
import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.model.Comment;
import io.realworld.angular.conduit.repository.CommentRepository;
import io.realworld.angular.conduit.service.CommentService;
import io.realworld.angular.conduit.service.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
//    @Override
//    public ResponseDto<CommentDto> addNewComment(CommentDto commentDto) {
//        try {
//            return ResponseDto.<CommentDto>builder()
//                    .code(0)
//                    .success(true)
//                    .message("OK")
//                    .data(commentMapper.toDto(commentRepository.save(commentMapper.toEntity(commentDto))))
//                    .build();
//        }catch (Exception e){
//            return ResponseDto.<CommentDto>builder()
//                    .code(1)
//                    .success(false)
//                    .message("Database Error")
//                    .data(commentDto)
//                    .build();
//        }
//    }
//
//    @Override
//    public ResponseDto<CommentDto> getById(Long id) {
//        return commentRepository.findById(id)
//                .map(comment -> ResponseDto.<CommentDto>builder()
//                        .code(0)
//                        .success(true)
//                        .message("OK")
//                        .data(commentMapper.toDto(comment))
//                        .build())
//                .orElse(ResponseDto.<CommentDto>builder()
//                        .code(-1)
//                        .success(false)
//                        .message("Not Found")
//                        .build());
//    }
//
//    @Override
//    public ResponseDto<CommentDto> deleteById(Long id) {
//        return commentRepository.findById(id)
//                .map(comment -> {
//                    commentRepository.deleteById(id);
//                    return ResponseDto.<CommentDto>builder()
//                            .code(0)
//                            .success(true)
//                            .message("OK")
//                            .data(commentMapper.toDto(comment))
//                            .build();
//                })
//                .orElse(ResponseDto.<CommentDto>builder()
//                        .code(-1)
//                        .success(false)
//                        .message("Not Found")
//                        .build());
//    }
//
//    @Override
//    public ResponseDto<CommentDto> edit(CommentDto commentDto) {
//        if (commentDto.getId() == null){
//            return ResponseDto.<CommentDto>builder()
//                    .code(-2)
//                    .success(false)
//                    .message("Validation Error")
//                    .data(commentDto)
//                    .build();
//        }
//        Optional<Comment> optionalComment = commentRepository.findById(commentDto.getId());
//        if (optionalComment.isEmpty()){
//            return ResponseDto.<CommentDto>builder()
//                    .code(-1)
//                    .success(false)
//                    .message("Not Found")
//                    .data(commentDto)
//                    .build();
//        }
//        Comment comment = optionalComment.get();
//        if (commentDto.getBody() != null){comment.setBody(commentDto.getBody());}
//        if (commentDto.getUpdateAt() != null){comment.setUpdateAt(LocalDateTime.now());}
//        try {
//            commentRepository.save(comment);
//            return ResponseDto.<CommentDto>builder()
//                    .code(0)
//                    .success(true)
//                    .message("OK")
//                    .data(commentMapper.toDto(comment))
//                    .build();
//        }catch (Exception e){
//            return ResponseDto.<CommentDto>builder()
//                    .code(1)
//                    .success(false)
//                    .message("Database Error")
//                    .data(commentDto)
//                    .build();
//        }
//    }
//
//    @Override
//    public ResponseDto<List<CommentDto>> getAllComment() {
//        return ResponseDto.<List<CommentDto>>builder()
//                .code(0)
//                .success(true)
//                .message("OK")
//                .data(commentRepository.findAll().stream().map(commentMapper::toDto).toList())
//                .build();
//    }
}
