package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
