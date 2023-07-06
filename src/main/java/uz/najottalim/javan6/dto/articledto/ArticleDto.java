package uz.najottalim.javan6.dto.articledto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.najottalim.javan6.dto.ProfileDto;
import uz.najottalim.javan6.dto.TagDto;

import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String slug;
    private String title;
    private String description;
    private String body;
    @JsonProperty(value = "tagList")
    private List<String> tags;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    @JsonProperty(value = "favorited",defaultValue = "false")
    private Boolean favorite;
    @JsonProperty(defaultValue = "0")
    private Long favoritesCount;
    @JsonProperty(value = "author")
    private ProfileDto profileDto;
}
