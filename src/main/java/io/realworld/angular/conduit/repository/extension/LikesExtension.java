package io.realworld.angular.conduit.repository.extension;


public interface LikesExtension {
    void addLike(Long idBySlug, Long userId);
    void removeLike(Long articleId, Long userId);
    Integer likesCount(Long articleId);
    boolean isLiked(Long articleId,Long userId);
}
