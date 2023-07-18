package io.realworld.angular.conduit.repository;

import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.repository.ext.ArticleRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>, ArticleRepositoryExtension {
    @Query(value = "select count(*) from ARTICLE a join LIKES l on a.ID = l.ARTICLE_ID where a.id = ?", nativeQuery = true)
    long getFavoritesCount(Long id);

//    @Query(value = "select case when count(*) > 0 then true else false end\n" +
//            "from ARTICLE a join LIKES l on a.ID = l.ARTICLE_ID\n" +
//            "where l.USER_ID = ? and a.ID = ?",
//            nativeQuery = true)
//    boolean isFavorited(Long userId, Long id);
    @Query(value = "insert into likes(article_id, user_id) values(?, ?)",
    nativeQuery = true)
    void likeArticle(Long articleId, Long UserId);
//    @Query(value = "delete from likes where user_id = ? and article_id = ?",
//    nativeQuery = true)
//    void deleteLike(Long userId, Long id);
}
