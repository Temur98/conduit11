package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.extension.ArticleExtension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ArticleExtensionImpl implements ArticleExtension {
    private final EntityManager entityManager;
    public List<Article> getArticlesPageable(Integer limit, Integer offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> query = criteriaBuilder.createQuery(Article.class);
        Root<Article> root = query.from(Article.class);

        List<Predicate> predicates = new ArrayList<>();

        author.ifPresent(value->predicates.add(criteriaBuilder.equal(root.get("author").get("username"),value)));

        favorited.ifPresent(value -> {
            ListJoin<Article, User> profileJoin = root.joinList("likes");
            predicates.add(criteriaBuilder.equal(profileJoin.get("username"),value));
        });

        tag.ifPresent(value->{
            ListJoin<Article, Tag> tagJoin = root.joinList("tags");
            predicates.add(criteriaBuilder.equal(tagJoin.get("name"),value));
        });


        CriteriaQuery<Article> cQuery = query.where(predicates.toArray(Predicate[]::new))
                .orderBy(criteriaBuilder.desc(root.get("updatedAt")));

        return entityManager.createQuery(cQuery).setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    @Override
    @Transactional
    public void likeArticle(Long articleId, Long userId) {
        String query = "insert into likes values(?,?)";
        entityManager.createNativeQuery(query)
                .setParameter(1,articleId)
                .setParameter(2,userId)
                .executeUpdate();
    }
    @Transactional
    @Override
    public boolean isCurrentUserLiked(Long articleId,Long userId) {
        String query = "select * from likes where article_id = ? and user_id = ?";
        List resultList = entityManager.createNativeQuery(query)
                .setParameter(1, articleId)
                .setParameter(2, userId)
                .getResultList();
        return !resultList.isEmpty();
    }
}
