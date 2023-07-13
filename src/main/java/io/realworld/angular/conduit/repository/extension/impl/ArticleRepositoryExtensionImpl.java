package io.realworld.angular.conduit.repository.extension.impl;

import io.realworld.angular.conduit.mapper.ArticleWithOwtDto;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.model.User;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.repository.extension.ArticleRepositoryExtension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ArticleRepositoryExtensionImpl implements ArticleRepositoryExtension {
    private final ArticleWithOwtDto articleMapper;
    private final UserRepository userRepository;
    private final EntityManager entityManager;


    @Override
    public List<Article> getAllArticles(Optional<Integer> limit, Optional<Integer> offset, Optional<String> author, Optional<String> favorited, Optional<String> tag) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> query = cb.createQuery(Article.class);
        Root<Article> root = query.from(Article.class);

        List<Predicate> predicates = new ArrayList<>();

        author.ifPresent(value->predicates.add(cb.equal(root.get("author").get("username"),value)));

        favorited.ifPresent(value->{
            ListJoin<Article, User> profileJoin = root.joinList("likes");
            predicates.add(cb.equal(profileJoin.get("user").get("username"),value));
        });

        tag.ifPresent(value->{
            ListJoin<Article, Tag> tagJoin = root.joinList("tags");
            predicates.add(cb.equal(tagJoin.get("name"),value));
        });


        CriteriaQuery<Article> cQuery = query.where(predicates.toArray(Predicate[]::new))
                .orderBy(cb.desc(root.get("updatedAt")));

        return entityManager.createQuery(cQuery).setFirstResult(offset.get()).setMaxResults(limit.get()).getResultList();
    }
}
