package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    @Query(value = "select t.name from article\n" +
            "join article_tag a on article.id = a.article_id\n" +
            "join tag t on a.tag_id = t.id\n" +
            "group by t.id,t.name\n" +
            "order by count(*) desc\n" +
            "limit 10;",nativeQuery = true)
    List<String> getPopularTags();

    Optional<Tag> findByName(String name);
}
