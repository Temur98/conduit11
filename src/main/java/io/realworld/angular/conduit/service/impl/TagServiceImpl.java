package io.realworld.angular.conduit.service.impl;

import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.dto.TagDto;
import io.realworld.angular.conduit.dto.responseList.TagListDto;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.TagRepository;
import io.realworld.angular.conduit.service.TagService;
import io.realworld.angular.conduit.service.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    @Override
    public ResponseDto<TagDto> addNewTag(TagDto tagDto) {
        try {
            return ResponseDto.<TagDto>builder()
                    .code(0)
                    .success(true)
                    .message("OK")
                    .data(tagMapper.toDto(tagRepository.save(tagMapper.toEntity(tagDto))))
                    .build();
        }catch (Exception e){
            return ResponseDto.<TagDto>builder()
                    .code(1)
                    .success(false)
                    .message("Database Error")
                    .data(tagDto)
                    .build();
        }

    }

    @Override
    public ResponseDto<TagDto> getById(Long id) {
        return tagRepository.findById(id)
                .map(tag -> ResponseDto.<TagDto>builder()
                        .code(0)
                        .success(true)
                        .message("OK")
                        .data(tagMapper.toDto(tag))
                        .build())
                .orElse(ResponseDto.<TagDto>builder()
                        .code(-1)
                        .success(false)
                        .message("Not Found")
                        .build());
    }

    @Override
    public ResponseDto<TagDto> deleteById(Long id) {
        return tagRepository.findById(id)
                .map(tag -> {
                    tagRepository.deleteById(id);
                    return ResponseDto.<TagDto>builder()
                            .code(0)
                            .success(true)
                            .message("OK")
                            .data(tagMapper.toDto(tag))
                            .build();
                })
                .orElse(ResponseDto.<TagDto>builder()
                        .code(-1)
                        .success(false)
                        .message("Not Found")
                        .build());
    }

    @Override
    public ResponseDto<TagDto> edit(TagDto tagDto) {
        if (tagDto.getId() == null){
            return ResponseDto.<TagDto>builder()
                    .code(-2)
                    .success(false)
                    .message("Validation Error")
                    .data(tagDto)
                    .build();
        }
        Optional<Tag> optionalTag = tagRepository.findById(tagDto.getId());
        if (optionalTag.isEmpty()){
            return ResponseDto.<TagDto>builder()
                    .code(-1)
                    .success(false)
                    .message("Not Found")
                    .build();
        }
        Tag tag = optionalTag.get();
        if (tagDto.getName() != null){tag.setName(tag.getName());}
        try {
            return ResponseDto.<TagDto>builder()
                    .code(0)
                    .success(true)
                    .message("OK")
                    .data(tagMapper.toDto(tag))
                    .build();
        }catch (Exception e){
            return ResponseDto.<TagDto>builder()
                    .code(1)
                    .success(false)
                    .message("Database Error")
                    .data(tagDto)
                    .build();
        }
    }

    @Override
    public ResponseDto<List<TagDto>> getAllTag() {
        return ResponseDto.<List<TagDto>>builder()
                .code(0)
                .success(true)
                .message("OK")
                .data(tagRepository.findAll().stream().map(tagMapper::toDto).toList())
                .build();
    }
    @Override
    public ResponseEntity<TagListDto> getPopularTags() {
        return ResponseEntity.ok(new TagListDto(tagRepository.getPopularTags()));
    }
}
