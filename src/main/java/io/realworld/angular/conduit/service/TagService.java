package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.dto.TagDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagService {

    ResponseEntity<CommonResponse<List<TagDTO>>> getAll();
}
