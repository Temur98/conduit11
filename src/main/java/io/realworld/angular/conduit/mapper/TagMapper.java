package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagMapper {
    private final TagRepository tagRepository;


    public Tag toEntity(TagDTO tagDTO){
        if(tagDTO == null) return null;
        return new Tag(
                tagDTO.id(),
                tagDTO.name()
        );
    }

    private List<Tag> saveTags(List<String> tags){
        List<Tag> tagsByNameIn = tagRepository.findTagsByNameIn(tags);
        List<String> tagNameList = tagsByNameIn.stream().map(Tag::getName).toList();

        List<Tag> list = tags.stream()
                .filter(tag -> !tagNameList.contains(tag))
                .map(tag -> new Tag(null,tag))
                .toList();

        tagRepository.saveAll(list);

        return null;
    }


    public TagDTO toDto(Tag tag){
        if(tag == null) return null;
        return new TagDTO(
                tag.getId(),
                tag.getName()
        );
    }

    public List<Tag> toEntities(List<String> tagList) {
        return saveTags(tagList);
    }
}
