package io.realworld.angular.conduit.dto;


import io.realworld.angular.conduit.model.User;

import java.time.LocalDate;

public record FollowDTO(
        Long id,
        User follower,
        User following,
        LocalDate createAt
) {
}
