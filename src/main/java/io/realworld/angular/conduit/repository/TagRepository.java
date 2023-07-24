package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
    List<Tag> findByName(String tag);
    List<Tag> findTagsByNameIn(List<String> names);
}
