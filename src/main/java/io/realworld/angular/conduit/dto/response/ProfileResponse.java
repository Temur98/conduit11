package io.realworld.angular.conduit.dto.response;

import io.realworld.angular.conduit.dto.ProfileDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {
    private ProfileDto profile;
}
