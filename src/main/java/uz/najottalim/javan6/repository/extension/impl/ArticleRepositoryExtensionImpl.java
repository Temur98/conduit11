package uz.najottalim.javan6.repository.extension.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.entity.Article;
import uz.najottalim.javan6.entity.Profile;
import uz.najottalim.javan6.entity.Tag;
import uz.najottalim.javan6.repository.extension.ArticleRepositoryExtension;

import java.util.*;
@Service
@RequiredArgsConstructor
public class ArticleRepositoryExtensionImpl implements ArticleRepositoryExtension {
    private final EntityManager entityManager;
    public List<Article> getArticlesPageable(Integer limit, Integer offset, Optional<String> author, Optional<String> favorited, Optional<String> tag)
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

        CriteriaQuery<Article> cQuery = query.where(predicates.toArray(Predicate[]::new));
        return entityManager.createQuery(cQuery).setFirstResult(offset).setMaxResults(limit).getResultList();
    }
}
