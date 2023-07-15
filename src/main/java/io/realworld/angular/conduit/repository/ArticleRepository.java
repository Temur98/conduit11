package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.extension.ArticleExtension;
import io.realworld.angular.conduit.repository.extension.LikesExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>, LikesExtension, ArticleExtension {
    @Query(value = "select count(*) from LIKES where ARTICLE_ID = ?", nativeQuery = true)
    long getFavoritesCount(Long id);

    @Query(value = """
            select case when count(*) > 0 then 0 else 1 end
            from LIKES
            where USER_ID = ? and ARTICLE_ID = ?""", nativeQuery = true)
    Long isFavorited(Long userId, Long id);

    List<Article> findArticleByAuthorId(Long id, PageRequest pageRequest);


}
