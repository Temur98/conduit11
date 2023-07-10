package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.dto.response.TagResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagService {
    ResponseEntity<TagResponse> getById(Long id);

    ResponseEntity<List<TagResponse>> getAll();

    ResponseEntity<TagResponse> addTag(TagDTO tagDTO);

    ResponseEntity<TagResponse> updateTag(TagDTO tagDTO);

    ResponseEntity<TagResponse> deleteTag(Long id);
}
