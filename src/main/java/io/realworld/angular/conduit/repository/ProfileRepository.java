package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Profile;
import io.realworld.angular.conduit.repository.ext.ProfileRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileRepositoryExtension {
    Optional<Profile> findByUserUsername(String username);
    Optional<Profile> findByUserEmail(String email);
    @Query(
            value = "select profile.* from profile join likes l on profile.id = l.user_id where l.article_id = ?1",
            nativeQuery = true
    )
    List<Profile> findAllByLikedArticle(Long articleId);
//    @Query(value = "insert into followers(user_id, follower_id) values(?, ?)", nativeQuery = true)
//    void addFollow(Long userId, Long profileId);
//    @Query(value = "select case when count(*) > 0 then true else false end\n" +
//            "from PROFILE p join FOLLOWERS f on p.ID = f.USER_ID\n" +
//            "where f.USER_ID = ? and p.ID = ?",
//            nativeQuery = true)
//    boolean isFollowed(Long userId, Long profileId);
//    @Query(value = "delete from followers where user_id = ? and follower_id = ?", nativeQuery = true)
//    void deleteFollow(Long userId, Long profileId);
}
