package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.LikeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponse {
    private LikeDTO like;
}
