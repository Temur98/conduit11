package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.dto.ArticleDTO;
import io.realworld.angular.conduit.repository.extension.LikesExtension;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesExtensionImpl implements LikesExtension {
    private final EntityManager entityManager;


    public boolean save(Long article){
//        entityManager.createNativeQuery("");
        return true;
    }

    @Override
    public ResponseEntity<ArticleDTO> addLike(Long idBySlug, Long userId) {
        return null;
    }
}
