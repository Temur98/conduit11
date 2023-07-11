package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.ext.ArticleRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>, ArticleRepositoryExtension {
    @Query(
            value = "select count(*) from likes where article_id = ?1",nativeQuery = true
    )
    Long getLikesCount(Long article_id);
    List<Article> findAllByProfile_UserEmail(String email);


}
