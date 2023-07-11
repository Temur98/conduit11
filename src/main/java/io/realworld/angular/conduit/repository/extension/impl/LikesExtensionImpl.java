package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.repository.extension.LikesExtension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LikesExtensionImpl implements LikesExtension {
    private final EntityManager entityManager;


    @Override
    public Integer addLike(Long articleId, Long userId) {
        return entityManager.createNativeQuery("INSERT INTO LIKES (ARTICLE_ID, USER_ID) VALUES (?1,?2)")
                .setParameter(1, articleId).setParameter(2, userId).executeUpdate();
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
