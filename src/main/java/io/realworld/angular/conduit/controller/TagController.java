package io.realworld.angular.conduit.controller;

import io.realworld.angular.conduit.dto.ResponseDto;
import io.realworld.angular.conduit.dto.TagDto;
import io.realworld.angular.conduit.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping
    public ResponseDto<TagDto> addNewTag(@RequestBody TagDto tagDto) {
        return tagService.addNewTag(tagDto);
    }

    @GetMapping("get-by-id")
    public ResponseDto<TagDto> getById(@RequestParam Long id) {
        return tagService.getById(id);
    }

    @DeleteMapping("delete-by-id")
    public ResponseDto<TagDto> deleteById(@RequestParam Long id) {
        return tagService.deleteById(id);
    }

    @PutMapping("edit")
    public ResponseDto<TagDto> edit(@RequestBody TagDto tagDto) {
        return tagService.edit(tagDto);
    }

    @GetMapping("get-all-tag")
    public ResponseDto<List<TagDto>> getAllTag() {
        return tagService.getAllTag();
    }
}
