package io.realworld.angular.conduit.mapper;

import io.realworld.angular.conduit.dto.TagDTO;
import io.realworld.angular.conduit.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagMapper {
    public static Tag toEntity(TagDTO tagDTO){
        if (tagDTO == null) return null;
        return new Tag(
                tagDTO.id(),
                tagDTO.name()
        );
    }
    public static TagDTO toDTO(Tag tag){
        if (tag == null) return null;
        return new  TagDTO(
                tag.getId(),
                tag.getName()
        );
    }
}
