package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserUsername(String username);
    Optional<Profile> findByUserEmail(String email);
    @Query(
            value = "select profile.* from profile join likes l on profile.id = l.user_id where l.article_id = ?1",
            nativeQuery = true
    )
    List<Profile> findAllByLikedArticle(Long articleId);
}
