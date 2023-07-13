package io.realworld.angular.conduit.repository.extension;

public interface ProfileExtension {
    void addFollow(Long userId, Long followerId);
    void unfollow(Long userId, Long followerId);
}
