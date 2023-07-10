package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.response.TagResponse;
import io.realworld.angular.conduit.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Override
    public ResponseEntity<TagResponse> getAll() {
        return null;
    }
}
