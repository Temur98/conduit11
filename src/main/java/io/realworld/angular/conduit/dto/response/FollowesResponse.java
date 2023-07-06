package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.FollowsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowesResponse {
    private FollowsDTO follows;
}
