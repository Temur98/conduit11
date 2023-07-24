package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.response.CommentResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface TagService {
    ResponseEntity<CommentResponse<List<String>>> getAll();
}
