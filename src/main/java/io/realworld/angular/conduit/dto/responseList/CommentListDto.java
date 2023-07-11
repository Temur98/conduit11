package io.realworld.angular.conduit.dto.responseList;

import io.realworld.angular.conduit.dto.CommentDto;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentListDto {
    private List<CommentDto> comments;
}
