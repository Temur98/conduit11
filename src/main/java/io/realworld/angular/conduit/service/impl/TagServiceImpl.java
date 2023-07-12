package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ArticleDTO;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;


    @Override
    public ResponseEntity<CommonResponse<List<TagDTO>>> getAll() {
        List<Tag> tags = tagRepository.findAll();
        CommonResponse<List<TagDTO>> commonResponse = new CommonResponse<List<TagDTO>>();
        commonResponse.add("tags",tags.stream().map(TagMapper::toDto).collect(Collectors.toList()));
        return ResponseEntity.ok(commonResponse);
    }
}
