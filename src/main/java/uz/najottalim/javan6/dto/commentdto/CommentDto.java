package uz.najottalim.javan6.dto.commentdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.najottalim.javan6.dto.ProfileDto;
import uz.najottalim.javan6.dto.articledto.ArticleDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    @JsonProperty(value = "author")
    private ProfileDto profileDto;
    @JsonProperty(value = "article")
    private ArticleDto articleDto;

    private String body;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
