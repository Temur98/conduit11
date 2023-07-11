package io.realworld.angular.conduit.repository.ext.impl;

import io.realworld.angular.conduit.dto.ArticleDto;
import io.realworld.angular.conduit.model.Article;
import io.realworld.angular.conduit.model.Profile;
import io.realworld.angular.conduit.model.Tag;
import io.realworld.angular.conduit.repository.UserRepository;
import io.realworld.angular.conduit.repository.ext.ArticleRepositoryExtension;
import io.realworld.angular.conduit.service.mapper.ArticleMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleRepositoryExtensionImpl implements ArticleRepositoryExtension {
    private final EntityManager entityManager;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository;
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
}
