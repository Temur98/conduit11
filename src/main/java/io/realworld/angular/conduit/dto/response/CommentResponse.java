package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.CommentDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {
    private CommentDto comment;
}
