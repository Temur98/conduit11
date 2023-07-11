package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.dto.response.CommentResponse;
import io.realworld.angular.conduit.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    @Override
    public ResponseEntity<CommentResponse<List<TagDTO>>> getAll() {
        return null;
    }
}
