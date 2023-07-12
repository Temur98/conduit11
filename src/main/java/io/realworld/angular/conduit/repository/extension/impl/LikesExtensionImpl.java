package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.repository.extension.LikesExtension;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikesExtensionImpl implements LikesExtension {
        private final EntityManager entityManager;

    @Override
    public Integer addLike(Long idBySlug, Long userId) {
        return entityManager.createNativeQuery("INSERT INTO LIKES (ARTICLE_ID, USER_ID) VALUES (?1,?2)")
                .setParameter(1, idBySlug).setParameter(2, userId).executeUpdate();
    }

    @Override
    public void removeLike(Long idBySlug, Long userId) {

    }

    @Override
    public Long likes(Long idBySlug, Long userId) {
        return null;
    }
}
