package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.repository.extension.LikesExtension;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LikesExtensionImpl implements LikesExtension {
    private final EntityManager entityManager;


    @Override
    @Transactional
    public void addLike(Long articleId, Long userId) {
        boolean isLiked = isLiked(articleId, userId);
        if (isLiked) {
            entityManager.createNativeQuery("INSERT INTO LIKES VALUES (?,?)")
                    .setParameter(1, articleId)
                    .setParameter(2, userId)
                    .executeUpdate();
        }
    }

    @Override
    public boolean isLiked(Long articleId,Long userId) {
        String query = "select * from likes where article_id = ? and user_id = ?";
        List resultList = entityManager.createNativeQuery(query)
                .setParameter(1, articleId)
                .setParameter(2, userId)
                .getResultList();
        return resultList.isEmpty();
    }

    @Override
    public Integer likesCount(Long articleId) {
        String query = "select * from likes where article_id = ?";
        List resultList = entityManager.createNativeQuery(query)
                .setParameter(1, articleId)
                .getResultList();
        return resultList.size();
    }

    @Override
    @Transactional
    public void removeLike(Long articleId, Long userId) {
        entityManager.createNativeQuery("DELETE FROM LIKES WHERE ARTICLE_ID = ? AND USER_ID = ?")
                .setParameter(1, articleId)
                .setParameter(2, userId)
                .executeUpdate();
    }


}
