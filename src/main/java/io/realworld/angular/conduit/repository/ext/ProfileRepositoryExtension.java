package io.realworld.angular.conduit.repository.ext;

import jakarta.transaction.Transactional;

public interface ProfileRepositoryExtension {

    @Transactional
    void addFollow(Long userId, Long followerId);

    @Transactional
    void deleteFollow(Long userId, Long followerId);

    @Transactional
    boolean isFollowed(Long userId, Long followerId);

    @Transactional
    void updateFollow(Boolean follow, Long id);
}
