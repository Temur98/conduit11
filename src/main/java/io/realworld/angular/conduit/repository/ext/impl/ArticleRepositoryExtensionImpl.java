package io.realworld.angular.conduit.repository.ext.impl;

import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Profile;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.ext.ArticleRepositoryExtension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleRepositoryExtensionImpl implements ArticleRepositoryExtension {
    private final EntityManager entityManager;
    public List<Article> getArticleListPageable(Integer limit, Integer offset, Optional<String> author, Optional<String> favorited, Optional<String> tag)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> query = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = query.from(Article.class);

        List<Predicate> predicates = new ArrayList<>();

        author.ifPresent(value->predicates.add(criteriaBuilder.equal(root.get("profile").get("user").get("username"),value)));

        favorited.ifPresent(value->{
            ListJoin<Article, Profile> profileJoin = root.joinList("likes");
            predicates.add(criteriaBuilder.equal(profileJoin.get("user").get("username"),value));
        });

        tag.ifPresent(value->{
            ListJoin<Article, Tag> tagJoin = root.joinList("tags");
            predicates.add(criteriaBuilder.equal(tagJoin.get("name"),value));
        });


        CriteriaQuery<Article> cQuery = query.where(predicates.toArray(Predicate[]::new))
                .orderBy(criteriaBuilder.desc(root.get("updateAt")));


        return entityManager.createQuery(cQuery)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    @Override
    @Transactional
    public List<Article> getArticlesByFollower(Long id, Integer limit, Integer offset) {
        String query = "select a.* from article a where a.profile_id in\n" +
                "                        (select user_id from followers where follower_id = ?) " +
                "order by a.update_at desc ";
        return entityManager.createNativeQuery(query,Article.class)
                .setParameter(1,id)
                .setMaxResults(limit)
                .setFirstResult(offset)
                .getResultList();

    }
//    @Override
//    @Transactional
//    public void likeArticle(Long articleId, Long userId) {
//        String query = "insert into likes values(?,?)";
//        entityManager.createNativeQuery(query)
//                .setParameter(1,articleId)
//                .setParameter(2,userId)
//                .executeUpdate();
//    }
    @Override
    @Transactional
    public void deleteLike(Long userId, Long articleId) {
        String query = "delete from likes where article_id = ? and user_id = ?";
        entityManager.createNativeQuery(query)
                .setParameter(1,articleId)
                .setParameter(2,userId)
                .executeUpdate();
    }
    @Transactional
    @Override
    public boolean isFavorited(Long articleId,Long userId) {
        String query = "select * from likes where article_id = ? and user_id = ?";
        List resultList = entityManager.createNativeQuery(query)
                .setParameter(1, articleId)
                .setParameter(2, userId)
                .getResultList();
        return !resultList.isEmpty();
    }
}
