package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.ProfileDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileResponse {
    private ProfileDTO profile;
}
