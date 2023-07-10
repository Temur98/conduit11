package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Override
    public ResponseEntity<CommonResponse<List<TagDTO>>> getAll() {
        return null;
    }
}
