package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.CommentDto;
import io.realworld.angular.conduit.dto.ResponseDto;

import java.util.List;

public interface CommentService {
    ResponseDto<CommentDto> addNewComment(CommentDto commentDto);

    ResponseDto<CommentDto> getById(Long id);

    ResponseDto<CommentDto> deleteById(Long id);

    ResponseDto<CommentDto> edit(CommentDto commentDto);

    ResponseDto<List<CommentDto>> getAllComment();
}
