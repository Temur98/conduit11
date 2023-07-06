package uz.najottalim.javan6.service.mapper;

import lombok.Setter;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dto.TagDto;
import uz.najottalim.javan6.entity.Tag;

@Service
public class TagMapper {
    public TagDto toDto(Tag tag){
        return new TagDto(tag.getId(), tag.getName());
    }

    public Tag toEntity(TagDto tag){
        return new Tag(tag.getId(), tag.getName());
    }

}
