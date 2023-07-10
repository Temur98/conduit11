package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.response.TagResponse;
import org.springframework.http.ResponseEntity;

public interface TagService {

    ResponseEntity<TagResponse> getAll();
}
