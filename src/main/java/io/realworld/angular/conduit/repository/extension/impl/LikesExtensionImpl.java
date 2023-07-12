package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.repository.extension.LikesExtension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
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
        boolean userDontClickedLike = isCurrentUserDontClickedLike(articleId, userId);
        if (userDontClickedLike) {
            entityManager.createNativeQuery("INSERT INTO LIKES VALUES (?,?)")
                    .setParameter(1, articleId)
                    .setParameter(2, userId)
                    .executeUpdate();
        }
    }

    public boolean isCurrentUserDontClickedLike(Long articleId,Long userId) {
        String query = "select * from likes where article_id = ? and user_id = ?";
        List resultList = entityManager.createNativeQuery(query)
                .setParameter(1, articleId)
                .setParameter(2, userId)
                .getResultList();
        return resultList.isEmpty();
    }

    @Override
    public void removeLike(Long idBySlug, Long userId) {
        
    }

    @Override
    public Long likes(Long idBySlug, Long userId) {
//        Query query = entityManager.createNativeQuery("select * from ARTICLES a join LIKES l on a.ID = l.ARTICLE_ID where a.id = ?", Tuple.class);
//        List<Tuple> likes = (List<Tuple>) query.getResultList();
//
//        likes.stream()
//                .map()
//
//        return null;
        return null;
    }


}
