package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
}
