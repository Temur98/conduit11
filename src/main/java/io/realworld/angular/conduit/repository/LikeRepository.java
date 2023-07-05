package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
