package io.realworld.angular.conduit.service;

import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.dto.TagDto;

import java.util.List;

public interface TagService {
    ResponseDto<TagDto> addNewTag(TagDto tagDto);

    ResponseDto<TagDto> getById(Long id);

    ResponseDto<TagDto> deleteById(Long id);

    ResponseDto<TagDto> edit(TagDto tagDto);

    ResponseDto<List<TagDto>> getAllTag();
}
