package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.response.CommonResponse;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.TagRepository;
import io.realworld.angular.conduit.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public ResponseEntity<CommonResponse<List<String>>> getAll() {
        List<String> list = tagRepository.findAll().stream().map(Tag::getName).toList();
        CommonResponse<List<String>> commonResponse = new CommonResponse<>();
        commonResponse.add("tags",list);

        return ResponseEntity.ok(commonResponse);

    }
}
