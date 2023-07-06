package io.realworld.angular.conduit.dto;

import io.realworld.angular.conduit.model.User;

public record FollowsDTO(
        Long usrerId,
        Long followId,
        User follower,
        User following
) {
}
