package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.repository.extension.ProfileExtension;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileExtensionImpl implements ProfileExtension {
    private final EntityManager entityManager;

    @Override
    public void addFollow(Long userId, Long followerId){
        entityManager.createNativeQuery("INSERT INTO FOLLOWS(USER_ID, FOLLOWER_ID) VALUES (?,?)")
                .setParameter(1,userId)
                .setParameter(2,followerId).executeUpdate();
    }

    @Override
    public void unfollow(Long userId, Long followerId){
        entityManager.createNativeQuery("DELETE FROM FOLLOWS WHERE USER_ID = ? AND FOLLOWER_ID = ?")
                .setParameter(1,userId)
                .setParameter(2,followerId).executeUpdate();
    }
}
