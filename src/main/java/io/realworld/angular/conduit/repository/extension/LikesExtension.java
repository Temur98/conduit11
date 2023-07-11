package io.realworld.angular.conduit.repository.extension;


public interface LikesExtension {
    void addLike(Long idBySlug, Long userId);
    void removeLike(Long idBySlug, Long userId);
    Long likes(Long idBySlug, Long userId);
}
