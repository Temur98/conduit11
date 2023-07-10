package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.dto.response.TagResponse;
import io.realworld.angular.conduit.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Override
    public ResponseEntity<TagResponse> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<TagResponse>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<TagResponse> addTag(TagDTO tagDTO) {
        return null;
    }

    @Override
    public ResponseEntity<TagResponse> updateTag(TagDTO tagDTO) {
        return null;
    }

    @Override
    public ResponseEntity<TagResponse> deleteTag(Long id) {
        return null;
    }
}
