package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.CommonResponse;
import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.mapper.TagMapper;
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
    public ResponseEntity<CommonResponse<List<TagDTO>>> getAll() {
        List<TagDTO> list = tagRepository.findAll().stream().map(TagMapper::toDto).toList();
        CommonResponse<List<TagDTO>> commonResponse = new CommonResponse<>();
        commonResponse.add("tags",list);

        return ResponseEntity.ok(commonResponse);

    }
}
